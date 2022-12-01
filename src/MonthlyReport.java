import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MonthlyReport {

    HashMap<String, ArrayList<MonthlyRecord>> monthlyReportList = new HashMap<>();
    String[] monthsNames = {"Январь", "Февраль", "Март", "Апрель",
            "Май", "Июнь", "Июль", "Август",
            "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }

    public MonthlyReport() {
        for (int i = 1; i < 4; i++) {

            ArrayList<String> reportList;
            String path = "./resources/m.20210" + i + ".csv";
            reportList = (ArrayList<String>) readFileContents(path);
            ArrayList<MonthlyRecord> arrayList = new ArrayList<>();

            for (int j = 1; j < reportList.size(); j++) {
                String[] report = reportList.get(j).split(",");
                MonthlyRecord record = new MonthlyRecord(report);
                arrayList.add(record);
            }
            monthlyReportList.put("m.20210" + i, arrayList);
        }
    }

    String[] monthlyMaxExpense(int i) {

            int maxExpense = 0;
            String maxExpenseName = "";

            for (int j = 0; j < (monthlyReportList.get("m.20210" + (i + 1))).size(); j++) {

                MonthlyRecord month = monthlyReportList.get("m.20210" + (i + 1)).get(j);

                if (month.isExpense && month.amount > maxExpense) {
                    maxExpense = month.amount;
                    maxExpenseName = month.itemName;
                }
            }
            String[] expense = {maxExpenseName, Integer.toString(maxExpense)};
            return expense;
    }

    String[] monthlyMaxProfit(int i) {


        int maxProfit = 0;
        String maxProfitName = "";

        for (int j = 0; j < (monthlyReportList.get("m.20210" + (i + 1))).size(); j++) {

            MonthlyRecord month = monthlyReportList.get("m.20210" + (i + 1)).get(j);

            if (!(month.isExpense) && month.amount > maxProfit) {
                maxProfit = month.amount;
                maxProfitName = month.itemName;
            }
        }
        String[] profit = {maxProfitName, Integer.toString(maxProfit)};

        return profit;

    }

}

