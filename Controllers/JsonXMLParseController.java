package Controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Interface.ConsoleManager;

public class JsonXMLParseController {
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

    public static String MapStringSetToXML(Map<String, Set<String>> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("<map>");
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            sb.append("<entry>");
            sb.append("<key>").append(entry.getKey()).append("</key>");
            sb.append("<values>");
            for (String value : entry.getValue()) {
                sb.append("<value>").append(value).append("</value>");
            }
            sb.append("</values>");
            sb.append("</entry>");
        }
        sb.append("</map>");
        return sb.toString();
    }

    public static Map<String, Set<String>> MapStringSetFromXML(String xml) {
        Map<String, Set<String>> map = new HashMap<>();
        int startIndex = xml.indexOf("<entry>");
        while (startIndex != -1) {
            int endIndex = xml.indexOf("</entry>", startIndex);
            String entryXml = xml.substring(startIndex, endIndex + 8);
            int keyStartIndex = entryXml.indexOf("<key>") + 5;
            int keyEndIndex = entryXml.indexOf("</key>");
            String key = entryXml.substring(keyStartIndex, keyEndIndex);
            Set<String> values = new HashSet<>();
            int valueStartIndex = entryXml.indexOf("<value>");
            while (valueStartIndex != -1) {
                int valueEndIndex = entryXml.indexOf("</value>", valueStartIndex);
                String value = entryXml.substring(valueStartIndex + 7, valueEndIndex);
                values.add(value);
                valueStartIndex = entryXml.indexOf("<value>", valueEndIndex);
            }
            map.put(key, values);
            startIndex = xml.indexOf("<entry>", endIndex);
        }
        return map;
    }
}
