import java.util.ArrayList;
public class MonthlyReport {

    int year;

    int month;

    ArrayList<Transaction> expenses;

    ArrayList<Transaction> earnings;

    MonthlyReport(int year, int month, ArrayList<Transaction> expenses, ArrayList<Transaction> earnings) {
        this.year = year;
        this.month = month;
        this.expenses = expenses;
        this.earnings = earnings;
    }

    int sumOfExpenses() {
        return countSum(expenses);
    }

    int sumOfEarnings() {

        return countSum(expenses);
    }

    int countSum(ArrayList<Transaction> records) {

        int sum = 0;

        for (int i = 0; i < records.size(); i++) {
            Transaction object = records.get(0);
            sum = object.totalSum() + sum;
        }
        return sum;
    }

    Transaction getMaxEarning() {

        return getMax(earnings);
    }
     Transaction getMaxExpense() {

         return getMax(expenses);
     }

     Transaction getMax (ArrayList<Transaction> records) {

         Transaction max = records.get(0);

        for (int i = 1; i < records.size(); i++) {
            Transaction candidate = records.get(i);
            if (candidate.totalSum() > max.totalSum()){
                max = candidate;
            }
        }
         return max;
     }

}
