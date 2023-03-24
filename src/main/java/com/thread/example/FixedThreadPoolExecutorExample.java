package com.thread.example;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//fixed number of threads. 
//The tasks submitted to this executor are executed by the specified number of threads 
//When there are more tasks than the number of threads, then those tasks will be added in a queue
public class FixedThreadPoolExecutorExample {
	public static void main(String[] args) {

		ThreadPoolExecutor callableExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		try {
			for (int number = 0; number < 4; number++) {
				System.out.println("Pool Size :: " + callableExecutor.getPoolSize());
				Future<String> future = callableExecutor.submit(new CallableTask("CallableTask :: " + number));
				System.out.println(future.get(1000, TimeUnit.SECONDS));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		callableExecutor.shutdown();
	}
}
