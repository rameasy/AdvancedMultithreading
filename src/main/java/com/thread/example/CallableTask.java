package com.thread.example;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

public class CallableTask implements Callable<String> {

	private final String name;

	public CallableTask(String name) {
		this.name = name;
	}

	public String call() throws Exception {
		System.out.println("Task [" + name + "] executed on : " + LocalDateTime.now().toString());
		Thread.sleep(1000);
		return name;
	}
}
