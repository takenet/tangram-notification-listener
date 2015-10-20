package br.com.takenet.tangram.notification.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.lang3.StringUtils;
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
	
	/**
	 * Date format used in logs. 
	 */
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

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

			// Converts the response entity to a XML string.
			String xmlResponse = EntitiesUtil.entityToXmlString(NotificationResponse.class, notificationResponse);
			
			// Set the HTTP response content.
			response.setContentType("text/xml;charset=utf-8");
			response.getWriter().println(xmlResponse);
			response.setStatus(HttpServletResponse.SC_OK);
			baseRequest.setHandled(true);
			
			// Log all requests and responses to the console and file.
			// Log configuration is defined in logback.xml file
			logNotification(xmlRequest, xmlResponse);
		} catch (JAXBException exRequest) { // request parsing error. BAD_REQUEST
			logger.error(exRequest.getCause().getMessage());
			
			// Creates the response.
			NotificationResponse notificationResponse = new NotificationResponse();
			notificationResponse.setAck(true);
			notificationResponse.setDescription(new Description(1, NotificationResponse.RESPONSE_ERROR));
			
			try {
				// Converts the response entity to a XML string.
				String xmlResponse = EntitiesUtil.entityToXmlString(NotificationResponse.class, notificationResponse);
				
				// Set the HTTP response content.
				response.setContentType("text/xml;charset=utf-8");
				response.getWriter().println(xmlResponse);
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				baseRequest.setHandled(true);
			} catch (JAXBException exResponse) {
				logger.error(exRequest.getCause().getMessage());
			}
		} catch (TransformerException formatException) { // error logging the request/response
			// Set the HTTP response content.
			response.setContentType("text/xml;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			baseRequest.setHandled(true);
		}
	}
	
	/**
	 * This methods pretty prints a XML string with a custom indent amount.
	 * 
	 * @param input
	 *        The XML string.
	 * @param indent
	 *        The indent amount.
	 * @return
	 *        The pretty formatted XML string.
	 * @throws TransformerException
	 *         If the XML string formatting failed.
	 */
	private static String prettyFormatXml(String input, int indent) throws TransformerException  {
        Source xmlInput = new StreamSource(new StringReader(input));
        StringWriter stringWriter = new StringWriter();
        StreamResult xmlOutput = new StreamResult(stringWriter);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute("indent-number", indent);
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(xmlInput, xmlOutput);
        return xmlOutput.getWriter().toString();
	}
	
	/**
	 * Logs all notifications to console and file.
	 * Log configuration is defined in logback.xml file.
	 * 
	 * @param request
	 *        The XML request string.
	 * @param response
	 *        The XML response string.
	 * @throws TransformerException 
	 *         If the XML string formatting failed.
	 */
	private void logNotification(String request, String response) throws TransformerException {
		logger.debug(String.format("%s%n%s%n%n%s%n%s%n%s%n",
				StringUtils.repeat("-", 80),
				df.format(new Date()),
				prettyFormatXml(request, 4), prettyFormatXml(response, 4),
				StringUtils.repeat("-", 80)));
	}
}
