import java.util.Scanner;
 public class Main {

    public static void main(String[] args) {
        System.out.println("Здравствуйте. Введите команду, чтобы продолжить.");

                String input;
        Scanner scanner = new Scanner(System.in);
        ReportEngine engine = new ReportEngine();

        while (true) {
            System.out.println("1 - Считать все месячные отчеты.");
            System.out.println("2 - Считать годовой отчет.");
            System.out.println("3 - Сверить отчеты.");
            System.out.println("4 - Вывести информацию о всех месячных отчетах.");
            System.out.println("5 - Вывести информацию о годовом отчете.");
            System.out.println("Нажмите Enter для заверешения работы.");

            input = scanner.nextLine();
             if (input.equals("1")){
                 System.out.println("Считывание месячных отчетов.");
                 engine.ReadMonthlyReports();
                 System.out.println("Считывание завершено.");
             } else if (input.equals("2")) {
                 System.out.println("Считывание годового отчета.");
                 engine.readYearlyReport();
                 System.out.println("Считывание завершено.");
             } else if (input.equals("3")) {
                 System.out.println("Сверка данных.");
                 engine.matchReport();
             } else if (input.equals("4")) {
                 System.out.println("Информация о месячных отчетах : ");
                 engine.monthInformation();
             } else if (input.equals("5")) {
                 System.out.println("Информация о годовом отчете : ");
                 engine.yearInformation();
             } else if (input.equals("Enter")) {
                 System.out.println("Завершение работы");
                 return;
             } else {
                 System.out.println("Такой команды не существует.");
             }
        }
    }
}

