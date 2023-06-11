package Controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Interface.ConsoleManager;

public class JsonParseController {
    public static Map<String, String> JsonToMap(String strJson) {
        Map<String, String> map = new HashMap<>();
        Matcher matcher = PatternMatcher(strJson, "\"(.*?)\":\"(.*?)\"");
        while (matcher.find()) {
            map.put(matcher.group(1), matcher.group(2));
        }
        return map;
    }

    public static String ReadJsonString(ConsoleManager cm, String message) {
        String strJson = cm.InputText(message + "\n");
        var filePath = strJson.replace("\"", "");

        if (IsFilePath(filePath)) {
            int strJsonLength = strJson.length();
            strJson = GetFromFile(filePath);
            DrawLineFromFile(cm, strJsonLength, strJson);
        } else {
            cm.PrintText();
        }

        return strJson;
    }

    public static Map<String, String> ReadJsonToMap(ConsoleManager cm, String message) {
        return JsonToMap(ReadJsonString(cm, message));
    }

    public static List<Map<String, String>> ReadJsonToArray(ConsoleManager cm, String message) {
        var strJson = ReadJsonString(cm, message);
        String strJsonArray = "";
        var matcher = PatternMatcher(strJson, "\\[(.*?)\\]");
        while (matcher.find()) {
            strJsonArray = matcher.group(1);
        }
        String[] jsonArray = strJsonArray.replace("},{", "}\n{").split("\n");

        var jsonDataArray = new ArrayList<Map<String, String>>();
        for (String jsonString : jsonArray) {
            jsonDataArray.add(JsonToMap(jsonString));
        }
        return jsonDataArray;
    }

    private static Matcher PatternMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

    private static Boolean IsFilePath(String filePath) {
        File file = new File(filePath);
        return file.isFile();
    }

    private static String GetFromFile(String filePath) {
        try {
            return SaveLoadFileController.LoadFromFile(filePath)[0];
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static void DrawLineFromFile(ConsoleManager cm, int charCount, String strLine) {
        cm.PrintText("\033[F", " ".repeat(charCount));
        cm.PrintText("\b".repeat(charCount), strLine + "\n\n");
    }
}
