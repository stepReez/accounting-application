import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class YearlyReport {


    ArrayList<YearlyRecord> yearlyReportList = new ArrayList<>();
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

    public YearlyReport() {
        ArrayList<String> reportList;
        String path = "./resources/y.2021.csv";
        reportList = (ArrayList<String>) readFileContents(path);
        for (int i = 1; i < reportList.size(); i ++) {
            YearlyRecord record = new YearlyRecord(reportList.get(i).split(","));
            yearlyReportList.add(record);
        }
    }

    HashMap<Integer,Integer> yearlyReportWriter() {

        int sumOfIncome = 0;

        HashMap<Integer, Integer> profitByMonth = new HashMap<>();

        for (YearlyRecord year : yearlyReportList) {
            if (!profitByMonth.containsKey(year.month)) {
                profitByMonth.put(year.month, 0);
            }

            if (year.isExpense) {
                profitByMonth.put(year.month, profitByMonth.get(year.month) - year.amount);
            } else {
                profitByMonth.put(year.month, profitByMonth.get(year.month) + year.amount);
            }
        }
        return profitByMonth;
    }

    int averageExpense() {
        int sumOfExpense = 0;

        for (YearlyRecord year : yearlyReportList) {

            if (year.isExpense) {
                sumOfExpense += year.amount;
            }
        }
        return sumOfExpense/yearlyReportList.size();
    }

    int averageIncome() {
        int sumOfIncome = 0;
        for (YearlyRecord year : yearlyReportList) {

            if (!year.isExpense) {
                sumOfIncome += year.amount;
            }
        }
        return sumOfIncome/yearlyReportList.size();
    }


}

