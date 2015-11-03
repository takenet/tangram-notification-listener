package br.com.takenet.tangram.notification.listener.console;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.takenet.tangram.notification.listener.LogFormat;
import br.com.takenet.tangram.notification.listener.NotificationListenerServer;

/**
 * This class starts a http server to listen the TANGRAM notification requests.
 * 
 * @author Andre Gomes, Takenet
 *
 */
public class NotificationListenerConsoleApp {
	/**
	 * Get a logger instance.
	 * All logs goes to console and a file.
	 */
	private static final Logger logger = LoggerFactory.getLogger(NotificationListenerConsoleApp.class);
	
	public static void main(String[] args) throws Exception {
		
		Integer port = 0;
		
		try {
			XMLConfiguration config = new XMLConfiguration("./config.xml");	
			port = Integer.valueOf(config.getString("server.port"));
		} catch (ConfigurationException | NumberFormatException  ex) {
			port = 8181;
		}
		
		// Creates a jetty server to listen to 8181 port.
		NotificationListenerServer server = new NotificationListenerServer(port);

		// Inject the custom handler to log all the requests.
		server.addOnHandleNotification((request, response) -> {
			try {
				logger.info(LogFormat.formattedNotification(request, response));
			} catch (Exception e) {
				logger.info(LogFormat.unformattedNotification(request, response));
			}
		});

		server.startServer();
		System.out.println("Listening on port " + port);
				
		// Starts the server and waits forever.
		System.out.println("Started");
		server.joinServer();
	}
}

