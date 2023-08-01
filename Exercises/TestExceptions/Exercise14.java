package Exercises.TestExceptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Exercises.Exercise;

public class Exercise14 extends Exercise {
    private static final int REQUIRED_FIELDS = 6;

    public Exercise14(String description) {
        super(description);
    }

    @Override
    public boolean solution() {
        cmdManager.printText();
        String input = cmdManager
                .inputText("Введите данные в формате: Фамилия Имя Отчество, дата рождения, номер телефона и пол:\n");
        String[] data = input.split(" ");
        if (data.length != REQUIRED_FIELDS) {
            cmdManager.printText("Ошибка: неверное количество данных");
            return false;
        }
        String lastName = data[0];
        String firstName = data[1];
        String middleName = data[2];
        LocalDate birthDate;
        try {
            birthDate = LocalDate.parse(data[3], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (Exception e) {
            cmdManager.printText("Ошибка: неверный формат даты рождения");
            return false;
        }
        long phoneNumber;
        try {
            phoneNumber = Long.parseLong(data[4]);
        } catch (NumberFormatException e) {
            cmdManager.printText("Ошибка: неверный формат номера телефона");
            return false;
        }
        char gender;
        if (data[5].length() != 1 || !("fm".contains(data[5]))) {
            cmdManager.printText("Ошибка: неверный формат пола");
            return false;
        } else {
            gender = data[5].charAt(0);
        }
        String folderData = "data\\";
        File folder = new File(folderData);
        if (!folder.exists())
            folder.mkdir();
        String filename = folderData + lastName + ".txt";
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write("Lastname=" + lastName + ";Firstname=" + firstName + ";Midlename=" + middleName
                    + ";BirthDate=" + birthDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                    + ";Phone=" + phoneNumber + ";Gender=" + gender + "\n");
            cmdManager.printText("Данные успешно записаны в файл " + filename);
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}