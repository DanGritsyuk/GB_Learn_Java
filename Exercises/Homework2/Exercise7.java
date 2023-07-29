package Exercises.Homework2;

import java.util.List;
import java.util.Map;

import Controllers.JsonXMLParseController;

import Exercises.Exercise;

public class Exercise7 extends Exercise {
    public Exercise7(String description) {
        super(description);
    }

    @Override
    public boolean solution() {
        var message = "Введите строку с данными учащихся или путь до файла, где она хранится:";
        List<Map<String, String>> studentMap = JsonXMLParseController.readJsonToArray(cmdManager, message);

        StringBuilder stringBuilder = new StringBuilder();
        for (Map<String, String> student : studentMap) {
            stringBuilder.append("Студент ").append(student.get("фамилия"))
                    .append(" получил ").append(student.get("оценка"))
                    .append(" по предмету ").append(student.get("предмет")).append(".\n");
        }

        cmdManager.printText(stringBuilder.toString());
        return false;
    }
}
