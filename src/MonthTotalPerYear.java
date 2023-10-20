public class MonthTotalPerYear {

    int month;
    int expensesSum;
    int earningsSum;

    MonthTotalPerYear( int month) {
        this.month = month;
    }

    int ProfitMonth(){
        return earningsSum - expensesSum;
    }
}
