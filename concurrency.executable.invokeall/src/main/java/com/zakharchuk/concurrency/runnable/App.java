package com.zakharchuk.concurrency.runnable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author zakharchuk
 * 
 * 
 *         Following class was made to show functionality of invokeAll() method.
 *         Executor starts all Callables from executing pool and waits for all
 *         tasks to complete. Executes the given tasks, returning a list of
 *         Futures holding their status and results when all complete. Future
 *         isDone() is true for each element of the returned list. A completed
 *         task could have terminated either normally or with the exception. If
 *         some of tasks was terminated with the exception calling future.get()
 *         will cause this exception to be rethrown (ExecutionException)
 * 
 */
public class App {
	// To set logger configuration define CLASSPATH to log4j2.xml file
	// Eclipse: Run As -> Run Configuration -> Classpath -> Advanced -> Add Folders
	public static final Logger logger = LogManager.getLogger();
	static final int FIVE = 5;

	public static void main(String[] args) {
		ExecutorService executor = Executors.newWorkStealingPool();
		List<Callable<String>> callables = Arrays.asList(new Callable<String>() {
			@Override
			public String call() {
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					logger.error(e);
				}
				return "Awake after 3 seconds of sleep";
			}

		}, new Callable<String>() {
			@Override
			public String call() {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					logger.error(e);
				}
				return "Awake after 2 seconds of sleep";
			}

		}, new Callable<String>() {
			@Override
			public String call() {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					logger.error(e);
				}
				return "Awake after 1 seconds of sleep";
			}

		});
		try {
			List<Future<String>> list = executor.invokeAll(callables);
			for (Iterator<Future<String>> iterator = list.iterator(); iterator.hasNext();) {
				Future<String> future = (Future<String>) iterator.next();
				System.out.println(future.get());
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
