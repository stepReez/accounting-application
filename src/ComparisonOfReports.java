import java.util.ArrayList;

public class ComparisonOfReports {

    static void reportReconciliation(YearlyReport yearlyReport, MonthlyReport monthlyReport) {

        ArrayList<Boolean> monthReconciliation = new ArrayList<>();

        for (int i = 1; i < monthlyReport.monthlyReportList.size() + 1; i++) {

            int monthExpense = 0;
            int monthProfit = 0;

            for (int j = 1; j < (monthlyReport.monthlyReportList.get("m.20210" + i)).size(); j++) {

                String[] monthArray = monthlyReport.monthlyReportList.get("m.20210" + i).get(j);

                if (Boolean.parseBoolean(monthArray[1])) {
                    monthExpense += Integer.parseInt(monthArray[2]) * Integer.parseInt(monthArray[3]);
                } else {
                    monthProfit += Integer.parseInt(monthArray[2]) * Integer.parseInt(monthArray[3]);
                }
            }

            boolean isExpensesEqual = false;
            boolean isProfitEqual = false;

            for (int j = 1; j < yearlyReport.yearlyReportList.size(); j++) {
                String[] monthReportList = yearlyReport.yearlyReportList.get(j);
                if (Integer.parseInt(monthReportList[0]) == i && Boolean.parseBoolean(monthReportList[2])) {
                    if (Integer.parseInt(monthReportList[1]) == monthExpense) {
                        isExpensesEqual = true;
                    }
                } else if (Integer.parseInt(monthReportList[0]) == i && !Boolean.parseBoolean(monthReportList[2])) {
                    if (Integer.parseInt(monthReportList[1]) == monthProfit) {
                        isProfitEqual = true;
                    }
                }
            }
            monthReconciliation.add(isExpensesEqual && isProfitEqual);
        }

        for (int i = 0; i < monthlyReport.monthlyReportList.size(); i++) {
            if (!monthReconciliation.get(i)) {
                System.out.println("Несоответствие данных в " + monthlyReport.monthsNames[i]);
            }
        }

        System.out.println("Сверка завершена");
    }
}
