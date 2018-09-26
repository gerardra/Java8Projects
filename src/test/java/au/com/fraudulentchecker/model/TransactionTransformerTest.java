package au.com.fraudulentchecker.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import au.com.fraudulentchecker.converters.Converter;
import au.com.fraudulentchecker.converters.LineToObjectConverter;

public class TransactionTransformerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void allTransactionWithDistinctCreditcardNumber() {
		final List<String> transactionsStr = Arrays.asList("10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-20T13:15:54, 10.00", "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00", "10d7ce2f43e35fa57d1bbf8b1e3, 2014-04-29T13:15:54, 10.00");
		final List<CreditcardTransaction> transactions = new ArrayList<>();
		final Converter<String, String, CreditcardTransaction> csvToCreditcardTransaction = new LineToObjectConverter();
		transactionsStr.stream().forEach((csv) -> transactions.add(csvToCreditcardTransaction.convert(csv, ",")));
		Map<String, Double> transformListToMap = TransactionTransformer.transformListToMap(transactions);
		Assert.assertEquals(3, transformListToMap.size());
	}
	
	@Test
	public void allTransactionWithSameCreditcardNumber() {
		final List<String> transactionsStr = Arrays.asList("10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-20T13:15:54, 10.00", "10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-29T13:15:54, 10.00", "10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-29T13:15:54, 10.00");
		final List<CreditcardTransaction> transactions = new ArrayList<>();
		final Converter<String, String, CreditcardTransaction> csvToCreditcardTransaction = new LineToObjectConverter();
		transactionsStr.stream().forEach((csv) -> transactions.add(csvToCreditcardTransaction.convert(csv, ",")));
		Map<String, Double> transformListToMap = TransactionTransformer.transformListToMap(transactions);
		Assert.assertEquals(1, transformListToMap.size());
	}


}
