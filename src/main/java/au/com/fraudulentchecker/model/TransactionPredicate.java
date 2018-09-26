package au.com.fraudulentchecker.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

public class TransactionPredicate {

	public static Predicate<CreditcardTransaction> filterByDate(final LocalDate date) {
	    return transaction -> transaction.getTransactionDateTime().toLocalDate().equals(date);
	}
	
	public static Predicate<Double> filterByAmount(final Double amount) {
	    return transactionAmount -> transactionAmount >= amount;
	}
	
	public static <T> List<T> filterTransactions(final Collection<T> transactions, final Predicate<T> predicate) {
    	List<T> filteredList = new ArrayList<T>();
    	for (T t : transactions) {
    		if (predicate.test(t)) {
    			filteredList.add(t);
    		}
    	}
    	return filteredList;
    }
	
	public static <K, V> List<K> filterMapByKeys(final Map<K, V> transactionsMap, final Predicate<V> predicate) {
    	List<K> filteredList = new ArrayList<K>();
    	for (Entry<K, V> entry : transactionsMap.entrySet()) {
    		if (predicate.test(entry.getValue())) {
    			filteredList.add(entry.getKey());
    		}
    	}
    	return filteredList;
    }
}
