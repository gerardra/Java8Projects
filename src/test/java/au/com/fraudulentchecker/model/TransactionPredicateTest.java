package au.com.fraudulentchecker.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import au.com.fraudulentchecker.converters.Converter;
import au.com.fraudulentchecker.converters.LineToObjectConverter;

public class TransactionPredicateTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void TransactionPredicate_filterByDate_AllMatchingDates() {
		final List<String> transactionsStr = Arrays.asList("10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-29T13:15:54, 10.00", "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00", "10d7ce2f43e35fa57d1bbf8b1e3, 2014-04-29T13:15:54, 10.00");
		final List<CreditcardTransaction> transactions = new ArrayList<>();
		final Converter<String, String, CreditcardTransaction> csvToCreditcardTransaction = new LineToObjectConverter();
		transactionsStr.stream().forEach((csv) -> transactions.add(csvToCreditcardTransaction.convert(csv, ",")));
		List<CreditcardTransaction> filterTransactions = TransactionPredicate.filterTransactions(transactions, TransactionPredicate.filterByDate(LocalDate.parse("2014-04-29")));
		Assert.assertEquals(3, filterTransactions.size());
	}
	
	@Test
	public void TransactionPredicate_filterByDate_OneMatchingDates() {
		final List<String> transactionsStr = Arrays.asList("10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-20T13:15:54, 10.00", "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00", "10d7ce2f43e35fa57d1bbf8b1e3, 2014-04-29T13:15:54, 10.00");
		final List<CreditcardTransaction> transactions = new ArrayList<>();
		final Converter<String, String, CreditcardTransaction> csvToCreditcardTransaction = new LineToObjectConverter();
		transactionsStr.stream().forEach((csv) -> transactions.add(csvToCreditcardTransaction.convert(csv, ",")));
		List<CreditcardTransaction> filterTransactions = TransactionPredicate.filterTransactions(transactions, TransactionPredicate.filterByDate(LocalDate.parse("2014-04-20")));
		Assert.assertEquals(1, filterTransactions.size());
	}

	@Test
	public void TransactionPredicate_filterByDate_NoMatchingDates() {
		final List<String> transactionsStr = Arrays.asList("10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-20T13:15:54, 10.00", "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00", "10d7ce2f43e35fa57d1bbf8b1e3, 2014-04-29T13:15:54, 10.00");
		final List<CreditcardTransaction> transactions = new ArrayList<>();
		final Converter<String, String, CreditcardTransaction> csvToCreditcardTransaction = new LineToObjectConverter();
		transactionsStr.stream().forEach((csv) -> transactions.add(csvToCreditcardTransaction.convert(csv, ",")));
		List<CreditcardTransaction> filterTransactions = TransactionPredicate.filterTransactions(transactions, TransactionPredicate.filterByDate(LocalDate.parse("2014-04-30")));
		Assert.assertEquals(0, filterTransactions.size());
	}
	

	@Test
	public void TransactionPredicate_filterByAmount_TwoTransactionMatchingPredicate() {
		Map<String, Double> transactionMap = new HashMap<>();
		transactionMap.put("10d7ce2f43e35fa57d1bbf8b1e1", new Double(25.00));
		transactionMap.put("10d7ce2f43e35fa57d1bbf8b1e2", new Double(5.00));
		transactionMap.put("10d7ce2f43e35fa57d1bbf8b1e3", new Double(15.00));
	    List<String> byKeys = TransactionPredicate.filterMapByKeys(transactionMap, TransactionPredicate.filterByAmount(new Double(10.00)));
		Assert.assertEquals(2, byKeys.size());
	}
	
	@Test
	public void TransactionPredicate_filterByAmount_NoTransactionMatchingPredicate() {
		Map<String, Double> transactionMap = new HashMap<>();
		transactionMap.put("10d7ce2f43e35fa57d1bbf8b1e1", new Double(25.00));
		transactionMap.put("10d7ce2f43e35fa57d1bbf8b1e2", new Double(5.00));
		transactionMap.put("10d7ce2f43e35fa57d1bbf8b1e3", new Double(15.00));
	    List<String> byKeys = TransactionPredicate.filterMapByKeys(transactionMap, TransactionPredicate.filterByAmount(new Double(100.00)));
		Assert.assertEquals(0, byKeys.size());
	}

	@Test
	public void TransactionPredicate_filterByAmount_AllTransactionMatchingPredicate() {
		Map<String, Double> transactionMap = new HashMap<>();
		transactionMap.put("10d7ce2f43e35fa57d1bbf8b1e1", new Double(25.00));
		transactionMap.put("10d7ce2f43e35fa57d1bbf8b1e2", new Double(5.00));
		transactionMap.put("10d7ce2f43e35fa57d1bbf8b1e3", new Double(15.00));
	    List<String> byKeys = TransactionPredicate.filterMapByKeys(transactionMap, TransactionPredicate.filterByAmount(new Double(4.99)));
		Assert.assertEquals(3, byKeys.size());
	}
	
	

}
