package com.learn.streams;

import java.util.*;

import static java.util.stream.Collectors.*;

class Transaction {
    private final long amount;
    private final Currency currency;
    private final String city;

    public Transaction(long amount, Currency currency, String city) {
        this.amount = amount;
        this.currency = currency;
        this.city = city;
    }

    public long getAmount() { return amount; }
    public Currency getCurrency() { return currency; }
    public String getCity() { return city; }

    public enum Currency { USD, EUR; }
}

public class Collectors {

    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(100, Transaction.Currency.EUR, "Berlin"),
                new Transaction(80, Transaction.Currency.USD, "New York"),
                new Transaction(50, Transaction.Currency.EUR, "Prague"),
                new Transaction(150, Transaction.Currency.EUR, "Munich")
        );

        // grouping by currency made easy by collectors
        Map<Transaction.Currency, List<Transaction>> transactionsByCurrency = transactions.stream()
                .collect(groupingBy(Transaction::getCurrency));

        Comparator<Transaction> amountComparator = Comparator.comparingLong(Transaction::getAmount);
        for (Transaction.Currency currency : transactionsByCurrency.keySet()) {
            Optional<Transaction> maxAmount = transactionsByCurrency.get(currency).stream()
                    .collect(maxBy(amountComparator));
        }

    }

}
