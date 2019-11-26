package com.atguigu.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket {
	private int number = 30;

	// 创建锁
	Lock lock = new ReentrantLock();

	public void sale() {
		// 上锁
		lock.lock();
		try {
			if (number > 0) {
				System.out.println(Thread.currentThread().getName() + "卖出第：\t" + (number--) + "\t 还剩下：" + number);
			}

		} finally {
			// 解锁
			lock.unlock();
		}
	}
	// // 1.同步方法
	// public synchronized void sale() {
	// // 2.同步代码块
	// synchronized (this) {
	// }
	// if (number > 0) {
	// System.out.println(Thread.currentThread().getName() + "卖出第：\t" + (number--) +
	// "\t 还剩下：" + number);
	// }
	// }

}

/**
 * 
 * @Description:卖票程序复习线程知识 , 三个售票员 卖出 30张票
 * 
 */
public class SaleTicket {
	public static void main(String[] args) {
		// 创建资源对象
		Ticket tk = new Ticket();
		// AA线程卖票
		// new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		// for (int i = 0; i < 40; i++) {
		// // 卖票
		// tk.sale();
		// }
		// }
		// }, "AA").start();
		//
		// // BB线程卖票
		// new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		// for (int i = 0; i < 40; i++) {
		// // 卖票
		// tk.sale();
		// }
		// }
		// }, "BB").start();
		//
		// // CC线程卖票
		// new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		// for (int i = 0; i < 40; i++) {
		// // 卖票
		// tk.sale();
		// }
		// }
		// }, "CC").start();

		// 使用Lambda表达式实现
		// 口诀：拷贝小括号，写死右箭头，落地大括号
		new Thread(() -> {
			for (int i = 0; i < 40; i++)
				tk.sale();
		}, "AA").start();
		new Thread(() -> {
			for (int i = 0; i < 40; i++)
				tk.sale();
		}, "BB").start();
		new Thread(() -> {
			for (int i = 0; i < 40; i++)
				tk.sale();
		}, "CC").start();
	}
}