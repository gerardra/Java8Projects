package au.com.fraudulentchecker;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import au.com.fraudulentchecker.FraudulentChecker;
import au.com.fraudulentchecker.FraudulentCreditcardChecker;

public class FraudulentCheckerTest{

	private FraudulentChecker fraudulentChecker;
	
	@Before
	public void setUp() throws Exception {
		fraudulentChecker = new FraudulentCreditcardChecker();
	}
	
	@Test
	public void getFraudulentCredutcardNumbers_AnySameDayTransactionReachingMaxAmount() {
		final List<String> transactions = Arrays.asList("10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-29T13:15:54, 10.00", "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00", "10d7ce2f43e35fa57d1bbf8b1e3, 2014-04-29T13:15:54, 10.00");
		final List<String> fraudulentCreditcardNumbers = fraudulentChecker.getFraudulentCreditcardNumbers(transactions, LocalDate.parse("2014-04-29"), new Double(10.1));
		Assert.assertEquals(fraudulentCreditcardNumbers.size(), 0);
	}

	@Test
	public void getFraudulentCredutcardNumbers_OneSameDayTransactionReachingMaxAmount() {
		final List<String> transactions = Arrays.asList("10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-29T13:15:54, 15.00", "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00", "10d7ce2f43e35fa57d1bbf8b1e3, 2014-04-29T13:15:54, 10.00");
		final List<String> fraudulentCreditcardNumbers = fraudulentChecker.getFraudulentCreditcardNumbers(transactions, LocalDate.parse("2014-04-29"), new Double(10.1));
		Assert.assertEquals(fraudulentCreditcardNumbers.size(), 1);
		Assert.assertEquals(fraudulentCreditcardNumbers.get(0), "10d7ce2f43e35fa57d1bbf8b1e1");
	}

	@Test
	public void getFraudulentCredutcardNumbers_OneDifferentDayTransactionReachingMaxAmount() {
		final List<String> transactions = Arrays.asList("10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-29T13:15:54, 15.00", "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00", "10d7ce2f43e35fa57d1bbf8b1e3, 2014-04-29T13:15:54, 10.00");
		final List<String> fraudulentCreditcardNumbers = fraudulentChecker.getFraudulentCreditcardNumbers(transactions, LocalDate.parse("2014-04-30"), new Double(10.1));
		Assert.assertEquals(fraudulentCreditcardNumbers.size(), 0);
	}

	@Test
	public void getFraudulentCredutcardNumbers_TwoSameDayDifferentTransactionsReachingMaxAmount() {
		final List<String> transactions = Arrays.asList("10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-29T13:15:54, 15.00", "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 15.00", "10d7ce2f43e35fa57d1bbf8b1e3, 2014-04-29T13:15:54, 10.00");
		final List<String> fraudulentCreditcardNumbers = fraudulentChecker.getFraudulentCreditcardNumbers(transactions, LocalDate.parse("2014-04-29"), new Double(10.1));
		Assert.assertEquals(fraudulentCreditcardNumbers.size(), 2);
		Assert.assertTrue(fraudulentCreditcardNumbers.contains("10d7ce2f43e35fa57d1bbf8b1e1"));
		Assert.assertTrue(fraudulentCreditcardNumbers.contains("10d7ce2f43e35fa57d1bbf8b1e2"));
	}
	
	@Test
	public void getFraudulentCredutcardNumbers_TwoSameDaySameTransactionReachingMaxAmount() {
		final List<String> transactions = Arrays.asList("10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-29T13:15:54, 15.00", "10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-29T13:15:54, 10.2", "10d7ce2f43e35fa57d1bbf8b1e3, 2014-04-29T13:15:54, 10.00");
		final List<String> fraudulentCreditcardNumbers = fraudulentChecker.getFraudulentCreditcardNumbers(transactions, LocalDate.parse("2014-04-29"), new Double(10.1));
		Assert.assertEquals(fraudulentCreditcardNumbers.size(), 1);
		Assert.assertTrue(fraudulentCreditcardNumbers.contains("10d7ce2f43e35fa57d1bbf8b1e1"));
	}
	
	@Test
	public void getFraudulentCredutcardNumbers_TwoSameDayDifferentTransactionsReachingMaxEqualAmount() {
		final List<String> transactions = Arrays.asList("10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-29T13:15:54, 10.10", "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.10", "10d7ce2f43e35fa57d1bbf8b1e3, 2014-04-29T13:15:54, 10.00");
		final List<String> fraudulentCreditcardNumbers = fraudulentChecker.getFraudulentCreditcardNumbers(transactions, LocalDate.parse("2014-04-29"), new Double(10.1));
		Assert.assertEquals(fraudulentCreditcardNumbers.size(), 2);
		Assert.assertTrue(fraudulentCreditcardNumbers.contains("10d7ce2f43e35fa57d1bbf8b1e1"));
		Assert.assertTrue(fraudulentCreditcardNumbers.contains("10d7ce2f43e35fa57d1bbf8b1e2"));
	}
	
	
	@Test
	public void getFraudulentCredutcardNumbers_ThreeSameDaySameTransactionsReachingMaxVeryLargeAmountAmount() {
		final List<String> transactions = Arrays.asList("10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-29T13:15:54, 100000000000000.1111111"
				, "10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-29T13:15:54, 100000000000000.1111111"
				, "10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-29T13:15:54, 100000000000000.1111111");
		final List<String> getFraudulentCreditcardNumbers = fraudulentChecker.getFraudulentCreditcardNumbers(transactions, LocalDate.parse("2014-04-29"), new Double(300000000000000.33));
		Assert.assertEquals(getFraudulentCreditcardNumbers.size(), 1);
		Assert.assertTrue(getFraudulentCreditcardNumbers.contains("10d7ce2f43e35fa57d1bbf8b1e1"));
	}
	

	
	@Test
	public void getFraudulentCredutcardNumbers_ThreeSameDaySameTransactionsNotReachingMaxVeryLargeAmountAmount() {
		final List<String> transactions = Arrays.asList("10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-29T13:15:54, 100000000000000.1111111"
				, "10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-29T13:15:54, 100000000000000.1111111"
				, "10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-29T13:15:54, 100000000000000.1111111");
		final List<String> getFraudulentCreditcardNumbers = fraudulentChecker.getFraudulentCreditcardNumbers(transactions, LocalDate.parse("2014-04-29"), new Double(300000000000000.35));
		Assert.assertEquals(getFraudulentCreditcardNumbers.size(), 0);
	}
}
