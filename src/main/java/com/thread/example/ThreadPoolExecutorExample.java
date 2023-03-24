package com.thread.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorExample {
	public static void main(String[] args) {
		int corePoolSize = 2;
		int maxPoolSize = 4;
		int keepAliveTime = 10;
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(50);
		for (int number = 0; number < 50; number++) {
			queue.add(new RunnableTask("RunnableTask :: " + number));
		}
		System.out.println(queue.size());
		ThreadPoolExecutor runnableExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime,
				TimeUnit.SECONDS, queue);
		try {

			for (int number = 0; number < 50; number++) {
				Runnable task = queue.poll();
				if (task != null) {
					Future<String> future = (Future<String>) runnableExecutor.submit(task);
					System.out.println(future.get(1000, TimeUnit.SECONDS));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		runnableExecutor.shutdown();
	}
}
