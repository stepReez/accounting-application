import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MonthlyReport {

    HashMap<String, ArrayList<String[]>> monthlyReportList = new HashMap<>();
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
        for (int i = 1; i < 4; i++) { // Не совсем понимаю, как считать неизвестное количество файлов

            ArrayList<String> reportList;
            String path = "./resources/m.20210" + i + ".csv";
            reportList = (ArrayList<String>) readFileContents(path);
            ArrayList<String[]> arrayList = new ArrayList<>();

            for (String list : reportList) {
                arrayList.add(list.split(","));
            }
            monthlyReportList.put("m.20210" + i, arrayList);
        }
        System.out.println("Месячные отчеты успешно считаны");
    }

    void monthlyReportWriter() {
        for (int i = 1; i < monthlyReportList.size() + 1; i++) {
            System.out.println("Отчет за: " + monthsNames[i - 1]);

            int maxExpense = 0;
            String maxExpenseName = "";

            int maxProfit = 0;
            String maxProfitName = "";

            for (int j = 1; j < (monthlyReportList.get("m.20210" + i)).size(); j++) {

                String[] monthArray = monthlyReportList.get("m.20210" + i).get(j);

                if (Boolean.parseBoolean(monthArray[1]) && Integer.parseInt(monthArray[2]) * Integer.parseInt(monthArray[3]) > maxExpense) {
                    maxExpense = Integer.parseInt(monthArray[2]) * Integer.parseInt(monthArray[3]);
                    maxExpenseName = monthArray[0];
                } else if (!(Boolean.parseBoolean(monthArray[1])) && Integer.parseInt(monthArray[2]) * Integer.parseInt(monthArray[3]) > maxProfit) {
                    maxProfit = Integer.parseInt(monthArray[2]) * Integer.parseInt(monthArray[3]);
                    maxProfitName = monthArray[0];
                }
            }
            System.out.println("Самый прибыльный товар: " + maxProfitName + ". Всего заработано: " + maxProfit);
            System.out.println("Самая большая трата в размере: " + maxExpense + " была совершена за: " + maxExpenseName);
            System.out.println();
        }
    }


}

