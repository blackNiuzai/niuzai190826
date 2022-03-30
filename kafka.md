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
















