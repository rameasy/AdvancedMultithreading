package com.thread.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//Schedules the task, like run them at regular interval or run them after a given delay
// 1. scheduleAtFixedRate
// 2. scheduleWithFixedDelay
public class ScheduledExecutorExample {
	public static void main(String[] args) throws InterruptedException {

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		for (int number = 0; number < 3; number++) {
			Runnable worker = new RunnableTask("Runnable Task with schedule ::" + number);
			executor.schedule(worker, 5, TimeUnit.SECONDS);
		}

		for (int number = 5; number < 8; number++) {
			Runnable worker = new RunnableTask("Runnable Task with schedule at fixed rate :: " + number);
			executor.scheduleAtFixedRate(worker, 0, 1, TimeUnit.SECONDS);
		}

		for (int number = 10; number < 14; number++) {
			Runnable worker = new RunnableTask("Runnable Task with schedule at fixed delay :: " + number);
			executor.scheduleWithFixedDelay(worker, 0, 1, TimeUnit.SECONDS);
		}

		// This method blocks until all tasks have completed execution.
		executor.awaitTermination(20, TimeUnit.SECONDS);

		executor.shutdown();
	}
}
