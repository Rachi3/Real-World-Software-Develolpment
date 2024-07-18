package main.java.com.iteratrlearning.shu_book.chapter3;

import java.util.List;

public interface BankStatementParser {
    BankTransaction parseFrom(String line);
    List<BankTransaction> parseLinesFrom(List<String> lines);

}
