package com.xjx.springboottest.cachedemo;
  
/**  
 * 主测试类
 * @author ZHANGQI947  
 */  
public class MainTest {  
  
    /**  
     * @param args  
     * @throws InterruptedException   
     */  
    public static void main(String[] args) throws InterruptedException {  
        // 获取缓存池  
        CachePool cachePool = CachePool.getInstance();  

        //定义学生对象
        Student stu1 = new Student("l1", "stu001", 25, 40);  
        Student stu2 = new Student("l2", "stu002", 25, 40);  
        Student stu3 = new Student("l3", "stu003", 25, 40);  
        Student stu4 = new Student("l4", "stu004", 25, 40);  

        //存入缓存  缓存名称 缓存实例对象  缓存时间 毫秒
        cachePool.putCacheItem("001", stu1, 1000);
        cachePool.putCacheItem("002", stu2, 6000);
        cachePool.putCacheItem("003", stu3, 60000);
        cachePool.putCacheItem("004", stu4, 1800000);
        
        // 设置线程休眠，其中超过设置缓存时间对象会超时
        Thread.sleep(2000);

        //获取缓存示例
        Student stu001 = (Student) cachePool.getCacheItem("001");
        //判断 不为空 就打印
        if (null != stu001) {
            //设置 1000 线程休眠 2000 缓存失效 stu001 为 null 不打印
            System.out.println(stu001.getName());  
        }  
        
        // ，这里取出的002
        Student stu002 = (Student) cachePool.getCacheItem("002");

        //002 缓存时间 6000  线程休眠 2000 取出 不为空 打印

        if (null != stu002) {  
            System.out.println(stu002.getName());  
        }  
        
        // 获取打印缓存池中对象数量
        int cacheSize = cachePool.getSize();  
        System.out.println("缓存池种的对象数量1:" + cacheSize);
        
        // 删除对象002
        cachePool.removeCacheItem("001");
        
        // 打印缓存池数量
        cacheSize = cachePool.getSize();
        System.out.println("缓存池种的对象数量2:" + cacheSize);
    }  
  
}  