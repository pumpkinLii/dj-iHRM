package com.cms.common;

import org.springframework.stereotype.Component;

@Component
public class MyThread extends Thread{
    @Override
    public void run() {
        //定时执行任务
        MyThread myThread=new MyThread();
        try {
            //先执行一次密钥的更改
            System.out.println("--------------------------------密钥修改完成 ---------------------------------");
            TokenUtils.setsecret_key();
            Thread.sleep(1000*60*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread.start();//再次启动线程
    }
}
