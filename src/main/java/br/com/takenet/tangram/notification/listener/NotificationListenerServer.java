package br.com.takenet.tangram.notification.listener;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.eclipse.jetty.server.Server;

public class NotificationListenerServer {
	private Server server;
	private NotificationHandler handler;
	private Future<Void> future;
	private ExecutorService pool;
	
	{
		pool = Executors.newFixedThreadPool(1);
		handler = new NotificationHandler();
	}
	
	public NotificationListenerServer(int port) {		
		// Creates a jetty server to listen to 8181 port.
		server = new Server(port);
		server.setHandler(handler);
	}
	
	public void addOnHandleNotification(OnHandleNotificationInterface h) {
		handler.addOnHandleNotification(h);
	}
	
	public void startServer() {
		future = pool.submit(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				if (!server.isRunning() && !server.isStarting())
					server.start();
				return null;
			}
		});
	}
	
	public void stopServer() {
		try {
			server.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (future != null)
			future.cancel(true);
	}
	
	public void joinServer() throws InterruptedException {
		server.join();
	}
	
	public void shutdown() {
		stopServer();
		pool.shutdown();
	}
}
