public class YearlyRecord {
    int month;
    int amount;
    boolean isExpense;

    public YearlyRecord (String[] report) {
        month = Integer.parseInt(report[0]);
        amount = Integer.parseInt(report[1]);
        isExpense = Boolean.parseBoolean(report[2]);
    }
}
