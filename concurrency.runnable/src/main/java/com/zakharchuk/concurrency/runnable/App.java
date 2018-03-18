package com.zakharchuk.concurrency.runnable;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
	public static final Logger logger = LogManager.getLogger();
	static final int FIVE = 5;
	
    public static void main( String[] args )
    {
    	Runnable runnable = new Runnable() {
			public void run() {
				String name = Thread.currentThread().getName();
				System.out.println(name + " is running now");
				try {
					int counter = 0;
					while (counter != FIVE) {
						TimeUnit.SECONDS.sleep(1);
						System.out.println("Time count: " + (counter + 1) + "(" + FIVE + ")");
						counter++;
					}
				} catch (InterruptedException e) {
					logger.error("Interrupted: ", e);
				}
			}
		};
		// start runnable in current thread
		runnable.run();
		
		Thread additionalThread = new Thread(runnable);
		additionalThread.start();
    }
}
