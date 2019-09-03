package org.jeecg.business.order.test;

import java.util.concurrent.*;

/**
 * @Description
 * @Author linminfeng
 * @Date 2019-08-14 11:03
 */
public class Main2 {

    // 定义线程池，推荐手动创建线程池： https://blog.csdn.net/LLLLLiSHI/article/details/88057655
    private static ExecutorService pool = Executors.newFixedThreadPool(2);

    /**
     * jdk的api：Future类已经提供满足的api
     */
    public static void main(String[] args) {
        System.out.println("主程序执行开始……");
        //定义线程1
        Callable call1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                // 设置2秒睡眠
                TimeUnit.SECONDS.sleep(2);
                return "这是线程1执行结果……";
            }
        };
        Callable call2 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                // 设置2秒睡眠
                TimeUnit.SECONDS.sleep(2);
                return "这是线程2执行结果……";
            }
        };

        // 手动控制线程
        Future result1 = pool.submit(call1);
        Future result2 = pool.submit(call2);
        try {
            // 如果在超时时间内，没有数据返回：则抛出TimeoutException异常
            Object callResult1 = result1.get(3, TimeUnit.SECONDS);
            Object callResult2 = result2.get(2, TimeUnit.SECONDS);
            System.out.println(callResult1);
            System.out.println(callResult2);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException发生");
        } catch (ExecutionException e) {
            System.out.println("ExecutionException发生");
        } catch (TimeoutException e) {
            System.out.println("TimeoutException发生，意味着线程超时报错");
        }
        System.out.println("主程序执行完成……");
    }
}