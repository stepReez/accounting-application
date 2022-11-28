import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        MonthlyReport monthlyReport = null;
        boolean isMonthlyReportInit = false;

        YearlyReport yearlyReport = null;
        boolean isYearlyReportInit = false;

        while (true) {
            printMenu();
            int inputMenuItem = scanner.nextInt();

            switch (inputMenuItem) {
                case 1 :
                    monthlyReport = new MonthlyReport();
                    isMonthlyReportInit = true;
                    break;
                case 2 :
                    yearlyReport = new YearlyReport();
                    isYearlyReportInit = true;
                    break;
                case 3 :
                    if (isMonthlyReportInit && isYearlyReportInit) {
                        ComparisonOfReports.reportReconciliation(yearlyReport, monthlyReport);
                    } else {
                        System.out.println("Сначала считайте месячные и годовой отчет");
                    }
                    break;
                case 4 :
                    if (isMonthlyReportInit) {
                        monthlyReport.monthlyReportWriter();
                    } else {
                        System.out.println("Сначала считайте месячные отчеты");
                    }
                    break;
                case 5 :
                    if (isYearlyReportInit) {
                        yearlyReport.yearlyReportWriter();
                    } else {
                        System.out.println("Сначала считайте годовой отчет");
                    }
                    break;
                case 0 :
                    System.out.println("Выход из программы");
                    break;
                default:
                    System.out.println("Выбраная команда не существует");
                    break;
            }
            if (inputMenuItem == 0) {
                break;
            }
        }
    }

    public static void printMenu() {
        System.out.println("Выберите команду");
        System.out.println("1. Считать все месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию о всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
        System.out.println("0. Выйти из программы");
    }
}

