package br.com.takenet.tangram.notification.listener.gui;

import java.io.IOException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.takenet.tangram.notification.listener.LogFormat;
import br.com.takenet.tangram.notification.listener.NotificationListenerServer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApplication extends Application {
	
	private static final Logger logger = LoggerFactory.getLogger(MainApplication.class);
	private Stage primaryStage;
	private NotificationListenerServer server;
	private Integer serverPort;
	private MainWindowController mainWindowController;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("TANGRAM Notification Listener");
		
		serverPort = getDefaultServerPort();
		this.server = new NotificationListenerServer(serverPort);
		setServerLogHandlers();
		
		mainWindowController = new MainWindowController(server);
		initRootLayout();
		mainWindowController.startServer();
	}
	
	private int getDefaultServerPort() {		
		try {
			XMLConfiguration config = new XMLConfiguration("./config.xml");	
			int port = Integer.valueOf(config.getString("server.port"));
			return port;
		} catch (ConfigurationException | NumberFormatException  ex) {
			return 8181;
		}
	}
	
	private void setServerLogHandlers() {
		// Inject the custom handler to log all the requests.
		server.addOnHandleNotification((request, response) -> {
			try {
				logger.debug(LogFormat.formattedNotification(request, response));
			} catch (Exception e) {
				logger.debug(LogFormat.unformattedNotification(request, response));
			}
		});
	}

	public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            ClassLoader classLoader = getClass().getClassLoader();
            loader.setLocation(classLoader.getResource("views/NotificationListenerView.fxml"));
            loader.setController(mainWindowController);
            
            BorderPane rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setOnCloseRequest(value -> {
            	server.shutdown();
            	Platform.exit();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		launch(args);
	}
}
