/*
 * Copyright (c) 1996, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package java.lang.reflect;

import sun.reflect.CallerSensitive;
import sun.reflect.ConstructorAccessor;
import sun.reflect.Reflection;
import sun.reflect.annotation.TypeAnnotation;
import sun.reflect.annotation.TypeAnnotationParser;
import sun.reflect.generics.repository.ConstructorRepository;
import sun.reflect.generics.factory.CoreReflectionFactory;
import sun.reflect.generics.factory.GenericsFactory;
import sun.reflect.generics.scope.ConstructorScope;
import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationFormatError;

/**
 * {@code Constructor} provides information about, and access to, a single
 * constructor for a class. //构造函数提供关于和访问类的单个构造函数的信息。
 *
 * <p>{@code Constructor} permits widening conversions to occur when matching the
 * actual parameters to newInstance() with the underlying
 * constructor's formal parameters, but throws an
 * {@code IllegalArgumentException} if a narrowing conversion would occur.
 *  各个参数将自动拆箱以匹配原始形式参数，并且原始参数和引用参数必须根据需要进行方法调用转换。
 * @param <T> the class in which the constructor is declared
 *
 * @see Member
 * @see java.lang.Class
 * @see java.lang.Class#getConstructors()
 * @see java.lang.Class#getConstructor(Class[])
 * @see java.lang.Class#getDeclaredConstructors()
 *
 * @author      Kenneth Russell
 * @author      Nakul Saraiya
 */
public final class Constructor<T> extends Executable {
    private Class<T>            clazz;  //当前构造器所属的类Class
    private int                 slot;
    private Class<?>[]          parameterTypes; //参数类型
    private Class<?>[]          exceptionTypes; //异常类型
    private int                 modifiers;  //java描述符
    // Generics and annotations support //泛型和注释支持
    private transient String    signature;
    // generic info repository; lazily initialized  //泛型信息存储库;延迟初始化
    private transient ConstructorRepository genericInfo;
    private byte[]              annotations;
    private byte[]              parameterAnnotations;

    // Generics infrastructure
    // Accessor for factory
    private GenericsFactory getFactory() {  //获取泛型工厂
        // create scope and factory
        return CoreReflectionFactory.make(this, ConstructorScope.make(this));
    }

    // Accessor for generic info repository
    @Override
    ConstructorRepository getGenericInfo() {    //获取泛型信息
        // lazily initialize repository if necessary
        if (genericInfo == null) {
            // create and cache generic info repository
            genericInfo =
                ConstructorRepository.make(getSignature(),
                                           getFactory());
        }
        return genericInfo; //return cached repository
    }

    private volatile ConstructorAccessor constructorAccessor;//构造器访问器
    // For sharing of ConstructorAccessors. This branching structure
    // is currently only two levels deep (i.e., one root Constructor
    // and potentially many Constructor objects pointing to it.)
    //
    // If this branching structure would ever contain cycles, deadlocks can
    // occur in annotation code.
    private Constructor<T>      root;

    /**
     * Used by Excecutable for annotation sharing.
     */
    @Override
    Executable getRoot() {
        return root;
    }

    /**
     * Package-private constructor used by ReflectAccess to enable
     * instantiation of these objects in Java code from the java.lang
     * package via sun.reflect.LangReflectAccess.
     */
    Constructor(Class<T> declaringClass,
                Class<?>[] parameterTypes,
                Class<?>[] checkedExceptions,
                int modifiers,
                int slot,
                String signature,
                byte[] annotations,
                byte[] parameterAnnotations) {
        this.clazz = declaringClass;
        this.parameterTypes = parameterTypes;
        this.exceptionTypes = checkedExceptions;
        this.modifiers = modifiers;
        this.slot = slot;
        this.signature = signature;
        this.annotations = annotations;
        this.parameterAnnotations = parameterAnnotations;
    }

    /**
     * Package-private routine (exposed to java.lang.Class via
     * ReflectAccess) which returns a copy of this Constructor. The copy's
     * "root" field points to this Constructor.
     */
    Constructor<T> copy() {
        // This routine enables sharing of ConstructorAccessor objects
        // among Constructor objects which refer to the same underlying
        // method in the VM. (All of this contortion is only necessary
        // because of the "accessibility" bit in AccessibleObject,
        // which implicitly requires that new java.lang.reflect
        // objects be fabricated for each reflective call on Class
        // objects.)
        if (this.root != null)
            throw new IllegalArgumentException("Can not copy a non-root Constructor");

        Constructor<T> res = new Constructor<>(clazz,
                                               parameterTypes,
                                               exceptionTypes, modifiers, slot,
                                               signature,
                                               annotations,
                                               parameterAnnotations);
        res.root = this;
        // Might as well eagerly propagate this if already present
        res.constructorAccessor = constructorAccessor;
        return res;
    }

    @Override
    boolean hasGenericInformation() {
        return (getSignature() != null);
    }

    @Override
    byte[] getAnnotationBytes() {
        return annotations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<T> getDeclaringClass() {   //获取该构造器哦起所属类Class
        return clazz;
    }

    /**
     * Returns the name of this constructor, as a string.  This is
     * the binary name of the constructor's declaring class.
     */
    @Override
    public String getName() {
        return getDeclaringClass().getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getModifiers() { //获取修饰符的int值
        return modifiers;
    }

    /**
     * {@inheritDoc}
     * @throws GenericSignatureFormatError {@inheritDoc}
     * @since 1.5
     */
    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public TypeVariable<Constructor<T>>[] getTypeParameters() {
      if (getSignature() != null) {
        return (TypeVariable<Constructor<T>>[])getGenericInfo().getTypeParameters();
      } else
          return (TypeVariable<Constructor<T>>[])new TypeVariable[0];
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?>[] getParameterTypes() { //获取构造器的参数列表类型
        return parameterTypes.clone();
    }

    /**
     * {@inheritDoc}
     * @since 1.8
     */
    public int getParameterCount() { return parameterTypes.length; }//返回构造器的参数数量

    /**
     * {@inheritDoc}
     * @throws GenericSignatureFormatError {@inheritDoc}
     * @throws TypeNotPresentException {@inheritDoc}
     * @throws MalformedParameterizedTypeException {@inheritDoc}
     * @since 1.5
     */
    @Override
    public Type[] getGenericParameterTypes() {
        return super.getGenericParameterTypes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?>[] getExceptionTypes() {//返回构造器对象异常类型,//表示由此构造方对象表示的底层构造函数声明的异常类型
        return exceptionTypes.clone();
    }


    /**
     * {@inheritDoc}
     * @throws GenericSignatureFormatError {@inheritDoc}
     * @throws TypeNotPresentException {@inheritDoc}
     * @throws MalformedParameterizedTypeException {@inheritDoc}
     * @since 1.5
     */
    @Override
    public Type[] getGenericExceptionTypes() {//声明为该Constructor对象抛出的异常
        return super.getGenericExceptionTypes();
    }

    /**
     * Compares this {@code Constructor} against the specified object.
     * Returns true if the objects are the same.  Two {@code Constructor} objects are
     * the same if they were declared by the same class and have the
     * same formal parameter types.
     */
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Constructor) {
            Constructor<?> other = (Constructor<?>)obj;
            if (getDeclaringClass() == other.getDeclaringClass()) {
                return equalParamTypes(parameterTypes, other.parameterTypes);
            }
        }
        return false;
    }

    /**
     * Returns a hashcode for this {@code Constructor}. The hashcode is
     * the same as the hashcode for the underlying constructor's
     * declaring class name.
     */
    public int hashCode() {
        return getDeclaringClass().getName().hashCode();
    }

    /**
     * Returns a string describing this {@code Constructor}.  The string is
     * formatted as the constructor access modifiers, if any,
     * followed by the fully-qualified name of the declaring class,
     * followed by a parenthesized, comma-separated list of the
     * constructor's formal parameter types.  For example:
     * <pre>
     *    public java.util.Hashtable(int,float)
     * </pre>
     *
     * <p>The only possible modifiers for constructors are the access
     * modifiers {@code public}, {@code protected} or
     * {@code private}.  Only one of these may appear, or none if the
     * constructor has default (package) access.
     *
     * @return a string describing this {@code Constructor}
     * @jls 8.8.3. Constructor Modifiers
     */
    public String toString() {
        return sharedToString(Modifier.constructorModifiers(),
                              false,
                              parameterTypes,
                              exceptionTypes);
    }

    @Override
    void specificToStringHeader(StringBuilder sb) {
        sb.append(getDeclaringClass().getTypeName());
    }

    /**
     * Returns a string describing this {@code Constructor},
     * including type parameters.  The string is formatted as the
     * constructor access modifiers, if any, followed by an
     * angle-bracketed comma separated list of the constructor's type
     * parameters, if any, followed by the fully-qualified name of the
     * declaring class, followed by a parenthesized, comma-separated
     * list of the constructor's generic formal parameter types.
     *
     * If this constructor was declared to take a variable number of
     * arguments, instead of denoting the last parameter as
     * "<tt><i>Type</i>[]</tt>", it is denoted as
     * "<tt><i>Type</i>...</tt>".
     *
     * A space is used to separate access modifiers from one another
     * and from the type parameters or return type.  If there are no
     * type parameters, the type parameter list is elided; if the type
     * parameter list is present, a space separates the list from the
     * class name.  If the constructor is declared to throw
     * exceptions, the parameter list is followed by a space, followed
     * by the word "{@code throws}" followed by a
     * comma-separated list of the thrown exception types.
     *
     * <p>The only possible modifiers for constructors are the access
     * modifiers {@code public}, {@code protected} or
     * {@code private}.  Only one of these may appear, or none if the
     * constructor has default (package) access.
     *
     * @return a string describing this {@code Constructor},
     * include type parameters
     *
     * @since 1.5
     * @jls 8.8.3. Constructor Modifiers
     */
    @Override
    public String toGenericString() {
        return sharedToGenericString(Modifier.constructorModifiers(), false);
    }

    @Override
    void specificToGenericStringHeader(StringBuilder sb) {
        specificToStringHeader(sb);
    }

    /**
     * Uses the constructor represented by this {@code Constructor} object to
     * create and initialize a new instance of the constructor's
     * declaring class, with the specified initialization parameters.
     * Individual parameters are automatically unwrapped to match
     * primitive formal parameters, and both primitive and reference
     * parameters are subject to method invocation conversions as necessary.
     *
     * <p>If the number of formal parameters required by the underlying constructor
     * is 0, the supplied {@code initargs} array may be of length 0 or null.
     *
     * <p>If the constructor's declaring class is an inner class in a
     * non-static context, the first argument to the constructor needs
     * to be the enclosing instance; see section 15.9.3 of
     * <cite>The Java&trade; Language Specification</cite>.
     *
     * <p>If the required access and argument checks succeed and the
     * instantiation will proceed, the constructor's declaring class
     * is initialized if it has not already been initialized.
     *
     * <p>If the constructor completes normally, returns the newly
     * created and initialized instance.
     *
     * @param initargs array of objects to be passed as arguments to
     * the constructor call; values of primitive types are wrapped in
     * a wrapper object of the appropriate type (e.g. a {@code float}
     * in a {@link java.lang.Float Float})
     *
     * @return a new object created by calling the constructor
     * this object represents
     *
     * @exception IllegalAccessException    if this {@code Constructor} object
     *              is enforcing Java language access control and the underlying
     *              constructor is inaccessible.
     * @exception IllegalArgumentException  if the number of actual
     *              and formal parameters differ; if an unwrapping
     *              conversion for primitive arguments fails; or if,
     *              after possible unwrapping, a parameter value
     *              cannot be converted to the corresponding formal
     *              parameter type by a method invocation conversion; if
     *              this constructor pertains to an enum type.
     * @exception InstantiationException    if the class that declares the
     *              underlying constructor represents an abstract class.
     * @exception InvocationTargetException if the underlying constructor
     *              throws an exception.
     * @exception ExceptionInInitializerError if the initialization provoked
     *              by this method fails.
     */
    @CallerSensitive
    public T newInstance(Object ... initargs)
        throws InstantiationException, IllegalAccessException,
               IllegalArgumentException, InvocationTargetException
    {   //通过参数列表，构造该构造器对应的类的实例对象
        if (!override) {//该标识表示是否覆盖语言级别的访问检查权限，初始false，可以通过通过constructor1.setAccessible(true)设置为true
            if (!Reflection.quickCheckMemberAccess(clazz, modifiers)) {
                Class<?> caller = Reflection.getCallerClass();  //获取调用类
                checkAccess(caller, clazz, null, modifiers);//校验权限
            }
        }
        if ((clazz.getModifiers() & Modifier.ENUM) != 0)    //修饰符是否为枚举类型
            throw new IllegalArgumentException("Cannot reflectively create enum objects");
        ConstructorAccessor ca = constructorAccessor;   // read volatile
        if (ca == null) {
            ca = acquireConstructorAccessor();  //获取构造器的访问器
        }
        @SuppressWarnings("unchecked")
        T inst = (T) ca.newInstance(initargs);//通过构造函数声明类的newInstance来实例化对象
        return inst;
    }

    /**
     * {@inheritDoc}
     * @since 1.5
     */
    @Override
    public boolean isVarArgs() {
        return super.isVarArgs();
    }

    /**
     * {@inheritDoc}
     * @jls 13.1 The Form of a Binary
     * @since 1.5
     */
    @Override
    public boolean isSynthetic() {
        return super.isSynthetic();
    }

    // NOTE that there is no synchronization used here. It is correct
    // (though not efficient) to generate more than one
    // ConstructorAccessor for a given Constructor. However, avoiding
    // synchronization will probably make the implementation more
    // scalable.
    private ConstructorAccessor acquireConstructorAccessor() {
        // First check to see if one has been created yet, and take it
        // if so.
        ConstructorAccessor tmp = null;
        if (root != null) tmp = root.getConstructorAccessor();//获取上层的ConstructorAccessor
        if (tmp != null) {
            constructorAccessor = tmp;
        } else {
            // Otherwise fabricate one and propagate it up to the root
            tmp = reflectionFactory.newConstructorAccessor(this);
            setConstructorAccessor(tmp);
        }

        return tmp;
    }

    // Returns ConstructorAccessor for this Constructor object, not
    // looking up the chain to the root
    ConstructorAccessor getConstructorAccessor() {
        return constructorAccessor;
    }

    // Sets the ConstructorAccessor for this Constructor object and
    // (recursively) its root
    void setConstructorAccessor(ConstructorAccessor accessor) { //设置构造函数访问器（构造函数声明类）
        constructorAccessor = accessor;
        // Propagate up
        if (root != null) {
            root.setConstructorAccessor(accessor);
        }
    }

    int getSlot() {
        return slot;
    }

    String getSignature() {
        return signature;
    }

    byte[] getRawAnnotations() {
        return annotations;
    }

    byte[] getRawParameterAnnotations() {
        return parameterAnnotations;
    }


    /**
     * {@inheritDoc}
     * @throws NullPointerException  {@inheritDoc}
     * @since 1.5
     */
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return super.getAnnotation(annotationClass);
    }

    /**
     * {@inheritDoc}
     * @since 1.5
     */
    public Annotation[] getDeclaredAnnotations()  {
        return super.getDeclaredAnnotations();
    }

    /**
     * {@inheritDoc}
     * @since 1.5
     */
    @Override
    public Annotation[][] getParameterAnnotations() {
        return sharedGetParameterAnnotations(parameterTypes, parameterAnnotations);
    }

    @Override
    void handleParameterNumberMismatch(int resultLength, int numParameters) {
        Class<?> declaringClass = getDeclaringClass();
        if (declaringClass.isEnum() ||
            declaringClass.isAnonymousClass() ||
            declaringClass.isLocalClass() )
            return ; // Can't do reliable parameter counting
        else {
            if (!declaringClass.isMemberClass() || // top-level
                // Check for the enclosing instance parameter for
                // non-static member classes
                (declaringClass.isMemberClass() &&
                 ((declaringClass.getModifiers() & Modifier.STATIC) == 0)  &&
                 resultLength + 1 != numParameters) ) {
                throw new AnnotationFormatError(
                          "Parameter annotations don't match number of parameters");
            }
        }
    }

    /**
     * {@inheritDoc}
     * @since 1.8
     */
    @Override
    public AnnotatedType getAnnotatedReturnType() {
        return getAnnotatedReturnType0(getDeclaringClass());
    }

    /**
     * {@inheritDoc}
     * @since 1.8
     */
    @Override
    public AnnotatedType getAnnotatedReceiverType() {//返回构造器的接收方类型
        if (getDeclaringClass().getEnclosingClass() == null)
            return super.getAnnotatedReceiverType();//放回一个方法/构造函数接收方类型

        return TypeAnnotationParser.buildAnnotatedType(getTypeAnnotationBytes0(),
                sun.misc.SharedSecrets.getJavaLangAccess().
                        getConstantPool(getDeclaringClass()),
                this,
                getDeclaringClass(),
                getDeclaringClass().getEnclosingClass(),
                TypeAnnotation.TypeAnnotationTarget.METHOD_RECEIVER);
    }
}
