#### java spi 

1、rt.jar是java的基础类库， java.time, java.math, java.nio, java.sql , java.math 都在这个jar包中
2、spi 全称：service provider interface 服务提供者接口
3、如jdbc， 不同的数据库驱动都是事先rt.jar里面java.sql.Driver预先定义的接口，而具体的实现由不同数据库的jar包里自己实现


#### dubbo spi
1、java spi配置文件里是实现类的全类名，一旦调用加载 就会实例化全部对象
2、dubbo spi 配置文件是key：value形式，其中value是实现类全类名，key是dubbo提供的的一些配置项，这样就可以根据配置加载不同的实现
