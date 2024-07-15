package main.java.com.iteratrlearning.shu_book.chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {
    private static final String RESOURCES = "src/main/resources/";
    private static final String startDate="30-01-2017" ,endDate="02-02-2017";

    public void analyze(final String fileName,final BankStatementParser bankStatementParser)throws IOException{

        final Path path = Paths.get(RESOURCES + fileName);//파일이 저장된 경로
        final List<String> lines = Files.readAllLines(path);//파일에 있는 내용 한줄씩 읽어서 List에 저장

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);//BankTranscation 리스트 만든후 lines 파싱해서 가져옴

        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
        //트랜잭션에 대한 연산을 처리할 클래스 내부 구현을 하면 다른곳에서 못쓰니 클래스로 구현

        collectSummary(bankStatementProcessor);
    }

    private static void collectSummary(BankStatementProcessor bankStatementProcessor) {
        System.out.println("The total for all transaction is "+ bankStatementProcessor.calculateTotal());
        System.out.println("The total for all transaction in January is "+ bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("The total for all transaction in February is "+ bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));
        System.out.println("The total salary received is "+ bankStatementProcessor.calculateTotalForCategory("Salary"));
        System.out.println("The total for all transactions from " + startDate + " to " + endDate + " is " + bankStatementProcessor.calculateTotalInSpecificRange(startDate,endDate));
        System.out.println("The max transaction from"+startDate+" to "+endDate+" is "+
                bankStatementProcessor.findMaxTransactionAmountInRange(startDate,endDate).getDate()+
                " Amount is "+bankStatementProcessor.findMaxTransactionAmountInRange(startDate,endDate).getAmount());
        System.out.println("The min transaction from"+startDate+" to "+endDate+" is "+
                bankStatementProcessor.findMinTransactionAmountInRange(startDate,endDate).getDate()+
                " Amount is "+bankStatementProcessor.findMinTransactionAmountInRange(startDate,endDate).getAmount());
    }


}
