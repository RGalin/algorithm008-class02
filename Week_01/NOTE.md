**学习总结**
在第一周的算法训练营的学习中，主要学习了数组（Array），链表（Linked List）, 跳表（Skip List），队列(Queue)，栈(Stack)，双端队列(Deque)，优先队列（Priority Queue）。
算法的技巧主要有：快慢指针，Stack求解最近相关性问题，寻找最近重复子问题;
编程的思维：升维，空间换时间。
数组
	支持O(1)的随机访问
	平均为O(n)的插入和删除
	警惕越界错误；导致Stack Over Flow
	
单链表
	不支持随机访问，需要遍历去访问结点
	插入和删除只需要移动指针，时间复杂度为O(1)
	每个结点需要额外的空间存储指针，需要的内存比数组大
	
双链表
	在单链表的基础上，除去头结点外，每个结点多了一个存放前驱结点地址的指针
	
循环链表
	为节点指针指向头结点
	
静态链表
	借助数组，伴随指向后继结点的指针
	
栈
	顺序和链式都可以实现，先进后出
	实际应用
		浏览器的前进与后退
		括号匹配
		表达式计算

队列
	普通队列
		顺序和链式都可以实现，先进先出
	双边队列
		入口和出口都可以进队和出队
	优先级队列
		根据优先级出队
	实际应用 
		LRU Cache

感想：
    在编程中是离不开算法，算法更像是一种对实际问题的数学抽象而给出的一种解觉方法。编程人员将这个抽象出来的问题转换成计算机所能读懂的语言，这样才可以将具体的实际问题交给计算机取处理，
而不需要耗费大量的人力和物力来做这些重复性的事情。而在计算机的领域里。存储、计算、通信这三个主要主题中，都需要使用到算法。只有拥有更好的算法，才能将有限的资源达到最大化的利用。
算法=控制逻辑+数据结构。
控制逻辑：我们只能控制计算机对代码的逻辑处理分支。
数据结构：待处理数据集的组织方式，每种数据结构都有自己本身的特点，我们只有选择符合自己需要处理的数据的数据结构，才能让我们更好更方便的去操作数据。
计算机能够理解的都是 if-else, for-loop, recursion这写控制逻辑。
学习算法的好处：
    1、了解计算程序处理的本质。
    2、写出更高效更简洁的代码。
    3、提高自己对代码的阅读和理解能力。


**Deque API改造代码**

public class Deque<Item>{

    private int N = 0;
    //Java种无法直接创建Item泛型的数组，所以只能使用Object类型的数组强制转换成Item类型的数组。
    private Item a[] = (Item[]) new Object[1];
    private int head = 0;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void addFirst(Item item) {
        if (N == a.length) resize(2 * a.length);
        if (item == null) throw new NullPointerException();
        if (isEmpty()) a[N++] =item;
        else {
            for (int i = N; i > 0; i--) {
                a[i] = a[i - 1];
            }
            a[head] = item;
            N++;
        }
    }

    public void addLast(Item item) {
        if (N == a.length) resize(2 * a.length);
        if (item == null) throw new NullPointerException();
        a[N++] = item;
    }

    /**
     * 扩大数组容量
     * @param max
     */
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        head = 0;
        System.arraycopy(a, 0, temp, 0, N);
        a = temp;
    }
}

**Queue & Priority Queue源码分析**
JDK version: jdk8

Queue
在JDK8中被定义为Interface,同时继承自Collection接口。Queue中的定义的方法：
boolean add(E e) -- 往队列中添加元素，返回boolean值，当满队列时，此方法会抛出IllegalStateException异常。
E element() -- 遍历并返回队列头部的元素，但不会把元素从队列中移除。
boolean offer(E e) -- 往队列中添加元素，当满队列时会返回特殊的值。
E peek() -- 遍历并返回队列头部的元素，但不会把元素从队列中移除,当队列为空时，返回null。
E poll() -- 遍历并返回队列头部的元素,同时会移除队头的元素,当队列为空时，返回null。
E remove() -- 遍历并返回队列头部的元素,同时会移除队头的元素。

PriorityQueue
PriorityQueue是一个具体的实现类，它继承自AbstractQueue抽象类。这个抽象类实现了Queue接口。
它的底层数据结构使用了priority heap，队列中的元素的顺序是natural ordering，或者是跟据用户提供的Comparator来进行排序的。

入队操作：
    /**
     * Inserts the specified element into this priority queue.
     *
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws ClassCastException if the specified element cannot be
     *         compared with elements currently in this priority queue
     *         according to the priority queue's ordering
     * @throws NullPointerException if the specified element is null
     */
    public boolean add(E e) {
        return offer(e);
    }

    /**
     * Inserts the specified element into this priority queue.
     *
     * @return {@code true} (as specified by {@link Queue#offer})
     * @throws ClassCastException if the specified element cannot be
     *         compared with elements currently in this priority queue
     *         according to the priority queue's ordering
     * @throws NullPointerException if the specified element is null
     */
    public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException();
        modCount++; //记录操作次数
        int i = size;
        if (i >= queue.length) //判断当前队列的size是否超过了最大队列的size,然后决定是否需要扩容。
            grow(i + 1);
        size = i + 1;
        if (i == 0) //首次向队列中添加元素
            queue[0] = e;
        else
            siftUp(i, e); //元素在队列中的顺序规则。
        return true;
    }
    
    /**
     * Inserts item x at position k, maintaining heap invariant by
     * promoting x up the tree until it is greater than or equal to
     * its parent, or is the root.
     *
     * To simplify and speed up coercions and comparisons. the
     * Comparable and Comparator versions are separated into different
     * methods that are otherwise identical. (Similarly for siftDown.)
     *
     * @param k the position to fill
     * @param x the item to insert
     */
    private void siftUp(int k, E x) {
        if (comparator != null)
            siftUpUsingComparator(k, x);
        else
            siftUpComparable(k, x);
    }
    
出队操作：poll(), peek(),
    public E poll() {
       if (size == 0) //空队列
           return null;
       int s = --size; 
       modCount++;
       E result = (E) queue[0]; //获取当前队列头的元素
       E x = (E) queue[s]; //取除队尾的元素
       queue[s] = null;//唤醒Java垃圾回收机制，回收数组s位置的内存空间。
       if (s != 0)
           siftDown(0, x); //从新调整队列中元素的顺序
       return result;
    }
    
    //peek方法直接返回队头元素。队列为空时返回null,但不会移除队头的元素。
    public E peek() {
       return (size == 0) ? null : (E) queue[0];
    }
    
    //移除指定元素，在removeAt()方法中，每次移除指定的元素后都会对队列中的元素进行重新排序。
    public boolean remove(Object o) {
        int i = indexOf(o);
        if (i == -1)
            return false;
        else {
            removeAt(i);
            return true;
       }
    }
    
    
    
    







