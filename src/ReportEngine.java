import java.util.HashMap;
import java.util.ArrayList;

public class ReportEngine {
    HashMap<Integer, MonthlyReport> monthlyReports = new HashMap<>();

    YearlyReport yearlyReport;

    FileReader filereader = new FileReader();

    ReportEngine() {
    }

    void ReadMonthlyReports() {
        for (int i = 1; i < 4; i++) {
            ArrayList<Transaction> expenses = new ArrayList<>();
            ArrayList<Transaction> earnings = new ArrayList<>();

            String fileName = "m.20210" + i + ".csv";
            ArrayList<String> lines = filereader.readFileContents(fileName);

            for (int a = 0; a < lines.size(); a++) {

                String line = lines.get(a);
                String[] lineContents = line.split(",");

                Transaction record = lineToRecord(lineContents);


                if (lineContents[1].equals("true")) {
                    expenses.add(record);
                } else {
                    earnings.add(record);
                }
            }
            monthlyReports.put(i, new MonthlyReport(2021, i, expenses, earnings));
        }
    }

    Transaction lineToRecord(String[] lineContents) {
        return new Transaction(
                lineContents[0],
                Integer.parseInt(lineContents[2]),
                Integer.parseInt(lineContents[3])
        );
    }

    void readYearlyReport() {

        HashMap<Integer, MonthTotalPerYear> monthTotals = new HashMap<>();

        MonthTotalPerYear record = null;

        String fileName = "y.2021.csv";
        ArrayList<String> lines = filereader.readFileContents(fileName);

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(1);
            String[] lineContents = line.split(",");

            if (!monthTotals.containsKey(Integer.parseInt(lineContents[0]))) {
                record = new MonthTotalPerYear(Integer.parseInt(lineContents[0]));
                if (lineContents[2].equals("true")) {
                    record.expensesSum = Integer.parseInt(lineContents[2]);
                    monthTotals.put(Integer.parseInt(lineContents[0]), record);
                } else {
                    record.earningsSum = Integer.parseInt(lineContents[2]);
                    monthTotals.put(Integer.parseInt(lineContents[0]), record);
                }
            } else {
                record = monthTotals.get(Integer.parseInt(lineContents[0]));
                if (lineContents[2].equals("true")) {
                    record.expensesSum = Integer.parseInt(lineContents[2]);
                } else {
                    record.earningsSum = Integer.parseInt(lineContents[2]);
                }
            }
        }

        yearlyReport = new YearlyReport(monthTotals);

    }

    void matchReport() {
if (monthlyReports.isEmpty() || yearlyReport == null) {
    System.out.println("Считайте месячные и годовой отчеты.");
}

for(int i = 1; i < 4; i++){
    MonthlyReport month = monthlyReports.get(i);
    MonthTotalPerYear total = yearlyReport.getTotalForMonth(i);

    int expenses = month.sumOfExpenses();
    int earnings = month.sumofEarnings();

    if (expenses != total.expensesSum){

    }
}
    }


}
