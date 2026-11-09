package br.dev.rasquino.zeno;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TransactionIngestor {

    public List<Transaction> read(String fileName){

        try {
            List<String> lines = Files.readAllLines(Path.of(fileName));
            return lines.stream()
                    .skip(1)
                    .limit(10)
                    .map(this::parseTransaction)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo " + fileName, e);
        }
    }

    private Transaction parseTransaction(String line) {
        String[] chunks = line.split(",");
        TransactionType transactionType = TransactionType.valueOf(chunks[1]);
        var origin = new TransactionCustomer(chunks[3], new BigDecimal(chunks[4]), new BigDecimal(chunks[5]));
        var recipient = new TransactionCustomer(chunks[6], new BigDecimal(chunks[7]), new BigDecimal(chunks[8]));
        return new Transaction(
                Integer.parseInt(chunks[0]),
                transactionType,
                new BigDecimal(chunks[2]),
                origin,
                recipient,
                chunks[9].equals("1"),
                chunks[10].equals("1"));

    }
}
