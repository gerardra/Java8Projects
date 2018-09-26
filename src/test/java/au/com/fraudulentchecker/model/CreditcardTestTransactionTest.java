package au.com.fraudulentchecker.model;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

public class CreditcardTestTransactionTest {

	private static String EMPTY = "";
	private static LocalDateTime NOW = LocalDateTime.now();
	
	@Test
    public void testWithNullValue() {
		CreditcardTransaction transactionCreditcard = new CreditcardTransaction(null, null, null);
    	Assert.assertEquals(null, transactionCreditcard.getCreditcardNumber());
    	Assert.assertEquals(null, transactionCreditcard.getTransactionAmount());
    	Assert.assertEquals(null, transactionCreditcard.getTransactionDateTime());
    }
	
	@Test
    public void testWithDefaultValues() {
		CreditcardTransaction transactionCreditcard = new CreditcardTransaction(EMPTY, NOW, 0.0);
    	Assert.assertEquals(EMPTY, transactionCreditcard.getCreditcardNumber());
    	Assert.assertEquals(NOW, transactionCreditcard.getTransactionDateTime());
    	Assert.assertEquals(new Double(0.0), transactionCreditcard.getTransactionAmount());
    }
	

	@Test
    public void testWithValues() {
		CreditcardTransaction transactionCreditcard = new CreditcardTransaction("12345678", NOW, 26000.0);
    	Assert.assertEquals("12345678", transactionCreditcard.getCreditcardNumber());
    	Assert.assertEquals(NOW, transactionCreditcard.getTransactionDateTime());
    	Assert.assertEquals(new Double(26000.0), transactionCreditcard.getTransactionAmount());
    }
}
