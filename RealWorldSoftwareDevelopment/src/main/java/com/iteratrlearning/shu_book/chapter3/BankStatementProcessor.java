package main.java.com.iteratrlearning.shu_book.chapter3;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /* 이처럼 직접 비교를 한다면 논리가 중복되어서 코드 복잡도가 올라감
    public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount){
        final List<BankTransaction> result = new ArrayList<>();
        for(final BankTransaction bt : bankTransactions) {
            if(bt.getAmount() >= amount) {
                result.add(bt);
            }
        }

        return result;
    }

    public List<BankTransaction> findTransactionsInMonth(final Month month){
        final List<BankTransaction> result = new ArrayList<>();
        for(final BankTransaction bt : bankTransactions) {
            if(bt.getDate().getMonth() == month) {
                result.add(bt);
            }
        }

        return result;
    }
    */
     
    //내가 구현한 내용
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


    private Map<String,Double> GroupByMonth(){
        //달별로
        Map<String,Double> grouyByMonth=new HashMap<>();

        for(final BankTransaction bt : bankTransactions) {
            if(grouyByMonth.get(bt.getDate().getMonth().toString()) == null) {
                grouyByMonth.put(bt.getDate().getMonth().toString(), bt.getAmount());
            }else{
                grouyByMonth.merge(bt.getDate().getMonth().toString(), bt.getAmount(), Double::sum);
            }
        }

        return grouyByMonth;
    }

    private Map<String,Double> GroupByDescription(){
        //달별로
        Map<String,Double> grouyByDescription=new HashMap<>();

        for(final BankTransaction bt : bankTransactions) {
            if(grouyByDescription.get(bt.getDescription()) == null) {
                grouyByDescription.put(bt.getDescription(), bt.getAmount());
            }else{
                grouyByDescription.merge(bt.getDescription(), bt.getAmount(), Double::sum);
            }
        }

        return grouyByDescription;
    }

    public void printHistogram() {
        // 달별 그룹화
        Map<String, Double> groupedByMonth = GroupByMonth();

        // 설명별 그룹화
        Map<String, Double> groupedByDescription = GroupByDescription();

        // 달별 그룹 내에서 설명별로 출력
        System.out.println("Histogram by Month and Description:");
        for (String month : groupedByMonth.keySet()) {
            System.out.println("Month: " + month);

            double totalInMonth = groupedByMonth.get(month);

            // 각 설명에 대해 해당 달의 총액과 비교하여 출력
            for (Map.Entry<String, Double> entry : groupedByDescription.entrySet()) {
                String description = entry.getKey();
                double amount = entry.getValue();
                System.out.printf("  %s: %.2f \n", description, amount);
            }

            System.out.println(month+"'s Total: "+totalInMonth);
        }
    }

}
