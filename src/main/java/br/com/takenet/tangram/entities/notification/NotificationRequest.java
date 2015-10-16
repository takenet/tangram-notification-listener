package br.com.takenet.tangram.entities.notification;

import java.util.Date;
import java.util.UUID;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import br.com.takenet.tangram.entities.Description;
import br.com.takenet.tangram.entities.SmsNumber;
import br.com.takenet.tangram.entities.adapters.DateXmlAdapter;

/**
 * This class represents the status of a SMS message delivery notification.
 * The notification requests are sent from TANGRAM to the client 
 * application. The client must process the notification and send a 
 * response of type {@link NotificationResponse}.
 *  
 * @see NotificationResponse
 * @author Andr'e Gomes, Takenet
 *
 */
@XmlType(name = "NotificationRequest")
@XmlRootElement(name = "notification_request")
@XmlAccessorType(XmlAccessType.FIELD)
public class NotificationRequest {
	/**
	 * Chave prim'aria para persist^encia do objeto.
	 */
	@XmlTransient
	private UUID uuid;
	
	/**
	 * <pre>
	 * Status da notificacao.
	 * Valore poss'iveis:
	 *    0. Entregue no celular
	 *    1. Enfileirado para processamento
	 *    2. Nao entregue no celular
	 *    3. Desconhecido
	 *    4. Nao informado
	 *    5. Lido
	 *    6. Tarifado
	 *    7. Nao tarifado
	 *    8. Entregue na SMSC
	 *    9. Nao entregue na SMSC
	 *    10. Autenticado
	 *    11. Nao autenticado
	 *    12. Assinado
	 *    13. Nao assinado
	 *    14. Assinatura cancelada
	 *    15. Assinatura nao cancelada
	 * </pre>
	 */
	@XmlAttribute(name = "status", required = true)
	private Integer status;
	
	/**
	 * Identificador do Dispatcher que enviou o enviaria a mensagem.  
	 * O Dispatcher 'e a entidade que comunica diretamente com a plataforma
	 * da operadora, podendo ser utilizado para identificar unicamente a 
	 * operadora. Entretanto, uma mesma operadora poder'a possuir v'arios
	 * Dispatchers.
	 */
	@XmlElement(name = "dispatcher_id", required = true)
	private Integer dispatcherId;

	/**
	 * Identificador da mensagem no TANGRAM.
	 */
	@XmlElement(name = "message_id", required = true)
	private String messageId;

	/**
	 * Identificador da mensagem na plataforma.
	 */
	@XmlElement(name = "smsc_message_id", required = true)
	private String smscMessageId;

	/**
	 * Originador da mensagem original.
	 */
	@XmlElement(name = "source", required = true)
	private SmsNumber source;

	/**
	 * Destinat'ario da mensagem original.
	 */
	@XmlElement(name = "destination", required = true)
	private SmsNumber destination;

	/**
	 * Data e hora do recebimento da mensagem original.
	 */
	@XmlElement(name = "request_datetime", required = true)
	@XmlJavaTypeAdapter(DateXmlAdapter.class)
	private Date requestDatetime;

	/**
	 * Data e hora da geracao da mensagem de notificacao.
	 */
	@XmlElement(name = "notification_datetime", required = true)
	@XmlJavaTypeAdapter(DateXmlAdapter.class)
	private Date notificationDatetime;

	/**
	 * Identificador/'Indice do pacote da mensagem original.
	 */
	@XmlElement(name = "package", required = false)
	private NotificationRequestPackage notificationPackage;

	/**
	 * Campo livre utilizado pela aplicacao na mensagem original
	 * app_specific.
	 */
	@XmlElement(name = "app_specific_id", required = false)
	private String appSpecificId;

	/**
	 * Em caso de notificacao de autenticacao, cont'em o token 
	 * da autenticacao do cliente.
	 */
	@XmlElement(name = "authentication", required = false)
	private String authentication;

	/**
	 * Descricao da notificacao.
	 */
	@XmlElement(name = "description", required = true)
	private Description description;
	
	/**
	 * Resposta associada a requisicao de notificacao
	 */
	@XmlTransient
	private NotificationResponse response;
	
	{
		source = new SmsNumber();
		destination = new SmsNumber();
		notificationPackage = new NotificationRequestPackage();
		description = new Description();
	}
	
	/**
	 * Gets the primary key used to persist the object.
	 * 
	 * @return 
	 *        the uuid key
	 */
	public UUID getUuid() {
		return uuid;
	}
	
	/**
	 * Sets the primary key used to persist the object.
	 * 
	 * @param uuid
	 *        The primary key
	 */
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	/**
	 * Gets the status of the notification request
	 * 
	 * @see #status 
	 * @return the status of the notification request
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * Sets the status of the notification request 
	 * 
	 * @see #status
	 * @param status 
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * Gets the dispatcher_id of the request
	 * 
	 * @see #dispatcherId
	 * @return the dispatcher id of the request 
	 */
	public Integer getDispatcherId() {
		return dispatcherId;
	}

	/**
	 * Sets the dispatcher_id of the request
	 * 
	 * @see #dispatcherId
	 * @param dispatcherId
	 */
	public void setDispatcherId(Integer dispatcherId) {
		this.dispatcherId = dispatcherId;
	}

	/**
	 * Gets the message_id of the request
	 * 
	 * @see #messageId
	 * @return the message id
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * Sets the message id of the request
	 * 
	 * @see #messageId
	 * @param messageId
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * Gets the SMSC Message Id of the request
	 * 
	 * @see #smscMessageId
	 * @return the smsc message id
	 */
	public String getSmscMessageId() {
		return smscMessageId;
	}

	/**
	 * Sets the SMSC Message Id of the request
	 * 
	 * @see #smscMessageId
	 * @param smscMessageId
	 */
	public void setSmscMessageId(String smscMessageId) {
		this.smscMessageId = smscMessageId;
	}

	/**
	 * Gets the source number of the request
	 * 
	 * @see #source
	 * @return the source number
	 */
	public SmsNumber getSource() {
		return source;
	}

	/**
	 * Sets the source number of the request
	 * 
	 * @see #source
	 * @param source
	 */
	public void setSource(SmsNumber source) {
		this.source = source;
	}

	/**
	 * Gets the destination number of the request
	 * 
	 * @see #destination
	 * @return the destination number
	 */
	public SmsNumber getDestination() {
		return destination;
	}

	/**
	 * Sets the destination number of the request
	 * 
	 * @see #destination
	 * @param destination
	 */
	public void setDestination(SmsNumber destination) {
		this.destination = destination;
	}

	/**
	 * Gets the request dateime
	 * 
	 * @see #requestDatetime
	 * @return the request datetime
	 */
	public Date getRequestDatetime() {
		return requestDatetime;
	}

	/**
	 * Sets the request datetime
	 * 
	 * @see #requestDatetime
	 * @param requestDatetime
	 */
	public void setRequestDatetime(Date requestDatetime) {
		this.requestDatetime = requestDatetime;
	}

	/**
	 * Gets the notification datetime
	 * 
	 * @see #notificationDatetime 
	 * @return notification datatime
	 */
	public Date getNotificationDatetime() {
		return notificationDatetime;
	}

	/**
	 * Sets the notification datetime
	 * 
	 * @see #notificationDatetime
	 * @param notificationDatetime
	 */
	public void setNotificationDatetime(Date notificationDatetime) {
		this.notificationDatetime = notificationDatetime;
	}

	/**
	 * Gets the notification package
	 * 
	 * @see #notificationPackage
	 * @return the notification package
	 */
	public NotificationRequestPackage getNotificationPackage() {
		return notificationPackage;
	}

	/**
	 * Sets the notification package
	 * 
	 * @see #notificationPackage
	 * @param notificationPackage
	 */
	public void setNotificationPackage(NotificationRequestPackage notificationPackage) {
		this.notificationPackage = notificationPackage;
	}

	/**
	 * Gets the app specific id
	 * 
	 * @see #appSpecificId
	 * @return the app specific id
	 */
	public String getAppSpecificId() {
		return appSpecificId;
	}

	/**
	 * Sets the app specific id
	 * 
	 * @see #appSpecificId
	 * @param appSpecificId
	 */
	public void setAppSpecificId(String appSpecificId) {
		this.appSpecificId = appSpecificId;
	}

	/**
	 * Gets the authentication 
	 * 
	 * @see #authentication
	 * @return the authentication
	 */
	public String getAuthentication() {
		return authentication;
	}

	/**
	 * Sets the authentication
	 * 
	 * @see #authentication
	 * @param authentication
	 */
	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	/**
	 * Gets the notification description object
	 * 
	 * @see #description
	 * @return the notification request description
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * Sets the notification request description
	 * 
	 * @see #description
	 * @param description
	 */
	public void setDescription(Description description) {
		this.description = description;
	}

	/**
	 * Gets the related response associated with the request 
	 * 
	 * @see #response
	 * @see NotificationResponse
	 * @return the associated response
	 */
	public NotificationResponse getResponse() {
		return response;
	}

	/**
	 * Sets the related response associated with the request
	 * 
	 * @see #response
	 * @see NotificationResponse
	 * @param response
	 */
	public void setResponse(NotificationResponse response) {
		this.response = response;
	}
	
}
