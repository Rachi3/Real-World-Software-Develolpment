package main.java.com.iteratrlearning.shu_book.chapter2;

import java.io.IOException;

public class MainApplication {
    public static void main(String[] args) throws IOException {

        final BankStatementAnalyzer  bankStatementAnalyzer = new BankStatementAnalyzer();
        //BankStatementAnalyzer 생성
        final BankStatementParser bankStatementParser= new BankStatementCSVParser();
        //파서 인터프리터 만든후 CSV로 내부구현

        bankStatementAnalyzer.analyze("bank-data-simple.csv", bankStatementParser);
        //파싱 방법과 파일 이름 넘겨서 분석하게 하기
    }
}
