# Collection集合

## 集合的概述

- 数组其实是一个集合。集合实际上是一个容器。

## 集合不能直接存储基本数据类型，而且集合也不能直接存储java对象，集合当中存储的都是java对象的内存地址（也就是说存储的是引用）

- list.add(100)//自动装箱Integer，实际上存储的是堆中值为100这个对象的引用

## java中的集合类底层都有对应的数据结构

## 集合的继承结构

- java中的集合分为两大类
  - 以单个元素的方式存储，那么超级接口就是collection
  - ![image-20211214140104629](C:\Users\15639\AppData\Roaming\Typora\typora-user-images\image-20211214140104629.png)
  - 以键值对的方式存储，那么超级接口就是Map
  - ![image-20211214140118104](C:\Users\15639\AppData\Roaming\Typora\typora-user-images\image-20211214140118104.png)
- 往Set里面放值，实际上是将Set的值存储在Map的key部分
- 集合类型在没有使用泛型之前，里面存储的元素类型是Object，使用了泛型之后，就只能存储对应的类型了，再次强调，集合中存储的是java对象的引用

## Collection中的常用方法

- 

  ```java
  collection.add()
  collection.size()
  collection.clear()
  collection.contains()
  collection.remove()
  collection.isEmpty()
  collection.toArray()
  collection.iterator()
  ```

  

- Iterator中的方法

  - 
    
    ```java
    - - iterator.hasNext()
      - iterator.next()
    
    
    ```
    
    ![image-20211214154814255](C:\Users\15639\AppData\Roaming\Typora\typora-user-images\image-20211214154814255.png)

- contains方法

  - 
    
    ```java
    c.contains(o)
    ```
    
    
    
  - 底层的实现其实是

    ```java
    o.equlas(c[i])
    ```

    

  - 所以放在集合中的元素需要重写equlas方法，因为Object的equlas方法比较的是地址而不是内容

  - 同理remove方法也是会调用equlas方法

## 当集合的结构发生变化时，需要重新获取迭代器，不然便利集合时就会抛出如下的异常，所以迭代过程中不能是用Collection中的remove方法删除元素

- 

  ```java
  Collection c=new ArrayList();
          c.add(1);
          c.add(2);
          c.add(3);
          Iterator it=c.iterator();
          while (it.hasNext()){
              c.remove(it.next());
          }
  java.util.ConcurrentModificationException
  ```

  

- 解决方案

  - iterator中提供的remove方法会删除迭代器当前指向的元素

  - 

    ```java
    Collection c=new ArrayList();
            c.add(1);
            c.add(2);
            c.add(3);
            Iterator it=c.iterator();
            while (it.hasNext()){
                it.next();//没有这句的话，那么it指向的位置就没有任何意义，所以还是会报错
                it.remove();
            }
    ```

    

  - 原理

    - 获取的迭代对象相当于对集合进行了一个快照，而且每次进行迭代的时候都会参考这个快照，如果直接通过集合删除的话，那么快照就没有更新，导致集合的快照和集合本身的不一致。而通过迭代器来删除的话，就会同时更新这两个

  

# List集合

## ArrayList

### 构造方式

- 

  ```java
  public ArrayList()
  ```

  - 初始容量是10（底层先创建了一个长度为0的Object[]数组，当添加一个元素的时候，初始化容量才为10）

  - size方法获取的是当前集合中元素的个数，不是集合的容量

- 

  ```java
  public ArrayList(int capacity)
  ```

  - 指定集合的容量

- 

  ```java
  public ArrayList(Collection c)
  ```

  - 可以用来将其他的集合类型转换为ArrayList

### 扩容的原理

- 每次扩容为原来的1.5倍，尽可能少的使用扩容机制，数组的扩容效率较低，建议在使用之前就预计元素的个数（这是一种比较好的扩容策略）

- 数组的优缺点
  - 检索的效率高
    - 每个元素占用的空间大小相同，内存地址是连续的，知道了首元素的地址，知道下标，根据数学表达式就可以很快的知道元素的内存地址，所以检索效率最高
  - 随机增删的效率低，末尾添加元素的效率还是高
  - 无法存储较大的数据，因为很难寻找到一块很大并且连续的内存空间

## LinkedList

### 构造方法

```java
 public LinkedList()
```



```java
public linkedList(Collection c)
```

### 链表的优缺点

- 随机删除元素的效率较高，因为不设计大量元素的位移
- 查询效率低，只能一个一个的比较

# 集合的工具类Collections（注意一下里面提供的方法）

# 泛型

## 判断一个类或者接口或者方法支不支持泛型，看它在定义的时候有没有加<>

## 泛型在编译期间起作用，是给编译器参考的，运行阶段泛型就没用了

## jdk8之后的砖石新特性就是定义泛型的时候后面的<>可以不用添加类型，jdk可以通过前面<>中的类型推断后面的类型

## 自定义泛型

- 

  ```java
  Class MyGeneric<T>(){
  	public T getValue(){
  	
  	}
     
  }
  E:Element
  T:Type
  ```

  

# foreach

## 语法

- 

  ```
  - for（元素类型 变量名:数组或者集合对象){
  - }
  ```

  

# Map，再次强调存放的是引用

## 常用的方法

- 

  ```java
  map.put(key,value)
  map.get(key)
  map.clear()
  map.containsKey/Value(key/value)
  map.size()
  map.keySet()//返回所有的key
  map.remove(key)
  map.values()//将map集合中的所有值用一个Collection来返回
  map.isEmpty()    
  ```

  

- 这里的contains方法底层还是调用的equlas来实现的，所以自定义方法的时候还是需要重写equlas方法

## 如何遍历Map

- 方式一

  - ```java
    Set key=map.keySet();
    Iterator it=key.iterator();
    while(it.hasNext()){
    	map.get(it.next())
    }
    ```

    

- 方式二，效率更高，只用操作一个对象，上面的需要同时操作map和set，而此处只需要entry

  - ```java
    Map<Integer,String> map=new HashMap<>();
            map.put(1,"adb");
            map.put(2,"def");
            map.put(3,"ghk");
            Set<Map.Entry<Integer,String>> set=map.entrySet();
            for (Map.Entry<Integer,String> entry:set
                 ) {
                System.out.println(entry.getKey()+"   "+entry.getValue());
            }
    ```


## 哈希表/散列表

- 一维数组，这是个数组中的每一个元素都是一个单向链表

## HashMap的底层实现

- 一维数组

  - Node<K,V> [] table

  - ```java
    static class Node<K,V>{
    	final int hash;//哈希值，是key执行hashCode方法的结果
    	final K key;
    	V value;
    	Node<K,V> next
    }
    ```

- ![image-20211219172351842](C:\Users\15639\AppData\Roaming\Typora\typora-user-images\image-20211219172351842.png)
- map.put(k,v)方法的实现原理
  - 先将k，v封装到Node对象中
  - 底层会调用k的hashCode方法获得hash值，（Object的hashCode返回的是对象在内存中的地址）然后通过hash算法，将hash值转换为数组的下标，
    - 如果下标对应的位置没有任何的数据，那么它将作为头节点
    - 如果下标对应的位置存在元素，那么就进行对k做equals
      - 如果所有的节点的k和比较的元素都不一样，那么就把新添加的元素放在末尾
      - 如果有一个节点的k和比较的元素相同，那么该节点的v就会被比较节点的v代替
- 所以放在HashMap的Key部分的元素需要重写equals方法，因为Object的equals比较的是内存地址，hashCode也需要重写

## HashMap的Key特点

- 无序：因为存储时需要依赖hash值和算法，导致存储的位置不是固定的
- 不可重复：如果key的值相同，那么新的Value会替代旧的Value
- 因为HashSet底层的实现时HashMap，而且值是存储在HashMap的key部分，所以也需要同时重写hashCode和equlas方法

## HashMap集合的默认初始化容量是16，默认加载因子为0.75，也就是说，当数组的容量到达75%的时候就会自动扩容，HashMap集合初始化容量必须是2的倍数，官方推荐（和人家的算法有关，这样的数据可以最大限度的保证效率）

## hashCode方法重写的要求

- 重写了equals的一定需要重写hashCode方法
- equals方法如果返回的是true，那么hashCode的值也是一样的
- 目前来说你还没有写这个hashCode的资本，所以建议不用自己定义的类来充当键

## 总结

- 存储在HashMap和HashSet的元素都是需要重写equals和hashCode方法的

- jdk8之后，如果哈希表底层的单向链表存储的节点超过8，那么就会变成红黑树，如果节点的个数小于6，就重新变成单向链表了

- 扩容2倍

- HashMap的key允许为null，效果和普通的key一样，Hashtable的key和value不能为null

- 按住Alt，鼠标下拉选中多行可以实现多行的编辑

- properties

  - 线程安全的

  - key和value都是String类型的

  - ```
    setProperty(key,value)
    getProperty(key)
    ```

    

# TreeMap

##  TreeSet的排序原理

- ```java
  TreeSet.add(key)
  ```

  - 该方法底层会调用TreeMap的put方法，在排序的过程中，会用到比较器，比较器是接口Comparable的一个抽象方法，其中定义了比较的规则，所以放进去的值所属的类没有实现Comparable接口的话，就会导致异常

  - ```java
    package org.example;
    
    import java.util.Set;
    import java.util.TreeSet;
    
    public class MyTreeSet {
        public static void main(String[] args) {
            User user1=new User(20);
            User user2=new User(10);
            User user3=new User(30);
            Set<User> ts=new TreeSet<>();
            ts.add(user1);
            ts.add(user2);
            ts.add(user3);
            for (User s:ts
                 ) {
                System.out.println(s);
            }
        }
    }
    class User implements Comparable<User>{
        private Integer age;
        public User(Integer age){
            this.age=age;
        }
        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    '}';
        }
    
        @Override
        public int compareTo(User o) {
            return this.age-o.age;
        }
    }
    
    ```

- 如果比较的是字符串的话，那就直接调用String里面的compareTo就行了

- TreeSet和TreeMap的遍历是中序遍历

## 还可以实现一个比较器，然后在初始化的时候传进来

- ```java
  package org.example;
  
  import java.util.Comparator;
  import java.util.Set;
  import java.util.TreeSet;
  
  public class MyTreeSet {
      public static void main(String[] args) {
          User user1=new User(20);
          User user2=new User(10);
          User user3=new User(30);
          Set<User> ts=new TreeSet<>(new Comparator<User>() {//用MyComparator也行，如果比较规则多变就用比较器，否则实现Comparable接口较好
              @Override
              public int compare(User o1, User o2) {
                  return o1.age-o2.age;
              }
          });
          ts.add(user1);
          ts.add(user2);
          ts.add(user3);
          for (User s:ts
               ) {
              System.out.println(s);
          }
      }
  }
  class MyComparator<User> implement Comparator{
      public int compare(User u1,User u2){
          return u1-u2;
      }
  }
  class User {
      Integer age;
      public User(Integer age){
          this.age=age;
      }
      @Override
      public String toString() {
          return "User{" +
                  "age=" + age +
                  '}';
      }
  
  /*    @Override
      public int compareTo(User o) {
          return this.age-o.age;
      }*/
  }
  
  ```

# 结合工具类Collections

## 常用方法

```java
collections.synchronizedList(List list)//将非线程安全的转化为线程安全
collections.sort(List,[Comparator])//参数可以是一个实现Comparable的对象，或者在传递一个Comparator比较器
new ArrayList(S)
```

