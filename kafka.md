#### kafka高可用
为了更好的负载均衡，Kafka尽量将所有的partition均匀分配到整个集群上。
一个典型的部署方式是一个topic的partition数量大于broker数量。
同时为了提高kafka的容错能力，也需要将同一个partition的replica尽量分散到不同的机器上

kafka分配replica算法：
1、将所有的broker（假设公n个broker）和待分配的partition排序
2、将第i个partition分配到第（i mod n）个broker上
3、将第i个partition的第j个replica分配到第（（i+j） mod n） 个broker


####kafka的exactly once
kafka为在<topic, partition>维度为每一条消息设置一个sequence Number 
如果消息的序号比broker维护的大一以上，说明中间有数据尚未写入，也即乱序
如果消息的序号小于等于broker维护的序号，那么认为是重复消息，broker会直接丢弃
以上两种情况都会抛异常，编码者要自己决定怎么做


####消息无丢失小结
producer acks=-1  即等
isr副本数，最少同步副本数大于1（因为包含了leader副本）
默认副本数大于1
自动提交 false 改为手动提
常见： 副本数=3 isr=2  acks=all 基本就能保证消息可靠性

#### rebalance相关
session.timeout.ms 设置了超时时间
heartbeat.interval.ms 心跳时间间隔
max.poll.interval.ms 每次消费的处理时间
max.poll.records 每次消费的消息数



####kafka高性能架构之道

######常用数据复制及一致性方案
读写分离即为典型的master-slave方案
同步复制可保证强一致性性但会影响可用性
异步复制可提高高可用性但会降低一直性

kafka基于ISR的数据复制方案，既不是完全的同步复制也不是完全的异步复制

######顺序写磁盘
partition相当于是一个非常长的数组，broker接收到的消息顺序写入大数组中，
consumer通过OffSet顺序消费这些数据 ，并且不删除已经消费的数据，避免了随机写磁盘
删除的时候也是segment维度整个删除

######page cache
broker接收到数据时。只是将数据写入page cache，并不保证数据一定完全写入磁盘
如果消费速度相当，则交换数据的过程只需要发生在page cache

######send file技术
少了cpu拷贝，直接在内核完成read buffer到nic buffer

######批处理减少网络开销
batch.size 和linger.ms参数实现批量发送


### kafka和rabbitMQ
rabbitMQ节点之间互相分布式存储队列的master 和 mirror
但是消费和生产只能通过master进行，当服务连接到mirror机器，也会路由到master先操作，再同步到mirror机器
rabbitMQ的mirror队列只做镜像作为容灾使用，不承担客户端的读写压力

rabbitMQ不承诺消息的顺序性，因此可以多线程并发处理

rabbitMQ master queue单节点导致性能瓶颈，吞吐量受限


### consumer 和 partition数量
consumer比partition多的时候，有部分consumer享受不到，这也是为了保证一个顺序性


### kafka的架构
主要包含四个API组件：
ProducerApi ConsumerApi StreamsApi ConnectApi 
producer 生产者
consumer 消费者
broker kafka服务节点
topic 队列，主题
partition 同一个topic的数据可以在多分区
offset 消息标识
replication partition的副本


### kafka顺序如何保证
1、 首先在推送消息时，我们可以通过重写路由方法，设置为消息设置一个partition key
   这样可以保证想要顺序的消费的消息被投递到同一个partition

2、 但是这样避免不了重试带来的问题， producer会失败重试，可以通过开启producer的幂等配置，
    这样子在消息中维护一个序号，broker只会接受递增的序号

3、消费端可以选择单线程消费模式，这样整体就是顺序的，但是一般我们会选择多线程消费去压榨机器的性能
   这样的话就需要在内存也维护队列，将想要同步消费的消息交由同一队列进行处理

### zookeeper在kafka中的作用
broker的注册、 topic的注册、 生产者的负载均衡，消费者监听partition以便能过够rebalance
记录消费offset、 消费者组和消费者的注册


### sendfile技术和mmap技术
传统IO实现文件传输需要四次拷贝 两次cpu拷贝，两次dma拷贝
两次cpu拷贝： 把内核缓冲区数据拷贝到用户缓冲区，再从用户缓冲区拷贝到socket缓冲区
两次dma拷贝： 从磁盘拷贝到内核缓冲区，从内核缓冲区拷贝到网卡缓冲区
sendfile技术去除了两次cpu拷贝 所以应该记作 零cpu拷贝

mmap技术中是先基于dma拷贝将磁盘文件拷贝到内核缓冲区，而用户缓冲区和内核缓冲区共享了，因此不需要再单独拷贝到用户缓冲区去操作，
此时可基于cpu拷贝直接将共享缓冲区中的内容拷贝到socket缓冲区


### kafka可靠性？
在 Kafka 0.8.2 版本中，引入了这个 acks 参数。
在之前的版本中，Kafka 采用了基于“Producer”和“Broker”之间的异步传输模式，无法保证消息发送的可靠性。

#### 内部主题
kafka内部主题 offset topic 记录每个partition的offset
每个partition由它的partitionID，消息offset是自增，所能够定位要下一个消息


### 记住
一个partition只能被同一个consumer group中的一个consumer消费


#### 事务
kafka中的事务，确保在一个事务中发送多条消息，要么都成功，要么都失败，
也就是保证对多个分区的写入操作的原子性
原理是两阶段提交， 有选举出的事务协调者broker，根据producer指定的事务id来关联这些事务消息

rabbitMQ的事务是在本地开辟一个和channel缓冲区，事务提交后才会将消息发送到broker处理，
也可以保证多条消息的同时成功和失败







    





















