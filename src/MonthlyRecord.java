public class MonthlyRecord {
    String itemName;
    boolean isExpense;
    int quantity;
    int sumOfOne;
    int amount;

    public MonthlyRecord(String[] report) {
        itemName = report[0];
        isExpense = Boolean.parseBoolean(report[1]);
        quantity = Integer.parseInt(report[2]);
        sumOfOne = Integer.parseInt(report[3]);
        amount = quantity * sumOfOne;
    }
}
