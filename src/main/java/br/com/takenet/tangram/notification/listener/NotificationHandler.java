package br.com.takenet.tangram.notification.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.takenet.tangram.entities.Description;
import br.com.takenet.tangram.entities.EntitiesUtil;
import br.com.takenet.tangram.entities.notification.NotificationRequest;
import br.com.takenet.tangram.entities.notification.NotificationResponse;

/**
 * This class handle all notifications request from TANGRAM.
 * All requests and responses are logged.
 *  
 * @author Andre Gomes, Takenet
 *
 */
public class NotificationHandler extends AbstractHandler {
	/**
	 * Get a logger instance.
	 * All logs goes to console and a file.
	 */
	private static final Logger logger = LoggerFactory.getLogger(NotificationHandler.class);
	
	private List<OnHandleNotificationInterface> loggerHandlers;
	
	{
		loggerHandlers = new ArrayList<>();
	}
	
	public void addOnHandleNotification(OnHandleNotificationInterface logger) {
		loggerHandlers.add(logger);
	}

	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// Read the notification request content to retrieve the xml string. 
		BufferedReader reader = request.getReader();
		StringBuilder builder = new StringBuilder();
		String aux = null;
		while ((aux = reader.readLine()) != null) {
		    builder.append(aux);
		    builder.append(System.lineSeparator());
		}
		String xmlRequest = builder.toString();
		String xmlResponse = "";
		
		// Converts the XML to an entity, gets the message id, 
		// and puts the context of the notification response in
		// the http response.
		try {
			// Converts the request XML to an entity.
			NotificationRequest notificationRequest = EntitiesUtil.xmlStringToEntity(NotificationRequest.class, xmlRequest);
			
			// Creates the response.
			NotificationResponse notificationResponse = new NotificationResponse();
			notificationResponse.setAck(true);
			notificationResponse.setDescription(new Description(0, NotificationResponse.RESPONSE_OK));
			notificationResponse.setMessageId(notificationRequest.getMessageId());

			try {
				// Converts the response entity to a XML string.
				xmlResponse = EntitiesUtil.entityToXmlString(NotificationResponse.class, notificationResponse);
				
				// Set the HTTP response content.
				response.setContentType("text/xml;charset=utf-8");
				response.getWriter().println(xmlResponse);
				response.setStatus(HttpServletResponse.SC_OK);
				baseRequest.setHandled(true);
			} catch (JAXBException ex) {
				logger.error(ex.getCause().getMessage());
			}
			
		} catch (JAXBException exRequest) { // request parsing error. BAD_REQUEST
			logger.error(exRequest.getCause().getMessage());
			
			// Creates the response.
			NotificationResponse notificationResponse = new NotificationResponse();
			notificationResponse.setAck(true);
			notificationResponse.setDescription(new Description(1, NotificationResponse.RESPONSE_ERROR));
			
			try {
				// Converts the response entity to a XML string.
				xmlResponse = EntitiesUtil.entityToXmlString(NotificationResponse.class, notificationResponse);
				
				// Set the HTTP response content.
				response.setContentType("text/xml;charset=utf-8");
				response.getWriter().println(xmlResponse);
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				baseRequest.setHandled(true);
			} catch (JAXBException exResponse) {
				logger.error(exRequest.getCause().getMessage());
			}
		} finally {
			dispatchNotifications(xmlRequest, xmlResponse);
		}
	}
	
	private void dispatchNotifications(String request, String response) {
		for (OnHandleNotificationInterface logger : loggerHandlers) {
			logger.log(request, response);
		}
	}
	
}
