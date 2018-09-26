package au.com.fraudulentchecker.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionTransformer {
	
	public  static Map<String, Double> transformListToMap(List<CreditcardTransaction> transactions) {
		return transactions.stream()
    			.collect(Collectors.groupingBy(CreditcardTransaction::getCreditcardNumber, Collectors.summingDouble(CreditcardTransaction::getTransactionAmount)));
	}

}
