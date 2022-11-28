import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class YearlyReport {


    ArrayList<String[]> yearlyReportList = new ArrayList<>();
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
        for (String list : reportList) {
            yearlyReportList.add(list.split(","));
        }
        System.out.println("Годовой отчет успешно считан");
    }

    void yearlyReportWriter() {

        int monthCount = 0;
        int sumOfIncome = 0;
        int sumOfExpense = 0;
        HashMap<String, Integer> profitByMonth = new HashMap<>();

        for (int i = 1; i < yearlyReportList.size(); i++) {
            monthCount++;
            String[] yearlyReport = yearlyReportList.get(i);

            if (!profitByMonth.containsKey(yearlyReport[0])) {
                profitByMonth.put(yearlyReport[0], 0);
            }

            if (Boolean.parseBoolean(yearlyReport[2])) {
                profitByMonth.put(yearlyReport[0], profitByMonth.get(yearlyReport[0]) - Integer.parseInt(yearlyReport[1]));
                sumOfExpense += Integer.parseInt(yearlyReport[1]);
            } else {
                profitByMonth.put(yearlyReport[0], profitByMonth.get(yearlyReport[0]) + Integer.parseInt(yearlyReport[1]));
                sumOfIncome += Integer.parseInt(yearlyReport[1]);
            }

        }

        for (int i = 1; i < profitByMonth.size() + 1; i++) {
            System.out.println("Прибыль за " + monthsNames[i - 1] + " составила: " + profitByMonth.get("0" + i));
        }

        System.out.println("Средний доход: " + (sumOfIncome / monthCount));
        System.out.println("Средний расход: " + (sumOfExpense / monthCount));
    }


}

