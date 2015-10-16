package br.com.takenet.tangram.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * This class represents the description for TANGRAM request and response. 
 * 
 * @author Andre Gomes, Takenet
 *
 */
@XmlType(name = "Description")
@XmlRootElement(name = "description")
@XmlAccessorType(XmlAccessType.FIELD)
public class Description {	
	/**
	 * Detalhamento do status da notificacao. Em caso de sucesso, o valor 'e 0 
	 * e no caso de erro, deve ser exatamente o code da resposta do pedido que 
	 * originou a notificacao.
	 */
	@XmlAttribute(name = "code", required = false)
	private Integer code;

	/**
	 * Descricao da notificacao, contendo a informacao detalhada sobre o 
	 * status da notificacao
	 */
	@XmlValue
	private String text;
	
	/**
	 * Creates an empty description object.
	 */
	public Description() {
	}
	
	/**
	 * Creates a description object with a code and text
	 * description.
	 * 
	 * @see #code
	 * @see #text
	 * @param code
	 *        The code of the description.
	 * @param value
	 *        The description text.
	 */
	public Description(Integer code, String value) {
		this.code = code;
		this.text = value;
	}

	/**
	 * Gets the code of the description
	 * 
	 * @see #code
	 * @return 
	 *        the code of the description
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * Sets the code of the request description
	 * 
	 * @see #code
	 * @param code 
	 *        the code of the description
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * Gets the text representing the description of the request.
	 * 
	 * @see #text
	 * @return
	 *        the text of the description
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text representing the description of the request
	 * 
	 * @see #text
	 * @param value
	 *        the text of the description
	 */
	public void setText(String value) {
		this.text = value;
	}

}