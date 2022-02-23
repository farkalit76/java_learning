package com.farkalit.test.completable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureClassTest {

public static void main(String[] args) {
		
		System.out.println("start future class executor...");
		ExecutorService service = Executors.newFixedThreadPool(10);
		
		for (int i = 0; i < 5; i++) {			
		
			try {
				//submit task and except the placeholder value
				Future<Integer> submit = service.submit(new Task());
				Integer value = submit.get();//blocking
				System.out.println("value:"+value);
			} catch ( Exception e) { //InterruptedException | ExecutionException e) {
				System.err.println("Error");
			}
		}
		
	}

}
class Task implements Callable<Integer>{

	public Integer call() {
		return new Random().nextInt();
	}
}
