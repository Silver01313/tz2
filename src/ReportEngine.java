import java.util.HashMap;
import java.util.ArrayList;

public class ReportEngine {
    HashMap<Integer, MonthlyReport> monthlyReports = new HashMap<>();

    YearlyReport yearlyReport;

    FileReader filereader = new FileReader();

    HashMap<Integer, String> nameOfMonth = new HashMap<>();

    void nameOfMonth() {
                nameOfMonth.put(1, "Январь");
                nameOfMonth.put(2, "Февраль");
                nameOfMonth.put(3, "Март");
    }

    void monthInformation(){
        for (int i = 1; i < 4; i++){
            System.out.println(nameOfMonth.get(i) + " : ");
            MonthlyReport maxEarnings = monthlyReports.get(i);
             Transaction maxEarning  = maxEarnings.getMaxEarning();
                    System.out.println("Самый прибыльный товар : " + maxEarning.name + " - " + maxEarning.totalSum());
            MonthlyReport maxExpenses = monthlyReports.get(i);
            Transaction maxExpense  = maxEarnings.getMaxEarning();
            System.out.println("Самвя большая трата : " + maxExpense.name + " - " + maxExpense.totalSum());


        }
    }

    void yearInformation(){
        System.out.println("Год - 2021");
        System.out.println("Прибыль за : ");

        for(int i = 1; i < 4; i++){

            System.out.println(nameOfMonth.get(i) + " : " + yearlyReport.profit.get(i));
        }
        System.out.println("Средний расход : " + yearlyReport.getAverageExpenses());
        System.out.println("Средний доход : " + yearlyReport.getAverageEarnings());

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

        for (int i = 1; i < 4; i++) {
            MonthlyReport month = monthlyReports.get(i);
            MonthTotalPerYear total = yearlyReport.getTotalForMonth(i);

            int expenses = month.sumOfExpenses();
            int earnings = month.sumOfEarnings();


            if (expenses != total.expensesSum || earnings != total.earningsSum) {
                System.out.println("Несоответствие в месяце : " + nameOfMonth.get(i));
                return;
            }
            System.out.println("Завершено без ошибок.");
        }
    }


}
