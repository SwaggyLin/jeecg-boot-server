package org.jeecg.business.order.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @Description
 * @Author linminfeng
 * @Date 2019-08-14 10:59
 */
public class Main {
    /* 启动线程数 */
    private static final int THREAD_COUNT = 10;

    public static void main(String[] args) {
        /* 线程池 */
        ExecutorService ex = Executors.newFixedThreadPool(THREAD_COUNT);
        /* 计数器 */
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        List<Future<String>> tasks = new ArrayList<>();
        /* 启动 THREAD_COUNT 子线程 */
        IntStream.range(0, THREAD_COUNT).forEach(i -> tasks.add(ex.submit(() -> planThread(i, latch))));
        /* 启动监听线程 */
        new Thread(() -> timoutListener(latch, 5000, tasks)).start();
        //主线程处理其它任务......
        /* 获取多线程处理结果 */
        for (int i = 0; i < tasks.size(); i++) {
            Future<String> f = tasks.get(i);
            try {
                System.out.println("Main线程：Thread 【" + i + "】返回:" + f.get());
            } catch (Exception e) {
                System.out.println("Main线程：Thread 【" + i + "】被取消，无返回结果");
            }
        }
        ex.shutdown();
    }

    /* 任务处理线程 */
    private static String planThread(int i, CountDownLatch latch) {
        try {
            System.out.println("===线程" + i + "开始执行");
            String s = "";
            int m = 0;

            while (m < Integer.MAX_VALUE) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程被中断");
                    return null;
                }
                if (m % 10000 == 0) {
                    System.out.println(m);
                }
                s += m;
                m++;
            }
            latch.countDown();
            System.out.println("线程" + i + "执行时完成===");
            return "" + i;
        } catch (CancellationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /* 超时监听线程 */
    public static void timoutListener(CountDownLatch latch, long timeOutMillion, List<Future<String>> tasks) {
        try {
            /* 如果计数器为0（所有任务完成）或超过时间，则向下执行 */
            latch.await(timeOutMillion, TimeUnit.MILLISECONDS);
            for (int i = 0; i < tasks.size(); i++) {
                Future<String> f = tasks.get(i);
                if (!f.isDone()) {
                    System.out.println("监听线程:终止Thread 【" + i + "】");
                    f.cancel(true);
                } else {
                    System.out.println("监听线程:Thread 【" + i + "】已正常结束");
                }
            }
            System.out.println("监听线程结束！");
        } catch (InterruptedException e) {
            System.out.println("监听线程抛出中断异常");
        }
    }
}