package br.com.takenet.tangram.notification.listener.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import br.com.takenet.tangram.notification.listener.LogFormat;
import br.com.takenet.tangram.notification.listener.NotificationListenerServer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainWindowController implements Initializable  {
	private NotificationListenerServer server;
	
	@FXML
	private MenuItem menuItemClose;

	@FXML
	private MenuItem menuItemServerStart;
	
	@FXML
	private MenuItem menuItemServerStop;
	
	@FXML
	private MenuItem menuItemAbout;

	@FXML
	private Label labelServerStatus;
	
	@FXML
	private TextArea logTextArea;
	
	public MainWindowController(NotificationListenerServer server) {
		this.server = server;
		addNotificationServerHandler();
	}
	
	private void addNotificationServerHandler() {
		// Inject the custom handler to log all the requests.
		server.addOnHandleNotification((request, response) -> {
			try {
				logTextArea.appendText(LogFormat.formattedNotification(request, response));
			} catch (Exception e) {
				logTextArea.appendText(LogFormat.unformattedNotification(request, response));
			}
		});
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		menuItemClose.setOnAction(value -> {
			exit();
    	});
		
		menuItemServerStart.setOnAction(value -> {
			startServer();
		});
		
		menuItemServerStop.setOnAction(value -> {
			stopServer();
		});
		
		menuItemAbout.setOnAction(value -> {
			showHelpWindow(resources);
		});
	}

	public void exit() {
		server.shutdown();
		Platform.exit();
	}
	
	public void startServer() {
		server.startServer();
		labelServerStatus.setText("Running");
		labelServerStatus.setTextFill(Color.GREEN);
		menuItemServerStart.setDisable(true);
		menuItemServerStop.setDisable(false);
	}
	
	public void stopServer() {
		server.stopServer();
		labelServerStatus.setText("Stopped");
		labelServerStatus.setTextFill(Color.RED);
		menuItemServerStart.setDisable(false);
		menuItemServerStop.setDisable(true);
	}
	
	public void showHelpWindow(ResourceBundle resources) {
        try {
        	Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("views/NotificationListenerAbout.fxml"), resources);
            Stage stage = new Stage();
            stage.setTitle("About TANGRAM Notification Listener");
            stage.setResizable(false);
            Scene rootScene = new Scene(root);
            rootScene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event) {
					if (event.getCode() == KeyCode.ESCAPE) {
						stage.close();
					}
				}
			});
            stage.setScene(rootScene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
