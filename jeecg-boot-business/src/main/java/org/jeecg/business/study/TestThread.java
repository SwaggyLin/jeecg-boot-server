package org.jeecg.business.study;

/**
 * @Description
 * @Author linminfeng
 * @Date 2019-11-28 17:32
 */
public class TestThread implements Runnable{
    @Override
    public void run() {
        System.out.println("我叫" + Thread.currentThread().getName() + "，我超喜欢沉默王二的写作风格");
    }


    public static void main(String[] args) {
        TestThread testThread=new TestThread();
        Thread thread=new Thread(testThread);
        thread.start();
    }


}
