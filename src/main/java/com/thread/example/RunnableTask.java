package com.thread.example;

import java.time.LocalDateTime;

class RunnableTask implements Runnable {
	private String name;

	public RunnableTask(String name) {
		this.name = name;
	}

	public void run() {
		try {
			System.out.println("Task [" + name + "] executed on : " + LocalDateTime.now().toString());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}