package au.com.fraudulentchecker.converters;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import au.com.fraudulentchecker.model.CreditcardTransaction;

public class LineToObjectConverterTest {

private Converter<String, String, CreditcardTransaction> converter;
	
	

	@Before
	public void setUp() throws Exception {
		converter = new LineToObjectConverter();
	}

	@Test
	public void convert_ValidValuesInCsv() {
		CreditcardTransaction creditcardTransaction = converter.convert("10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-29T13:15:54, 15.00", ",");
		Assert.assertEquals("10d7ce2f43e35fa57d1bbf8b1e1", creditcardTransaction.getCreditcardNumber());
		Assert.assertEquals(LocalDate.parse("2014-04-29"), creditcardTransaction.getTransactionDateTime().toLocalDate());
		Assert.assertEquals("15.0", creditcardTransaction.getTransactionAmount().toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void convert_EmptyValuesInCsv() {
		converter.convert("", ",");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void convert_NullCsvLineValuesInCsv() {
		converter.convert(null, ",");
	}

}
