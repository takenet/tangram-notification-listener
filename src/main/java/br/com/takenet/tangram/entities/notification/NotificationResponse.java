package br.com.takenet.tangram.entities.notification;

import java.util.UUID;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import br.com.takenet.tangram.entities.Description;

/**
 * This class represents a response of a notification. 
 * When a notification request is sent to the client application,
 * it must send a response containing the result of the operation.
 * 
 * @see NotificationRequest
 * @author Andr'e Gomes, Takenet
 *
 */
@XmlType(name = "NotificationResponse")
@XmlRootElement(name = "notification_response")
@XmlAccessorType(XmlAccessType.FIELD)
public class NotificationResponse {
	public static final String RESPONSE_OK = "Notificacao processada com sucesso";
	public static final String RESPONSE_ERROR = "Notificacao nao processada";
	
	/**
	 * Chave prim'aria para persist^encia do objeto.
	 */
	@XmlTransient
	private UUID uuid;
	
	/**
	 * Indica a versao do XML utilizado.
	 */
	@XmlAttribute(name = "version", required = false)
	private Integer version;

	/**
	 * Determina se a aplicacao recebeu/processou corretamente a notificacao.
	 */
	@XmlAttribute(name = "ack", required = true)
	private Boolean ack;

	/**
	 * Identificador da mensagem no TANGRAM
	 */
	@XmlElement(name = "message_id", required = false)
	private String messageId;

	/**
	 * Mensagem descritiva sobre o resultado da operacao
	 */
	@XmlElement(name = "description", required = true)
	private Description description;
	
	{
		description = new Description();
		ack = true;
	}
	
	/**
	 * Gets the primary key used to persist the object.
	 * 
	 * @see #uuid
	 * @return 
	 *        the uuid key
	 */
	public UUID getUuid() {
		return uuid;
	}
	
	/**
	 * Sets the primary key used to persist the object.
	 * 
	 * @see #uuid
	 * @param uuid
	 *        The primary key
	 */
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	/**
	 * Gets the XML version of the response
	 * 
	 * @see #version
	 * @return
	 *        The version of the XML.
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * Sets the XML version of the response
	 * 
	 * @see #version
	 * @param version
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * Returns true if the response was processed successfully.
	 * 
	 * @see #ack
	 * @return
	 *        True if successfully processed
	 */
	public Boolean getAck() {
		return ack;
	}

	/**
	 * Sets the ack to true if the response was processed successfully.
	 * 
	 * @see #ack
	 * @param ack
	 *        True if successfully processed 
	 */
	public void setAck(Boolean ack) {
		this.ack = ack;
	}

	/**
	 * Gets the message id of the response
	 * 
	 * @see #messageId
	 * @return
	 *        The message id
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * Sets the message id of the response
	 * 
	 * @see #messageId
	 * @param messageId
	 *        The message id of the response.
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * Gets the description of the response
	 * 
	 * @see #description
	 * @return
	 *        The description of the response.
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * Sets the description of the response
	 * 
	 * @see #description
	 * @param description
	 *        The description of the response
	 */
	public void setDescription(Description description) {
		this.description = description;
	}

}
