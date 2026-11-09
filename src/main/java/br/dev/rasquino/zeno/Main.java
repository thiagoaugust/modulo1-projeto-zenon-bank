package br.dev.rasquino.zeno;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    void main() {
        var transactionIngestor = new TransactionIngestor();
        List<Transaction> transactions = transactionIngestor.read("data/PS_20174392719_1491204439457_log.csv");
        transactions.forEach(IO::println);
    }
}
