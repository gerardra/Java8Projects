package au.com.fraudulentchecker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import au.com.fraudulentchecker.converters.Converter;
import au.com.fraudulentchecker.converters.LineToObjectConverter;
import au.com.fraudulentchecker.model.CreditcardTransaction;
import au.com.fraudulentchecker.model.TransactionPredicate;
import au.com.fraudulentchecker.model.TransactionTransformer;

public class FraudulentCreditcardChecker implements FraudulentChecker{
	

	@Override
    public List<String> getFraudulentCreditcardNumbers(final List<String> transactionsCsv, final LocalDate date, final Double amount) {
		final List<CreditcardTransaction> transactions = new ArrayList<>();
		final Converter<String, String, CreditcardTransaction> csvToCreditcardTransaction = new LineToObjectConverter();
		transactionsCsv.stream().forEach((csv) -> transactions.add(csvToCreditcardTransaction.convert(csv, COMMA)));
		final List<CreditcardTransaction> dateTransactions = TransactionPredicate.filterTransactions(transactions, TransactionPredicate.filterByDate(date));
    	final Map<String, Double> transactionsAmountByCcNumber = TransactionTransformer.transformListToMap(dateTransactions);
    	List<Entry<String, Double>> collect = transactionsAmountByCcNumber.entrySet().stream().filter(entry -> entry.getValue() > amount).collect(Collectors.toList());
    	System.out.println("Test " + collect);
    	return TransactionPredicate.filterMapByKeys(transactionsAmountByCcNumber, TransactionPredicate.filterByAmount(amount));
    	
    }
    
    
}
