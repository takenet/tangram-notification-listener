package br.com.takenet.tangram.entities;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.UUID;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class defines utilities methods for the application entities.
 * 
 * @author Andre Gomes, Takenet
 *
 */
public class EntitiesUtil {

	private EntitiesUtil() {
	}

	/**
	 * Retrieves an entity object of the XML representation.
	 * The entity must be annotated with @{@link XmlRootElement}.
	 * 
	 * @param <T> 
	 * 		  the return type
	 * @param type 
	 *        the class of the returned object.
	 * @param xmlString
	 *        XML representing the object.
	 * @return 
	 * 		  the corresponding object
	 * @throws JAXBException
	 *        if conversion fails
	 */
	static public <T> T xmlStringToEntity(Class<T> type, String xmlString) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(type);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(xmlString);
		@SuppressWarnings("unchecked")
		T obj = (T) unmarshaller.unmarshal(reader);

		return obj;
	}

	/**
	 * Retrieves a XML string of an entity object.
	 * The entity must be annotated with @{@link XmlRootElement}.
	 * 
	 * @param type 
	 * 		  the class of the entity object
	 * @param entity
	 *        the object
	 * @return 
	 *        the XML string representation
	 * @throws JAXBException
	 *        if conversion fails
	 */
	static public <T> String entityToXmlString(Class<T> type, T entity) throws JAXBException {
		StringWriter wr = new StringWriter();
		JAXBContext jaxbContext = JAXBContext.newInstance(type);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(entity, wr);

		return wr.toString();
	}

	/**
	 * Converts a string to a UUID object.
	 * 
	 * @see <a href="http://stackoverflow.com/questions/18871980/uuid-fromstring-returns-an-invalid-uuid">Invalid long bug</a>
	 * @param str 
	 *        string representation of the UUID
	 * @return 
	 *        a UUID object
	 */
	public static UUID uuidFromString(String str) {
		String strNum = str.replace("-", "");
		UUID uuid = new UUID(new BigInteger(strNum.substring(0, 16), 16).longValue(),
				new BigInteger(strNum.substring(16), 16).longValue());

		return uuid;
	}
}
