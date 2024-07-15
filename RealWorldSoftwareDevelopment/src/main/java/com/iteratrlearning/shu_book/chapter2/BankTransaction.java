package main.java.com.iteratrlearning.shu_book.chapter2;

import java.time.LocalDate;
import java.util.Objects;

//은행 거래 내역에 대한 정보를 담고 있는 클래스
public class BankTransaction {
    private final LocalDate date;
    private final double amount;
    private final String description;

    public BankTransaction(final LocalDate date, final double amount, final String description){
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public String toString(){
        return "BankTransaction{" +
                "date="+date+
                ", amount="+amount+
                ", description="+description+'\''+
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(o==null||getClass()!=o.getClass()) return false;

        BankTransaction that = (BankTransaction) o;

        return Double.compare(that.amount,amount)==0&&
                date.equals(that.date)&&
                description.equals(that.description);
    }

    @Override
    public int hashCode(){
        return Objects.hash(date,amount,description);
    }
}
