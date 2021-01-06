# LinkedList源码分析

## 1.简介

​	LinkedList底层的数据结构是双向链表。由于是链表结构，所以它的新增和删除效率很高，而查询效率就相对较低了。

```java
public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable
```

- 实现了List接口。
- 实现Deque接口，代表底层是双端队列
- 实现Cloneable，Serializable 拷贝和序列化

## 2.属性

```java
	transient int size = 0;//表示集合中元素个数

    transient Node<E> first;//链表的头节点

    transient Node<E> last;//链表的尾节点
```

## 3.构造方法

```java
public LinkedList() {
    }

    public LinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }
```

LinkedList是链表结构，不需要预先设置容量，直接添加即可，与ArrayList底层是数组不同。

## 4.常用方法

### 4.1add()

```java
public boolean add(E e) {//添加元素到链表尾端
        linkLast(e);    //构建元素e的结点并添加到链表尾端
        return true;
    }

void linkLast(E e) {//将链表e作为链表最后一个元素
        final Node<E> l = last;//变量l暂存链表最后一个结点last
        final Node<E> newNode = new Node<>(l, e, null);//将原链表最后一个结点l，待添加元素e，重新构建一个元素为e的结点，它的前置结点为l
        last = newNode;//将新构建的结点赋给last
        if (l == null)  //如果这个l为空,则该连表就变为只有一个结点e的链表，所以他的第一个结点first也是nowNode
            first = newNode;
        else    //否则该l结点的next结点就是newNode
            l.next = newNode;
        size++; //链表大小size+1
        modCount++; //修改计数+1
    }

private static class Node<E> {      //LinkedList底层数据结构，定义一个Node结点
        E item;     //结点元素（数据）
        Node<E> next;   //前置结点
        Node<E> prev;   //后置结点

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
```

add()方法：

将元素添加到链表尾端。

### 4.2addFirst()

```java
public void addFirst(E e) {     //在链表头部添加元素
        linkFirst(e);
    }

private void linkFirst(E e) {   //参照linkLast方法，这里是添加头部first
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
        modCount++;
    }
```

### 4.3add(int index, E element)

```java
public void add(int index, E element) {     //在指定下标处添加元素
        checkPositionIndex(index);      //校验下标

        if (index == size)  //如果下标是链表的size
            linkLast(element);      //直接在链表尾部添加元素
        else
            linkBefore(element, node(index));   //在指定下标结点前添加元素
    }

private void checkPositionIndex(int index) {        //检测下标位置合法性
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

void linkBefore(E e, Node<E> succ) {    //在非空结点succ之前添加一个元素为e的结点    
        // assert succ != null;
        final Node<E> pred = succ.prev;     //获取succ之前的结点
        final Node<E> newNode = new Node<>(pred, e, succ);//构造新节点，前置结点为prev、后置结点succ
        succ.prev = newNode;    //关联succ前置结点prev
        if (pred == null)       //如果succ原来的前置结点为null，则要添加的结点就是第一个几点
            first = newNode;
        else
            pred.next = newNode;    //关联pred的后置结点next
        size++;     //链表大小+1
        modCount++; //修改次数+1
    }
```

在指定的下标处添加元素。

### 4.4contains()

```java
public boolean contains(Object o) {     //查看某个元素是否在链表内
        return indexOf(o) != -1;
    }

public int indexOf(Object o) {      //查看链表中指定元素的下标
        int index = 0;  //下标为0，从链表头部开始遍历
        if (o == null) {    //如果元素为null，则查询链表中第一个为null的元素的下标
            for (Node<E> x = first; x != null; x = x.next) {        //遍历链表中的结点找出null结点下标
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {        //遍历
                if (o.equals(x.item))       //找出结点元素对应的下标
                    return index;
                index++;
            }
        }
        return -1;  //若没有找到对应元素返回-1
    }
```

查看集合中是否包含某个元素。

### 4.5clear()

```java
public void clear() {//清空该集合链表中的所有元素
        // Clearing all of the links between nodes is "unnecessary", but:
        // - helps a generational GC if the discarded nodes inhabit
        //   more than one generation
        // - is sure to free memory even if there is a reachable Iterator
        for (Node<E> x = first; x != null; ) {//遍历链表中的非null结点,进行清理
            Node<E> next = x.next;  //暂存该节点的后置结点
            x.item = null;  //清理值
            x.next = null;  //清理后置结点
            x.prev = null;  //清理前置结点
            x = next;
        }
        first = last = null;    //将头节点和尾结点设为null
        size = 0;   //链表大小设为0
        modCount++; //修改次数+1
    }
```

将集合中的元素全部清空。

### 4.6get()

```java
public E get(int index) {   //获取某个下标下的元素
    checkElementIndex(index);   //校验下标
    return node(index).item;    //返回该结点的值
}

Node<E> node(int index) {   //构造指定下标的结点对象
        // assert isElementIndex(index);

        if (index < (size >> 1)) {  //如果下标小于链表大小的一半
            Node<E> x = first;  //暂存头结点 ，从头节点开始遍历
            for (int i = 0; i < index; i++)//返回index之前的一个结点
                x = x.next;
            return x;
        } else {
            Node<E> x = last;//从尾节点开始遍历
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
```

### 4.7remove()

```java
public E remove(int index) {//删除指定下标的结点
    checkElementIndex(index);
    return unlink(node(index));//顶层是依赖unLink方法
}

E unlink(Node<E> x) {   //解除非空节点x的链接
        // assert x != null;
        final E element = x.item;   //x节点的值
        final Node<E> next = x.next;    //x结点的后置节点
        final Node<E> prev = x.prev;    //x结点的前置结点

        if (prev == null) {     //如果x节点的前置结点为null,说明x结点为头节点，
            first = next;       //直接将头节点改为x结点的后置结点
        } else {    //前置节点不为null
            prev.next = next;   //将前置结点的后置结点关联x的后置结点next
            x.prev = null;      //将x的前置结点设为null
        }

        if (next == null) {     //如果x结点的后置结点为null，说明x结点为尾结点
            last = prev;        //直接将为节点改为x节点的前置结点
        } else {    //后置节点不为null
            next.prev = prev;   //将x的后置结点的前置结点设为x的前置结点
            x.next = null;      //x的后置节点设为null
        }

        x.item = null;  //将x节点值设为null
        size--;     //链表大小-1
        modCount++; //修改操作+1
        return element;
    }
```

小结：
（1）底层是双向链表；
（2）它是有序集合。
（3）链表元素可以重复。
（4）随机访问效率低，增删效率高。