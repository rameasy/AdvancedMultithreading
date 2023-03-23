package com.thread.example;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * The Class CallableExample.
 */

/*
 * Callable Interface represents an asynchronous task which can be executed by a separate thread,
 * it has one call() method, which returns a Future object.
 * @FunctionalInterface 
 * public interface Callable<V> { 
 * 	V call() throws Exception; 
 * }
 */
public class CallableExample {
	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

		ExecutorService executor = Executors.newFixedThreadPool(1);
		
		//Callable can be executed only by submti() method of ExecutorService Interface
		Future<String> future = executor.submit(new Task("Demo-Task"));
		
		//If future.get method is not invoked then exception will not be read.
		//Always invoke Future.get() with timeouts to avoid unexpected waits.
		String completedThread = future.get(100, TimeUnit.SECONDS);
		
		System.out.println("Thread completed : " + completedThread);
		
		Callable<String> stringCallable = () -> {
            Thread.sleep(1000);
            return "Anonymous object";
        };
        Future<String> futureForAnonymousObject = executor.submit(stringCallable);
        System.out.println(futureForAnonymousObject.get());
		executor.shutdown();
	}
}

class Task implements Callable<String> {
	private final String name;

	public Task(String name) {
		this.name = name;
	}

	// The call method can throw checked exceptions. 
	//These exceptions are collected in Future object which can be checked by making a call to Future.get() method. 
	public String call() throws Exception {
		System.out.println("Task [" + name + "] executed on : " + LocalDateTime.now().toString());
		//throw new Exception();
		return name;
	}
}
