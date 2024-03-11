#### java spi
1、rt.jar是java的基础类库， java.time, java.math, java.nio, java.sql , java.math 都在这个jar包中
2、spi 全称：service provider interface 服务提供者接口
3、如jdbc， 不同的数据库驱动都是事先rt.jar里面java.sql.Driver预先定义的接口，而具体的实现由不同数据库的jar包里自己实现


#### dubbo spi
1、java spi配置文件里是实现类的全类名，一旦调用加载 就会实例化全部对象
2、dubbo spi 配置文件是key：value形式，其中value是实现类全类名，key是dubbo提供的的一些配置项，这样就可以根据配置加载不同的实现


####ConcurrentHashMap
get不需要加锁，用了volatile实现 线程间可见
对count作了分片处理，如果cas冲突则先存到一个cell数组里


#### java spi
springboot的动态加载就是spi机制，通过读取特定文件下，自动扫描并加载自动配置类



####java的反射原理简述
主要通过Class类来实现，获取方式：
使用Class.forName()获取某类的Class的对象
使用类.class  如String.class 
使用对象.getClass()
在类对象中属性是Field对象， 方法是Method对象，可以根据名称获取，修改属性值或者使用invoke执行方法



#### 双亲委派模型
避免重复加载，也保证核心类不会被随意替换
bootStrapClassLoader 启动类加载器
ExtClassLoader 拓展类加载器
AppClassLoader 应用类加载器


####hashMap为什么要用（与&）操作 而不用取余操作算下标
取余操作底层是很多次位运算， &就只有一次了
####为什么hashCode要用高16位和低16位异或呢
因为hashcode是int类型32位的，异或可以减少数组位置相同的概率
异或运算可以保证两个数值特性， 与运算使结果靠近0  或运算使结果靠近1


####TheadLocal
threadLocal的entry中
key是我们定义的threadLocal的弱引用，
一般来说我们定义的ThreadLocal对象会指向类中的私有静态变量（强引用）， 所以一般不会被回收
让key为弱引用，目的是当我们不再使用某个threadLocal变量，即我们将它置为null， 那key可以在下次gc被回收
不让value为弱引用，是因为value这东西一般我们在代码中不会有单独的强引用去保证它的存活，如果弱引用了，容易发生key没gc但value被gc，导致取出来是null
