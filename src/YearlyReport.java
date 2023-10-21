import java.util.HashMap;
public class YearlyReport {
    HashMap<Integer, MonthTotalPerYear> monthTotals;
    HashMap<Integer, Integer> profit = new HashMap<>();
    YearlyReport(HashMap<Integer, MonthTotalPerYear> monthTotals){
        this.monthTotals = monthTotals;

    }



    int getAverageEarnings( HashMap<Integer, MonthTotalPerYear> monthTotals) {
        MonthTotalPerYear object;
        int sum = 0;
        for (int i = 1; i <= monthTotals.size(); i++) {
           object = monthTotals.get(i);
           sum = sum + object.earningsSum;
        }
        sum = sum / monthTotals.size();
        return sum;
    }

    int getAverageExpenses( HashMap<Integer, MonthTotalPerYear> monthTotals) {
        MonthTotalPerYear object;
        int sum = 0;
        for (int i = 1; i <= monthTotals.size(); i++) {
            object = monthTotals.get(i);
            sum = sum + object.expensesSum;
        }
        sum = sum / monthTotals.size();
        return sum;
    }






    MonthTotalPerYear getTotalForMonth(int month) {
        return monthTotals.get(month);
    }
}
