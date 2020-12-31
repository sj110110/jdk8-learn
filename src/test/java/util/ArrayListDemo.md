# ArrayList源码分析

## 1.简介

​	ArrayList 底层以数组为存储结构的java集合类。由于底层是数组结构，所以他的查询效率很高，而插入效率较低，
因为需要移动数组中的元素。ArrayList是线程不安全的。

## 2.属性

```java
private static final long serialVersionUID = 8683452581122892189L;

private static final int DEFAULT_CAPACITY = 10;//默认容量大小10

private static final Object[] EMPTY_ELEMENTDATA = {};//使用有参构造且initcapacity=0时会指向该数组

private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};//调用无参构造时会指向该数组

transient Object[] elementData;//实际数据使用该数组进行保存

private int size;//数组元素个数（集合的实际元素的个数）
```

## 3.构造方法

```java
public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {//initcapacity不为0时初始化elementData
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;//让elementData引用空的数组EMPTY_ELEMENTDATA
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        }
    }

public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;//默认初始化时将一个默认空数组赋值给elementData数组，此时数组长度为0
    }
```

## 4.常用方法

### 4.1add()

```java
public boolean add(E e) {
        ensureCapacityInternal(size + 1);  //传入最小的容量=1+size
        elementData[size++] = e;//将数据保存到对象数组中
        return true;
    }

private void ensureCapacityInternal(int minCapacity) {//确定是否需要扩容
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);//确定是否需要扩容
    }

    private void ensureExplicitCapacity(int minCapacity) {//确定容量
        modCount++;//修改标记位+1

        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);//扩容
    }
 
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);//newCapacity = 1.5 * oldCapacity
        if (newCapacity - minCapacity < 0) //是判断是否真的需要扩容（当前集合容量的1.5倍 > 最小容量（初始10））
            newCapacity = minCapacity;//如果新容量大小<最小容量10，就进行不需要扩容，但是还是会进行下面的拷贝操作
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);//将元素数组通过拷贝操作进行重新赋值
    }
```

add()方法：

（1）首先根据当前集合实际大小size+1来确认是否需要扩容；

（2）如果是空集合并且第一次add添加元素，则将容量扩大为10；

（3）如果不是第一次扩容则判断当前容量大小超过size时，进行扩容，扩容大小为元素组的1.5倍

### 4.2remove()

```java
	public E remove(int index) {
        rangeCheck(index);//判断下标合法性

        modCount++;
        E oldValue = elementData(index);//暂存改下表下的元素，用于返回值

        int numMoved = size - index - 1;//确定需要移动的元素个数，也就是从下标处后面需要移动的元素
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);//通过数组拷贝的方法，将下标出元素移动到最后一位
        elementData[--size] = null; // clear to let GC do its work//把移动过后的最后一个下标位置置为null

        return oldValue;
    }

	private void rangeCheck(int index) {//判断下标是否异常
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }


    public boolean remove(Object o) {
        if (o == null) {//删除集合中为null的元素，实际就是将所有未null得元素移至尾部
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)  //遍历集合
                if (o.equals(elementData[index])) { //将该元素与集合中的元素进行比对
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

	private void fastRemove(int index) {    //快速移除，就是跳过对下标进行检查，前提是调用方确保被删除元素确实存在
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null;
    }
```

### 4.3clear()

```java
public void clear() {
        modCount++;

        // clear to let GC do its work
        for (int i = 0; i < size; i++)//遍历集合，将每个下标置为null
            elementData[i] = null;

        size = 0;
    }
```

遍历集合，删除所有元素。（全部置为null）

### 4.4trimToSize()

```java
public void trimToSize() {//调整容器大小为当前元素所占用的大小
        modCount++;
        if (size < elementData.length) {
            elementData = (size == 0)
              ? EMPTY_ELEMENTDATA
              : Arrays.copyOf(elementData, size);//将存储元素的数组重新赋值为容量大小为实际容量大小的数组
        }
    }
```

调整集合容器的大小。

### 4.5set()

```java
public E set(int index, E element) {
        rangeCheck(index);//下标检查

        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }
```

用于替换指定下标下的元素。