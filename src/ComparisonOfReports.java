import java.util.ArrayList;

public class ComparisonOfReports {

    static ArrayList<Boolean> reportReconciliation(YearlyReport yearlyReport, MonthlyReport monthlyReport) {

        ArrayList<Boolean> monthReconciliation = new ArrayList<>();

        for (int i = 0; i < monthlyReport.monthlyReportList.size(); i++) {

            int monthExpense = 0;
            int monthProfit = 0;

            for (int j = 0; j < (monthlyReport.monthlyReportList.get("m.20210" + (i + 1))).size(); j++) {

                MonthlyRecord monthArray = monthlyReport.monthlyReportList.get("m.20210" + (i + 1)).get(j);

                if (monthArray.isExpense) {
                    monthExpense += monthArray.amount;
                } else {
                    monthProfit += monthArray.amount;
                }
            }

            boolean isExpensesEqual = false;
            boolean isProfitEqual = false;

            for (int j = 0; j < yearlyReport.yearlyReportList.size(); j++) {
                YearlyRecord monthReportList = yearlyReport.yearlyReportList.get(j);
                if (monthReportList.month == (i + 1) && monthReportList.isExpense) {
                    if (monthReportList.amount == monthExpense) {
                        isExpensesEqual = true;
                    }
                } else if (monthReportList.month == (i + 1) && !monthReportList.isExpense) {
                    if (monthReportList.amount == monthProfit) {
                        isProfitEqual = true;
                    }
                }
            }
            monthReconciliation.add(isExpensesEqual && isProfitEqual);
        }

        return monthReconciliation;
    }
}
