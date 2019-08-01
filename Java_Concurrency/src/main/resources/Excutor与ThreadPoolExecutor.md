>线程池的主要作用是支持高并发的访问处理，并且可以将线程对象进行复用。在线程池中
支持线程对象管理，包括创建与销毁，使用池时，只需要执行具体的任务即可，线程对象的处理都都在池中被封装了。
## Executor接口
* java.util.concurrent.Executor。与线程池有关的大多都实现了此类。
* 使用Executors工厂类创建线程池
  >大多数情况下，需要使用接口的实现类来完成指定的功能，比如ThreadPoolExecutor类就是Executor的实现类，但ThreadPoolExecutor在使用上不方便。
  在实例化的时候需要很多参数，还要考虑线程的并发数等于线程池运行效率有关的参数。所以官方建议使用Executors工厂类来创建线程池对象。
  * 使用newCachedThreadPool()方法创建无界线程池
      * 该方法创建的线程池可以进行线程自动回收。所谓无界线程池就是池中存放线程个数是理论上的Integer.MAX_VALUE最大值。
  * 使用newCachedThreadPool(ThreadFactory)定制线程工厂
      * 该方法解决无界线程池中的Thread类由程序员自己定制    
  * 使用newFixedThreadPool(int)创建有界线程池。即指定最大线程个数。
  * 有界线程池中的Thread类还可以由线程池自己定制，方法newFixedThreadPool(int i,ThreadFactory tf);
  *  使用newSingleThreadExecutor()方法创建单一线程池。单一线程池可以实现队列的方式来执行任务。
  *  使用newSingleThreadExecutor(ThreadFactory)定制线程工厂。
* ThreadPoolExecutor的使用
  * 
  