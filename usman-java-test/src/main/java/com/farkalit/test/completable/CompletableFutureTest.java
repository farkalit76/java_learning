package com.farkalit.test.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureTest {
	
	public static void main(String[] args) {
		
		System.out.println("start completable future executor...");
		ExecutorService service = Executors.newFixedThreadPool(10);
		
		for (int i = 0; i < 5; i++) {
			System.out.println("\nOrder number :"+i);
			
			try {
				//CompletableFuture<String> apply = 
				CompletableFuture<String> apply = CompletableFuture.supplyAsync(() -> {
					try {
						return getOrder();
					} catch (Exception e) {
						//e.printStackTrace();
						System.err.println("Error message:"+e.getMessage());
					}
					//return "dummy null order ";
					return new FailedOrder().getOrder();
				}, service)
						//.exceptionally(order -> new FailedOrder().getOrder())
					.thenApply(order -> enrichOrder(order))
					.thenApply(order -> performPayment(order))
					.thenApply(order -> dispatch(order))
					.thenApply(order -> sendMail(order));
					//.thenAccept(order -> sendMail(order));
				System.out.println("Response:"+apply.get());
			} catch ( InterruptedException | ExecutionException e) {
				System.err.println("Error"+e.getMessage());
			}
		}
		
	}
	
	public static String getOrder() throws Exception {
		try {
			String order = "mac-laptop:199000";
			System.out.println("Order started..."+order);
			int a = 0;
			int result =5/a;
			return order;	
		} catch (Exception e) {
			throw new Exception("error caught");
		}
		
	}
	
	public static String enrichOrder(String order) {
		//String order = "mac-laptop:199000";
		System.out.println("Enrich Order for..."+order);
		return order;
	}
	
	public static String performPayment(String order) {
		//String order = "mac-laptop:199000";
		System.out.println("Perform payment for..."+order);
		return order;
	}
	
	public static String dispatch(String order) {
		//String order = "mac-laptop:199000";
		System.out.println("Dispatch order..."+order);
		return order;
	}
	
	public static String sendMail(String order) {
		//String order = "mac-laptop:199000";
		System.out.println("Mail send for..."+order);
		return order;
	}

}
class FailedOrder {

	public String getOrder() {
		return " default order...mamamama";
	}
	
}