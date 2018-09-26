package au.com.fraudulentchecker.converters;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;

import au.com.fraudulentchecker.model.CreditcardTransaction;

public class LineToObjectConverter implements Converter<String, String, CreditcardTransaction> {


	public CreditcardTransaction convert(String line, String separator) {
		String[] transactionDetails = StringUtils.isNotBlank(line)? line.split(separator) : new String[0];
		if(transactionDetails.length != 3 || StringUtils.isBlank(transactionDetails[0]) || StringUtils.isBlank(transactionDetails[1]) || StringUtils.isBlank(transactionDetails[2])) {
  		  throw new IllegalArgumentException("Credit card transaction details cannot be empty");
  	  }
  	  final CreditcardTransaction ccTransaction = new CreditcardTransaction(transactionDetails[0].trim(), LocalDateTime.parse(StringUtils.stripToEmpty(transactionDetails[1])),  new Double(transactionDetails[2].trim()));
  	  return ccTransaction;
	}

	

}
