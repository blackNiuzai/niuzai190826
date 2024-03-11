#### Spring AOP

两种实现方式
1、jdk动态代理
2、cglib动态代理

#####区别

JDK动态代理要求目标实现接口，目标没有接口的时候使用CGLIB动态代理
JDK动态代理是自带的，CGLIB需要引入第三方包
CGLIB动态代理，是在内存中构建一个子类对象从而时间功能拓展，所以无法对final；类，private方法和static方法实现代理

策略：目标有实现类接口时候，使用JDK动态代理，目标没有实现类接口，采用CGLIB

###autoWired
默认byType，如果多个相同类型的bean 就byName， 如果type 和name 都无法定位到唯一 就会报错；
@resource 默认byName 找不到就退化为byType


###spring实例化bean
class->扫描创建beanDefinition(scope,isLazy,dependsOn,beanClass,init-method)放到beanDefinitionMap(是一个concurrentHashMap)
   
 
#### spring三级缓存
当一个bean初始化时候，它并不知道自己的代理对象会不会被循环依赖，所以创建工厂对象到第第三级缓存
如果有被循环依赖，那么就可以使用这个工厂对象创建出代理对象，如果没有被循环依赖，就可以在bean创建的最后再进行动态代理


### spring bean的生命周期
解析beanDefinitionMap：
spring启动时看，会扫描component注解等将bean的定义信息放到beanDefinitionMap
实例化：
根据beanDefinition中的类的类名 构造方法等使用反射机制实例化类
设置对象属性：
spring会继续使用反射技术来设置bean实例的属性
初始化bean：
会根据实bean实现的接口做一些bean的前置后置处理等
容器化：
将bean放到单例池中

这些Aware有什么用， 
比如SpringContextAware可以帮我们注入单例容器，例如springUtils.getBean();
再比如： BeanFactoryAware帮我们注入BeanFactory对象，BeanFactory可以用于动态注册bean;



postConstruct是注解执行在构造函数执行之后，是对bean进行初始化操作的方法
它标注的方法会在依赖注入后完成
但是在beanPostProcessor之前，beanPostProcessor是用于对bean的增强处理等



#### FactoryBean
spring实例化的策略有两种： 1、通过反射 2、通过工厂方法（factoryBean既可以是反射也可以是工厂）
但是反射这种方式对interface是没用的，mybatis我们可以只定义interface并为加了mapper注解的接口，
是因为它底层使用了factoryBean去创建实例，并生成动态代理（即mapper接口的实现类单例）


####springMVC
一般mvc的web项目有一个webapp文件夹，下面有个web.xml, tomcat在启动的时候会读取web.xml
web.xml会定义servlet对象（具体就是dispatchServlet）
tomcat启动时会调用dispatchServlet的初始化方法，会创建spring容器  （注意：如果在web.xml定义两个dispatchServlet， 那项目启动会有两套容器）
创建完spring容器后会发布事件，事件会触发InitStrategies（里面会触发handlerMapping之类的）


### 为什么bean容器需要concurrentHashMap
因为spring允许我们自定义bean，而此时如果用了多线程是会有并发问题的，因此spring在底层用了concurrentHashMap防止了并发创建
https://stackoverflow.com/questions/49131471/why-bean-definitions-is-stored-in-concurrent-hashmap











