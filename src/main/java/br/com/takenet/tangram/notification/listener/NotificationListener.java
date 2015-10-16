package br.com.takenet.tangram.notification.listener;

import org.eclipse.jetty.server.Server;

/**
 * This class starts a http server to listen the TANGRAM notification requests.
 * 
 * @author Andre Gomes, Takenet
 *
 */
public class NotificationListener {
	public static void main(String[] args) throws Exception {
		// Creates a jetty server to listen to 8181 port.
		Server server = new Server(8181);
		
		// Inject the custom handler to log all the requests.
		server.setHandler(new NotificationHandler());
		
		// Starts the server and waits forever.
		server.start();
		server.join();
	}
}

