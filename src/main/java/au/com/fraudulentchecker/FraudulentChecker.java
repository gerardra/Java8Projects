package au.com.fraudulentchecker;

import java.time.LocalDate;
import java.util.List;

@FunctionalInterface
public interface FraudulentChecker 
{
    String COMMA = ",";
	public List<String> getFraudulentCreditcardNumbers(final List<String> transactions, final LocalDate date, final Double amount);
}
