package ie.gmit.sw.client;


import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ie.gmit.sw.client.RequestCall;;

public class Listener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Thread task = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				final Queue<AsyncContext> ctxQueue = new ConcurrentLinkedQueue<AsyncContext>();
                sce.getServletContext().setAttribute("context", ctxQueue);
                
                final Queue<RequestCall> inQueue = new ConcurrentLinkedQueue<RequestCall>();
                final Queue<RequestCall> outQueue = new ConcurrentLinkedQueue<RequestCall>();

                sce.getServletContext().setAttribute("inQueue", inQueue);

                Executor inExecutor = Executors.newCachedThreadPool();
                final Executor outExecutor = Executors.newCachedThreadPool();
                final Executor userExecutor = Executors.newCachedThreadPool();
			}
		});
	}
	

}
