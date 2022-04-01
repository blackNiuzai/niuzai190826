#### java.nio
java nio包定义了各种与buffer相关的类
java.nio.channel包，包含与Channel和Selector相关的类
java.nio.charset包，与字符集相关的类


#### 三大核心组件
channel、buffer、selector


传统的IO面向流，每次可以从流中读取一个或多个字节，只能向后读取不能向前读取
NIO是面向缓冲区的，把数据读取到缓冲区中，可以在缓冲区中向前/向后移动

在NIO中，所有的数组都需要通过channel传输，通道可以直接将一块数据映射到内存中
，channel是双向的，不仅可以读取数据还能保存数据


IO流是线程阻塞的，在调用read、write读写数据时候，线程阻塞，直到读取完毕或者数据完全写入

NIO不是线程阻塞的，当线程从channel中读取数据时，如果通道中没有可用的数据，线程不阻塞，可以做其他任务

##Buffer
buffer缓冲区实际上是一个组数，把数组的内容与信息包装成buffer对象， 它提供了一组访问这些信息的方法

缓冲区的重要属性：
capacity容量，是指缓冲区可以存储多少个数据，创建时指定大小不能修改
position当前位置，缓冲区写入、读取的位置。范围是0 ~ capacity-1
limit上限，指第一个不能被读出或写入的位置
mark标记，设置一个标记位置

###NIO中得到关键buffer
ByteBuffer， CharBuffer， DoubleBuffer
FloatBuffer， IntBuffer， LongBuffer， 
ShortBuffer 这些buffer覆盖了能够通过I/O发送的所有基本类型

