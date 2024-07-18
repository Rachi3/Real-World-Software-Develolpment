package main.java.com.iteratrlearning.shu_book.chapter3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {
    private static final DateTimeFormatter DATE_PATTERN
            = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public BankTransaction parseFrom(String line){
        final String[] columns = line.split(",");

        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);//형식일치하지 않으면 오류발생
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];

        return new BankTransaction(date,amount,description);
    }//한 줄 읽어서 트랜잭션 정보 가져오기

    /*
    *
    * */
    @Override
    public List<BankTransaction> parseLinesFrom(List<String> lines){
        final List<BankTransaction> bankTransactions = new ArrayList<>();

        for(final String line: lines){
            bankTransactions.add(parseFrom(line));
        }

        return bankTransactions;
    }//여러줄 읽어서 리스트로 반환
}
