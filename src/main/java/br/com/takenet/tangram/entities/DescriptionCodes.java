package br.com.takenet.tangram.entities;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum DescriptionCodes {
	@XmlEnumValue("0")
	SUCCESS(0),
	@XmlEnumValue("1") 
	UNKNOW(1),
	@XmlEnumValue("101")
	ACCESS_DENIED_USER(101),
	@XmlEnumValue("102")
	ACCESS_DENIED_IP(102),
	@XmlEnumValue("103")
	UNAUTHORIZED_CHANNEL(103),
	@XmlEnumValue("104")
	ACCESS_DENIED_CHANNEL(104),
	@XmlEnumValue("105")
	ACCESS_DENIED_SERVICE(105),
	@XmlEnumValue("106")
	ACCESS_DENIED_SERVICE_DISABLED(106),
	@XmlEnumValue("107")
	ACCESS_DENIED_SERVICE_SUSPENDED(107),
	@XmlEnumValue("112")
	ACCESS_DENIED_INVALID_DESTINATION(112),
	@XmlEnumValue("203")
	UNAUTHORIZED_PERMISSION_UNSET(203),
	@XmlEnumValue("204")
	UNAUTHORIZED_PERMISSION_DENIED(204),
	@XmlEnumValue("206")
	UNAUTHORIZED_CLIENT_LIST_UNPROVISIONED(206),
	@XmlEnumValue("207")
	UNAUTHORIZED_CLIENT_UNREGISTERED(207),
	@XmlEnumValue("208")
	UNAUTHORIZED_CLIENT_UNPROVISIONED(208),
	@XmlEnumValue("209")
	UNAUTHORIZED_CLIENT_REFUSE(209),
	@XmlEnumValue("210")
	UNAUTHORIZED_CLIENT_DISABLED(210),
	@XmlEnumValue("211")
	UNAUTHORIZED_CLIENT_SUSPENDED(211),
	@XmlEnumValue("212")
	UNAUTHORIZED_CLIENT_NON_PAYMENT(212),
	@XmlEnumValue("213")
	UNAUTHORIZED_CHANNEL_INVALID(213),
	@XmlEnumValue("214")
	UNAUTHORIZED_CHANNEL_UNSET(214),
	@XmlEnumValue("215")
	UNAUTHORIZED_SERVICE_INVALID(215),
	@XmlEnumValue("216")
	UNAUTHORIZED_ALTER_PERMISSION(216),
	@XmlEnumValue("217")
	UNAUTHORIZED_PERMISSION_UNDEFINED(217),
	@XmlEnumValue("218")
	UNAUTHORIZED_CLIENT_OUT_OF_CREDIT(218),
	@XmlEnumValue("1000")
	WRONG_REQUEST_UNSPECIFIED(1000),
	@XmlEnumValue("1001")
	WRONG_REQUEST_APP_ID(1001),
	@XmlEnumValue("1002")
	WRONG_REQUEST_CLIENT(1002),
	@XmlEnumValue("1003")
	WRONG_REQUEST_OPCODE_UNSPECIFIED(1003),
	@XmlEnumValue("1004")
	WRONG_REQUEST_OPCODE_INVALID(1004),
	@XmlEnumValue("4001")
	ERROR_CARRIER(4001),
	@XmlEnumValue("4002")
	ERROR_INVALID_CLIENT(4002),
	@XmlEnumValue("4003")
	ERROR_PASSWORD(4003);
	
	private Integer code;
	
	/**
	 * Creates a description code enum type.
	 * 
	 * @param code
	 *        The description code.
	 */
	DescriptionCodes(int code) {
		this.code = code;
	}
	
	/**
	 * Gets the integer representation of the code.
	 * 
	 * @return
	 *        The integer representation of the code.
	 */
	public Integer getCode() {
		return code;
	}
}
