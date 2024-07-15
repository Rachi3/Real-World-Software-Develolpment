package main.java.com.iteratrlearning.shu_book.chapter2;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotal() {
        double total = 0;
        for(final BankTransaction bt : bankTransactions) {
            total += bt.getAmount();
        }

        return total;
    }

    public double calculateTotalInMonth(final Month month){
        double total = 0;

        for(final BankTransaction bt : bankTransactions) {

            if(bt.getDate().getMonth() == month) {
                total +=bt.getAmount();
            }
        }

        return total;
    }

    public double calculateTotalForCategory(final String category) {
        double total = 0;

        for(final BankTransaction bt : bankTransactions) {
            if(bt.getDescription().equals(category)) {
                total +=bt.getAmount();
            }
        }

        return total;
    }

    public double calculateTotalInSpecificRange(final String startDate, final String endDate){
        //1.dd-MM-yyyy로 나눈후 해당 범위 내의 있는 것들 찾아내기
        //2.범위 비교를 위해서 사용가능한 API가 있는지
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        double total=0.d;
        try{
            LocalDate LstartDate= LocalDate.parse(startDate, DATE_PATTERN);
            LocalDate LendDate= LocalDate.parse(endDate, DATE_PATTERN);

            for(final BankTransaction bt : bankTransactions) {
                LocalDate targetDate=bt.getDate();
                
                if(!targetDate.isBefore(LstartDate) && !targetDate.isAfter(LendDate)) {
                    total+=bt.getAmount();
                }//범위내의 모든값 더하기
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  total;
    }

    public BankTransaction findMaxTransactionAmountInRange(final String startDate, final String endDate) {
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        BankTransaction maxTransaction=null;
        try{
            LocalDate LstartDate = LocalDate.parse(startDate, DATE_PATTERN);
            LocalDate LendDate = LocalDate.parse(endDate, DATE_PATTERN);

            for(final BankTransaction bt : bankTransactions) {
                LocalDate targetDate=bt.getDate();

                if(!targetDate.isBefore(LstartDate) && !targetDate.isAfter(LendDate)) {
                    if (maxTransaction == null || bt.getAmount() > maxTransaction.getAmount()) {
                        maxTransaction = bt;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return maxTransaction;
    }

    public BankTransaction findMinTransactionAmountInRange(final String startDate, final String endDate) {
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        BankTransaction minTransaction = null;
        try {
            LocalDate LstartDate = LocalDate.parse(startDate, DATE_PATTERN);
            LocalDate LendDate = LocalDate.parse(endDate, DATE_PATTERN);

            for (final BankTransaction bt : bankTransactions) {
                LocalDate targetDate = bt.getDate();

                if (!targetDate.isBefore(LstartDate) && !targetDate.isAfter(LendDate)) {
                    // 최초의 유효한 트랜잭션을 초기 최소값으로 설정
                    if (minTransaction == null || bt.getAmount() < minTransaction.getAmount()) {
                        minTransaction = bt;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return minTransaction;
    }



}
