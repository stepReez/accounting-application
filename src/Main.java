import java.util.ArrayList;
import java.util.HashMap;
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
                    System.out.println("Месячные отчеты успешно считаны");
                    break;

                case 2 :
                    yearlyReport = new YearlyReport();
                    isYearlyReportInit = true;
                    System.out.println("Годовой отчет успешно считан");
                    break;

                case 3 :
                    if (isMonthlyReportInit && isYearlyReportInit) {
                        ArrayList<Boolean> monthReconciliation = ComparisonOfReports.reportReconciliation(yearlyReport, monthlyReport);

                        for (int i = 0; i < monthlyReport.monthlyReportList.size(); i++) {
                            if (!monthReconciliation.get(i)) {
                                System.out.println("Несоответствие данных в " + monthlyReport.monthsNames[i]);
                            }
                        }
                        System.out.println("Сверка завершена");
                    } else {
                        System.out.println("Сначала считайте месячные и годовой отчет");
                    }
                    break;

                case 4 :
                    if (isMonthlyReportInit) {
                        for (int i = 0; i < monthlyReport.monthlyReportList.size(); i++) {

                            System.out.println("Отчет за: " + monthlyReport.monthsNames[i]);
                            System.out.println("Самый прибыльный товар: " + monthlyReport.monthlyMaxProfit(i)[0] +
                                    ". Всего заработано: " + monthlyReport.monthlyMaxProfit(i)[1]);

                            System.out.println("Самая большая трата в размере: " + monthlyReport.monthlyMaxExpense(i)[1] +
                                    " была совершена за: " + monthlyReport.monthlyMaxExpense(i)[0]);

                            System.out.println();

                        }
                    } else {
                        System.out.println("Сначала считайте месячные отчеты");
                    }
                    break;

                case 5 :
                    if (isYearlyReportInit) {

                        HashMap<Integer, Integer> profitByMonth = yearlyReport.yearlyReportWriter();
                        for (int i = 0; i < profitByMonth.size(); i++) {
                            System.out.println("Прибыль за " + yearlyReport.monthsNames[i] + " составила: " + profitByMonth.get(i + 1));
                        }
                        System.out.println("Средний доход: " + yearlyReport.averageIncome());
                        System.out.println("Средний расход: " + yearlyReport.averageExpense());

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

