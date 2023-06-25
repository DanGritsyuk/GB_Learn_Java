package Exercises.Homework2;

import java.util.Map;

import Controllers.JsonXMLParseController;
import Exercises.Exercise;

public class Exercise5 extends Exercise {
    public Exercise5(String description) {
        super(description);
    }

    @Override
    public boolean Solution() {
        var message = "Введите строку параметров или путь до файла, где она хранится:";
        Map<String, String> map = JsonXMLParseController.ReadJsonToMap(cmdManager, message);

        StringBuilder whereClause = new StringBuilder();
        boolean firstParam = true;
        for (String key : map.keySet()) {
            String value = map.get(key);
            if (!value.equals("null")) {
                if (firstParam) {
                    whereClause.append(" WHERE ");
                    firstParam = false;
                } else {
                    whereClause.append(" AND ");
                }
                whereClause.append(key).append("=").append(value);
            }
        }

        String sqlQuery = "select * from students" + whereClause.toString();
        cmdManager.PrintText(sqlQuery);
        return false;
    }
}
