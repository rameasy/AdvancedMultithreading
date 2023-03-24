package com.thread.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

//There is no limit in creating the threads.
//Mainly used for short-lived tasks.
//If a thread remains idle for close to sixty seconds, it is terminated and removed from the cache.
public class CachedThreadPoolExecutorExample {
	public static void main(String[] args) {

		ExecutorService callableExecutor = Executors.newCachedThreadPool();
		try {
			for (int number = 0; number < 4; number++) {
				Future<String> future = callableExecutor.submit(new CallableTask("CallableTask :: " + number));
				System.out.println(future.get(1000, TimeUnit.SECONDS));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		callableExecutor.shutdown();
	}
}
