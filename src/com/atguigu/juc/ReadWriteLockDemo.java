package com.atguigu.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyQueue
{
	
	
	private Object obj;

	//创建读写锁
	ReadWriteLock rw = new ReentrantReadWriteLock();
	
	public void readObj()
	{
		//上读锁
		rw.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"读到的内容是："+obj);
		} finally {
			// 解读锁
			rw.readLock().unlock();
		}
	}
	
	public void writeObj(Object obj)
	{
		//上写锁
		rw.writeLock().lock();
		try {
			this.obj = obj;
			System.out.println(Thread.currentThread().getName()+"写的内容是："+obj);
		} finally {
			//解写锁
			rw.writeLock().unlock();
		}
	}
	
}

/**
 * 
 * @Description: 一个线程写入,100个线程读取
 * 
 */
public class ReadWriteLockDemo
{
	public static void main(String[] args) throws InterruptedException
	{
		//创建资源对象
		MyQueue mq = new MyQueue();
		//一个线程写
		new Thread(()->{
			//写入内容
			mq.writeObj("拷贝小括号，写死右箭头，落地大括号！");
		}, "AA").start();
		
		//100个线程读
		for (int i = 1; i <= 100; i++) {
			new Thread(()->{
				//读内容
				mq.readObj();
			}, String.valueOf(i)).start();
		}
	}
}
