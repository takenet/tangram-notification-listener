package br.com.takenet.tangram.notification.listener;

import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.lang3.StringUtils;

public class LogFormat {
	/**
	 * Date format used in logs. 
	 */
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	
	private LogFormat() {
	}
	
	public static String unformattedNotification(String request, String response) {
		String formattedStr = String.format("%s%n%s%n%n%s%n%s%n%s%n",
				StringUtils.repeat("-", 80),
				df.format(new Date()),
				request, response,
				StringUtils.repeat("-", 80));
		
		return formattedStr;
	}
	
	public static String formattedNotification(String request, String response) throws TransformerException {
		String formattedStr = String.format("%s%n%s%n%n%s%n%s%n%s%n",
				StringUtils.repeat("-", 80),
				df.format(new Date()),
				LogFormat.prettyFormatXml(request, 4), LogFormat.prettyFormatXml(response, 4),
				StringUtils.repeat("-", 80));
		
		return formattedStr;
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
	public static String prettyFormatXml(String input, int indent) throws TransformerException  {
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
}
