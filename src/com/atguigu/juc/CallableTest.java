package com.atguigu.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
//		int num = 0;
//		for (; num < 50; num++) {
//			System.out.println(Thread.currentThread().getName() + "===》" + num);
//
//		}
		Thread.sleep(5000);
		//返回200状态码
		return 200;
	}

}

public class CallableTest{
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//创建MyThread对象
		MyThread myThread = new MyThread();
		//创建未来任务对象
		FutureTask<Integer> futureTask = new FutureTask<>(myThread);
		//创建处理未来任务的线程
		new Thread(futureTask, "未来任务").start();
		//主线程结束
		System.out.println(Thread.currentThread().getName()+"结束");
		//获取未来任务处理的结果
		Integer result = futureTask.get();
		System.out.println(result);
		Integer result2 = futureTask.get();
		System.out.println(result2);
	}
}
