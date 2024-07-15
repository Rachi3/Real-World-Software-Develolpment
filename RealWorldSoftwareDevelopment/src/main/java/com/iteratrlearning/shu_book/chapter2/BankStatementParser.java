package main.java.com.iteratrlearning.shu_book.chapter2;

import java.util.List;

public interface BankStatementParser {
    BankTransaction parseFrom(String line);
    List<BankTransaction> parseLinesFrom(List<String> lines);

}
