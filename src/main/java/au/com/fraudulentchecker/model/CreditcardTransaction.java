package au.com.fraudulentchecker.model;

import java.time.LocalDateTime;

public class CreditcardTransaction {
	
	private final String creditcardNumber;
	
	private final LocalDateTime transactionDateTime;
	
	private final Double transactionAmount;
	
	public CreditcardTransaction(final String creditcardNumber, final LocalDateTime transactionDateTime,
			final Double transactionAmount) {
		super();
		this.creditcardNumber = creditcardNumber;
		this.transactionDateTime = transactionDateTime;
		this.transactionAmount = transactionAmount;
	}
	
	public String getCreditcardNumber() {
		return creditcardNumber;
	}
	
	public LocalDateTime getTransactionDateTime() {
		return transactionDateTime;
	}
	
	public Double getTransactionAmount() {
		return transactionAmount;
	}
	
}
