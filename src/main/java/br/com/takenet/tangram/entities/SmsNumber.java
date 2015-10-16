package br.com.takenet.tangram.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * The SmsNumber class represents a telephone number.
 * 
 * @author Andr'e Gomes, Takenet
 *
 */
@XmlType(name = "SmsNumber")
@XmlRootElement(name = "sms_number")
@XmlAccessorType(XmlAccessType.FIELD)
public class SmsNumber {
	
	/**
	 * Telephone dialing prefix that are required only when
	 * dialing a telephone number to establish a call to 
	 * another country 
	 */
	@XmlAttribute(name = "country_code", required = false)
	private String countryCode;

	/**
	 * The telephone number without country code.
	 * This number must include the area code prefix.
	 */
	@XmlValue
	private Long number;
	
	
	/**
	 * Creates an empty SmsNumber object.
	 */
	public SmsNumber() {
	}
	
	/**
	 * Creates a SmsNumber object without country code.
	 * 
	 * @see #countryCode
	 * @see #number
	 * @param number
	 */
	public SmsNumber(Long number) {
		this.number = number;
	}
	
	/**
	 * Creates a SmsNumber object with country code, area code and number.
	 * @param countryCode
	 *        The country code of the telephone.
	 * @param number
	 *        The telephone number with area code.
	 */
	public SmsNumber(String countryCode, Long number) {
		this.countryCode = countryCode;
		this.number = number;
	}
	

	/**
	 * Returns the country code of the telephone number.
	 * 
	 * @see #countryCode 
	 * @return countryCode the country code prefix
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Sets the country code of the telephone number.
	 * 
	 * @see #countryCode 
	 * @param countryCode
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * Returns the number + area code of the telephone number
	 * 
	 * @see #number
	 * @return the area code + the number
	 */
	public Long getNumber() {
		return number;
	}

	/**
	 * Sets the area code + number of the telephone number
	 * 
	 * @see #number
	 */
	public void setNumber(Long number) {
		this.number = number;
	}

}
