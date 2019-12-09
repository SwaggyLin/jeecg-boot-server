package org.jeecg.business.study;

import java.util.concurrent.*;

/**
 * @Description
 * @Author linminfeng
 * @Date 2019-11-28 17:31
 */
public class ThreadTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        for (int i = 0; i < 10; i++) {
//            Thread t = new Thread(new TestThread());
//            t.start();
//        }
//        Callable<String> callable  =new CallableThread();
//        FutureTask<String> futureTask=new FutureTask<>(callable);
//        Thread mThread=new Thread(futureTask);
//        Thread mThread2=new Thread(futureTask);
//        Thread mThread3=new Thread(futureTask);
//        mThread.setName("线程1");
//        mThread2.setName("线程2");
//        mThread3.setName("线程3");
//        mThread3.start();
//        mThread2.start();
//        mThread.start();
//        System.out.println(futureTask.get());
        //ExecutorService ex= Executors.newFixedThreadPool(10);
        ExecutorService ex= new ThreadPoolExecutor(10, 10,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue(10));
        for(int i=0;i<10;i++) {
            ex.submit(new TestThread());
        }
        ex.shutdown();
    }
}
