package Exercises.Homework2;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controllers.SaveLoadFileController;
import Exercises.Exercise;

public class Exercise5 extends Exercise {
    public Exercise5(String description) {
        super(description);
    }

    @Override
    public boolean Solution() {
        var message = "Введите строку параметров или путь до файла, где она хранится:";
        String strJson = _cm.InputText(message + "\n");
        var filePath = strJson.replace("\"", "");

        if (IsFilePath(filePath)) {
            int strJsonLength = strJson.length();
            strJson = GetFromFile(filePath);
            DrawLineInFile(strJsonLength, strJson);
        }

        Map<String, String> map = new HashMap<>();
        Pattern pattern = Pattern.compile("\"(.*?)\":\"(.*?)\"");
        Matcher matcher = pattern.matcher(strJson);
        while (matcher.find()) {
            map.put(matcher.group(1), matcher.group(2));
        }

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
        _cm.PrintText(sqlQuery);
        return false;
    }

    private Boolean IsFilePath(String filePath) {
        File file = new File(filePath);
        return file.isFile();
    }

    private String GetFromFile(String filePath) {
        try {
            return SaveLoadFileController.LoadFromFile(filePath)[0];
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void DrawLineInFile(int charCount, String strLine) {
        _cm.PrintText("\033[F", " ".repeat(charCount));
        _cm.PrintText("\b".repeat(charCount), strLine + "\n\n");
    }
}
