package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        int[] salaries = new int[names.length];

        for (String record : data) {
            String[] parts = record.split(" ");
            LocalDate date = LocalDate.parse(parts[0], formatter);
            if (!date.isBefore(from) && !date.isAfter(to)) {
                String name = parts[1];
                int hours = Integer.parseInt(parts[2]);
                int rate = Integer.parseInt(parts[3]);
                int salary = hours * rate;

                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salaries[i] += salary;
                        break;
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();

        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }

        return result.toString();
    }
}
