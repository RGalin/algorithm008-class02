学习笔记
Hash Table: 
    根据关键码值（Key value）而直接进行访问的数据结构.
    通过把关键码值映射到表中一个位置来访问记录，以加快查找的速度.
tree:
    前序（Pre-order）：根-左-右  
    中序（In-order）：左-根-右  
    后序（Post-order）：左-右-根
二叉搜索树:
    是指一棵空树或者具有下列性质的二叉树： 
    左子树上所有结点的值均小于它的根结点的值； 
    右子树上所有结点的值均大于它的根结点的值； 
    以此类推：左、右子树也分别为二叉查找树。（这就是重复性！）
Heap: 
    可以迅速找到一堆数中的最大值或者最小值的数据结构。
    将根节点最大的堆叫做大顶堆或大根堆，根节点最小的堆叫小顶堆或小根堆。
    假设是大项堆，则常见操作（API）：
        find-max:        O(1)
        delete-max:    O(logN)
        insert(create): O(logN) || O(1)
二叉堆:
    [性质一]是一颗完全树。
    [性质二]树中任意节点的值总是>=其子节点的值。
    二叉堆的实际实现可以使用数组来表示。元素的位置符合其索引规则即可。
    索引为i的左孩子的索引为2*i + 1.
    右孩子索引为2*i + 2.
    父节点索引为floor((i-1)/2).

HashMap小结：
HashMap 是基于哈希表的 Map 接口的非同步实现。此实现提供所有可选的映射操作，并允许使用 null 值和 null 键。
HashMap 的数据结构：数组和链表
存储：
/**
 * Associates the specified value with the specified key in this map.
 * If the map previously contained a mapping for the key, the old
 * value is replaced.
 *
 * @param key key with which the specified value is to be associated
 * @param value value to be associated with the specified key
 * @return the previous value associated with <tt>key</tt>, or
 *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
 *         (A <tt>null</tt> return can also indicate that the map
 *         previously associated <tt>null</tt> with <tt>key</tt>.)
 */
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}

/**
 * Implements Map.put and related methods.
 *
 * @param hash hash for key
 * @param key the key
 * @param value the value to put
 * @param onlyIfAbsent if true, don't change existing value
 * @param evict if false, the table is in creation mode.
 * @return previous value, or null if none
 */
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    else {
        Node<K,V> e; K k;
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else {
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    if (++size > threshold)
        resize();
    afterNodeInsertion(evict);
    return null;
}
在注释中首先提到了，当我们 put 的时候，如果 key 存在了，那么新的 value 会代替旧的 value，
并且如果 key 存在的情况下，该方法返回的是旧的 value，如果 key 不存在，那么返回 null。
往 HashMap 中 put 元素的时候，先根据 key 的 hashCode 重新计算 hash 值，根据 hash 值得到这个元素在数组中的位置（即下标），
如果数组该位置上已经存放有其他元素了，那么在这个位置上的元素将以链表的形式存放，新加入的放在链头，最先加入的放在链尾。
如果数组该位置上没有元素，就直接将该元素放到此数组中的该位置上。

判断当前桶是否为空，空的就需要初始化（resize 中会判断是否进行初始化）。
根据当前 key 的 hashcode 定位到具体的桶中并判断是否为空，为空表明没有 Hash 冲突就直接在当前位置创建一个新桶即可。
如果当前桶有值（ Hash 冲突），那么就要比较当前桶中的 key、key 的 hashcode 与写入的 key 是否相等，相等就赋值给 e,在第 8 步的时候会统一进行赋值及返回。
如果当前桶为红黑树，那就要按照红黑树的方式写入数据。
如果是个链表，就需要将当前的 key、value 封装成一个新节点写入到当前桶的后面（形成链表）。
接着判断当前链表的大小是否大于预设的阈值，大于时就要转换为红黑树。
如果在遍历过程中找到 key 相同时直接退出遍历。
如果 e != null 就相当于存在相同的 key,那就需要将值覆盖。
最后判断是否需要进行扩容。

读取
/**
 * Returns the value to which the specified key is mapped,
 * or {@code null} if this map contains no mapping for the key.
 *
 * <p>More formally, if this map contains a mapping from a key
 * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
 * key.equals(k))}, then this method returns {@code v}; otherwise
 * it returns {@code null}.  (There can be at most one such mapping.)
 *
 * <p>A return value of {@code null} does not <i>necessarily</i>
 * indicate that the map contains no mapping for the key; it's also
 * possible that the map explicitly maps the key to {@code null}.
 * The {@link #containsKey containsKey} operation may be used to
 * distinguish these two cases.
 *
 * @see #put(Object, Object)
 */
public V get(Object key) {
    Node<K,V> e;
    return (e = getNode(hash(key), key)) == null ? null : e.value;
}

/**
 * Implements Map.get and related methods.
 *
 * @param hash hash for key
 * @param key the key
 * @return the node, or null if none
 */
final Node<K,V> getNode(int hash, Object key) {
    Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (first = tab[(n - 1) & hash]) != null) {
        if (first.hash == hash && // always check first node
            ((k = first.key) == key || (key != null && key.equals(k))))
            return first;
        if ((e = first.next) != null) {
            if (first instanceof TreeNode)
                return ((TreeNode<K,V>)first).getTreeNode(hash, key);
            do {
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            } while ((e = e.next) != null);
        }
    }
    return null;
}
首先将 key hash 之后取得所定位的桶。
如果桶为空则直接返回 null 。
否则判断桶的第一个位置(有可能是链表、红黑树)的 key 是否为查询的 key，是就直接返回 value。
如果第一个不匹配，则判断它的下一个是红黑树还是链表。
红黑树就按照树的查找方式返回值。
不然就按照链表的方式遍历匹配返回值。
从这两个核心方法（get/put）可以看出 1.8 中对大链表做了优化，修改为红黑树之后查询效率直接提高到了 O(logn)。

