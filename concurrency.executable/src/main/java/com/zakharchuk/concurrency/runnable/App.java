package com.zakharchuk.concurrency.runnable;

import java.util.concurrent.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author zakharchuk
 * 
 * Following class shows basics for using ExecutorService class from java.util.concurrent
 * package. 
 * future get will be executed when Callable is ready to return value
 * 
 * */
public class App 
{
	// To set logger configuration define CLASSPATH to log4j2.xml file
	// Eclipse: Run As -> Run Configuration -> Classpath -> Advanced -> Add Folders
	public static final Logger logger = LogManager.getLogger();
	static final int FIVE = 5;
	
    public static void main( String[] args )
    {
    	ExecutorService executor = Executors.newFixedThreadPool(1);
    	Future<Integer> future = executor.submit(new Callable<Integer>() {
			public Integer call() throws Exception {
				int counter = 0;
				while (counter != FIVE) {
					TimeUnit.SECONDS.sleep(1);
					System.out.println("Waiting: " + (counter + 1) + "(" + FIVE + ")");
					counter++;
				}
				return counter;
			}
    	});
    	try {
			System.out.println("Waited: " + future.get());
		} catch (InterruptedException e) {
			logger.error(e);
		} catch (ExecutionException e) {
			logger.error(e);
		}
    }
}
