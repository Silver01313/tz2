import java.util.HashMap;
public class YearlyReport {
    HashMap<Integer, MonthTotalPerYear> monthTotals;
    YearlyReport(HashMap<Integer, MonthTotalPerYear> monthTotals){
        this.monthTotals = monthTotals;
    }

    int getAverageEarnings() {
        MonthTotalPerYear object;
        int sum = 0;
        for (int i = 0; i < monthTotals.size(); i++) {
           object = monthTotals.get(i);
           sum = sum + object.earningsSum;
        }
        sum = sum / monthTotals.size();
        return sum;
    }

    int getAverageExpenses() {

    }

    HashMap<Integer, Integer> profitPerMonth() {
        HashMap<Integer, Integer> profit = new HashMap<>();

        for (int i = 0; i < monthTotals.size(); i++){
            MonthTotalPerYear object = monthTotals.get(i);
            profit.put(i + 1 ,object.ProfitMonth());
        }
        return profit;
    }

    MonthTotalPerYear getTotalForMonth(int month) {
        return monthTotals.get(month);
    }
}
