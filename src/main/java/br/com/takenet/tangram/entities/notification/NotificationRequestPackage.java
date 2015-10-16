package br.com.takenet.tangram.entities.notification;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class represents a message package
 * 
 * @see NotificationRequest
 * @author Andr'e Gomes, Takenet
 *
 */
@XmlType(name = "NotificationRequestPackage")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NotificationRequestPackage {	
	/**
	 * Identificador do pacote da mensagem original
	 */
	@XmlAttribute(name = "id", required = false)
	private String id;

	/**
	 * 'Indice da mensagem original no pacote
	 */
	@XmlAttribute(name = "index", required = false)
	private Integer index;

	/**
	 * Gets the id of the message package
	 * 
	 * @see #id
	 * @return
	 *         then id of the message
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set the id of the package
	 * 
	 * @see #id
	 * @param id
	 *        the id of the package
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the index of message package
	 * 
	 * @see #index
	 * @return
	 *        the index of message package
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * Sets the index of message package
	 * @see #index
	 * @param index
	 *        the index of message package
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}
}
