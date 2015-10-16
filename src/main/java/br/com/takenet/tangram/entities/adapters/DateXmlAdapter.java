package br.com.takenet.tangram.entities.adapters;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * This class translates String to Date and Date to String.  
 * Format: ddMMyyHHmm
 * 
 * @author Andre Gomes, Takenet
 *
 */
public class DateXmlAdapter extends XmlAdapter<String, Date> {
	/**
	 * The format used to translate: ddMMyyHHmm
	 */
	private SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyHHmm");

	@Override
	public String marshal(Date d) throws Exception {
		return dateFormat.format(d);
	}

	@Override
	public Date unmarshal(String d) throws Exception {
		return dateFormat.parse(d);
	}

}
