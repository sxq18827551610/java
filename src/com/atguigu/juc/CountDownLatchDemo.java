package com.atguigu.juc;

import java.util.concurrent.CountDownLatch;


/**
 * 
 * @Description:
 *  *让一些线程阻塞直到另一些线程完成一系列操作后才被唤醒。
 * 
 * 
 * 解释：6个同学陆续离开教室后值班同学才可以关门。
 * 
 * main主线程必须要等前面6个线程完成全部工作后，自己才能开干 
 */
public class CountDownLatchDemo
{
	public static void main(String[] args) throws InterruptedException
	{
		CountDownLatch cd = new CountDownLatch(4);
		
		//6个同学离开教室
		for (int i = 1; i <= 8; i++) {
			new Thread(()->{
				System.out.println(Thread.currentThread().getName()+"号同学离开教室！");
				//减少计数
				cd.countDown();
			}, String.valueOf(i)).start();
		}
		//阻塞班长
		cd.await();
		//班长锁门
		System.out.println(Thread.currentThread().getName()+"班长锁门！");
	}


}
