package org.jeecg.business.study;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description
 * @Author linminfeng
 * @Date 2019-11-28 17:39
 */
public class CallableThread implements Callable<String> {
    private int count = 20;
    @Override
    public String call() throws Exception {
        for (int i = count; i > 0; i--) {
            Thread.yield();
            System.out.println(Thread.currentThread().getName()+"当前票数：" + i);
        }
        return "sale out";
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<String> callable  =new CallableThread();
        FutureTask<String> futureTask=new FutureTask<>(callable);
        Thread mThread=new Thread(futureTask);
        Thread mThread2=new Thread(futureTask);
        Thread mThread3=new Thread(futureTask);
		mThread.setName("hhh");
        mThread2.start();
        mThread.start();
        mThread3.start();
        System.out.println(futureTask.get());

    }
}
