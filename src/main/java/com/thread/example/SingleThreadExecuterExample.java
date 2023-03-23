package com.thread.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

//SingleThreadExecutor has only one thread. It executes the tasks in a sequential manner. 
//If a thread dies due to an exception while executing the task, a new thread is created to replace the old thread.
public class SingleThreadExecuterExample {
	public static void main(String[] args) {

		ExecutorService runnableExecutor = Executors.newSingleThreadExecutor();
		for (int number = 0; number < 4; number++) {
			Runnable worker = (Runnable) new RunnableTask("RunnableTask " + number);
			runnableExecutor.execute(worker);
		}
		runnableExecutor.shutdown();
		ExecutorService callableExecutor = Executors.newSingleThreadExecutor();
		try {
			for (int number = 0; number < 4; number++) {
				Future<String> future = runnableExecutor.submit(new CallableTask("CallableTask " + number));
				System.out.println(future.get(100, TimeUnit.SECONDS));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		callableExecutor.shutdown();
	}
}
