#### CAP理论
C - consistentcy 一致性
A - availability 可用性
P - Partition tolerance 分区容错性

redis是CP的

#### ZAB 和 raft  都是CP的分布式一致性协议
数据同步协议：
都是两阶段提交，多数派prepare成功就全体commit
区别在于zab在重新选主后，新leader如果含有旧leader未commit的数据会继承
raft新leader对于未commit的数据直接丢弃



