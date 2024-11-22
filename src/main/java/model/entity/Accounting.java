package model.entity;


import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder


public class Accounting {
    private double totalSales;
    private double totalExpenses;
    private double totalSalaries;
    private double profit;

    public void addSale (double amount) {}

    public void addExpense(double amount) {}

    public void addSalaries(double amount) {}

    public void calculateExpenses() {}

    public void calculateProfit() {}

    public void generateMonthlyReport() {}

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
