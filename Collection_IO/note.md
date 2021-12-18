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

    
