package Exercises.Homework5;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Exercises.Exercise;
import Interface.MenuRender;

public class Exercise11 extends Exercise {
    public Exercise11(String description) {
        super(description);
    }

    private PhoneBook _phoneBook = new PhoneBook();

    @Override
    public boolean Solution() {
        var phoneBookIsOpen = true;
        while (phoneBookIsOpen) {
            int taskId = StartMenu();
            switch (taskId) {
                case 0, 4:
                    phoneBookIsOpen = false;
                    break;
                case 1:
                    SetContact();
                    PressEnterEvent("Запись добавлена.");
                    break;
                case 2:
                    FindContact();
                    PressEnterEvent(" ");
                    break;
                case 3:
                    if (_phoneBook.GetContacts().size() == 0) {
                        PressEnterEvent("Справочник пуст.");
                    } else {
                        GetAllContacts();
                    }
                    break;

                default:
                    _cm.PrintText("Неизвестная команда");
                    break;
            }
        }

        List<String> sortedNames = _phoneBook.getAllNamesList();
        System.out.println("Имена в порядке убывания числа телефонов: " + sortedNames);

        return true;
    }

    private int StartMenu() {
        Map<String, List<String>> menuData = new LinkedHashMap<String, List<String>>();
        menuData.put("ГЛАВНОЕ МЕНЮ", Arrays.asList("Добавить номер", "Найти контакт", "Просмотреть весь список"));
        menuData.put("---------", Arrays.asList("ВЫХОД"));
        return SetMenuOptions(menuData, false);
    }

    private void SetContact() {
        var name = _cm.InputText("Введите фамилию: ");
        var phone = _cm.InputText("Введите телефон: ");
        ClearField(2);
        _phoneBook.AddContact(name, phone);

    }

    private void FindContact() {
        var name = _cm.InputText("Введите фамилию: ");
        ClearField(2);

        List<String> ivanovPhones = _phoneBook.GetPhonesByName("Иванов");
        _cm.PrintText("Телефоны " + name + ": " + ivanovPhones);
    }

    private void GetAllContacts() {
        SetMenuOptions(_phoneBook.GetContacts(), true);
    }

    private void ClearField(int linescount) {
        for (int i = 1; i <= linescount; i++) {
            _cm.PrintText("\033[F", " ".repeat(100));
        }
    }

    private void PressEnterEvent(String message) {
        Map<String, List<String>> menuData = new LinkedHashMap<String, List<String>>();
        menuData.put(message, Arrays.asList("Нажмите Enter..."));
        SetMenuOptions(menuData, false);
    }

    private static int SetMenuOptions(Map<String, List<String>> menuData, Boolean showHelpMenu) {
        int consoleLines = 0;
        if (menuData.size() > 1) {
            consoleLines = 10;
        }

        MenuRender mr = new MenuRender(menuData, consoleLines, true, showHelpMenu, null, null, "", "");
        return mr.StartRenderMenu(0);
    }
}