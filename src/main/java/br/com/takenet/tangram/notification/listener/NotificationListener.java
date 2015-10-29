package br.com.takenet.tangram.notification.listener;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.eclipse.jetty.server.Server;

/**
 * This class starts a http server to listen the TANGRAM notification requests.
 * 
 * @author Andre Gomes, Takenet
 *
 */
public class NotificationListener {
	public static void main(String[] args) throws Exception {
		Integer port = 0;
		
		try {
			XMLConfiguration config = new XMLConfiguration("./config.xml");	
			port = Integer.valueOf(config.getString("server.port"));
		} catch (ConfigurationException | NumberFormatException  ex) {
			port = 8181;
		}
		
		// Creates a jetty server to listen to 8181 port.
		Server server = new Server(port);
		System.out.println("Listening on port " + port);
		
		// Inject the custom handler to log all the requests.
		server.setHandler(new NotificationHandler());
		
		// Starts the server and waits forever.
		server.start();
		server.join();
	}
}

