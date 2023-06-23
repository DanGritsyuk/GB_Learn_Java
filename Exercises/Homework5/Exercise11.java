package Exercises.Homework5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import Exercises.Exercise;
import Interface.MenuRender;

public class Exercise11 extends Exercise {
    public Exercise11(String description) {
        super(description);
    }

    private PhoneBookModel _phoneBook = new PhoneBookModel();

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
                    break;
                case 3:
                    GetAllContacts();
                    break;

                default:
                    _cm.PrintText("Неизвестная команда");
                    break;
            }
        }

        return true;
    }

    private int StartMenu() {
        Map<String, List<String>> menuData = new LinkedHashMap<String, List<String>>();
        menuData.put("ГЛАВНОЕ МЕНЮ", Arrays.asList("Добавить номер", "Найти контакт", "Просмотреть весь список"));
        menuData.put("---------", Arrays.asList("ВЫХОД"));
        return SetMenuOptions(menuData, 10, false, false, "");
    }

    private void SetContact() {
        var name = _cm.InputText("Введите фамилию: ");
        var phone = _cm.InputText("Введите телефон: ");
        ClearField(2);
        _phoneBook.AddContact(name, phone);

    }

    private void FindContact() {
        int linesCount = 0;
        Map<String, Set<String>> menuData = new LinkedHashMap<String, Set<String>>();
        if (_phoneBook.GetContacts().size() > 0) {
            var name = _cm.InputText("Введите фамилию: ");
            ClearField(1);

            Set<String> contactPhones = _phoneBook.GetPhonesByName(name);

            if (contactPhones != null) {
                menuData.put("Телефоны " + name + ":", contactPhones);
                linesCount = contactPhones.size() + 7;
            }
        }

        OpenPhoneBook(menuData, linesCount, false, true);
    }

    private void GetAllContacts() {
        OpenPhoneBook(_phoneBook.GetContacts(), 20, true, false);
    }

    private void ClearField(int linescount) {
        for (int i = 1; i <= linescount; i++) {
            _cm.PrintText("\033[F", " ".repeat(100) + "\b".repeat(99));
        }
    }

    private static void OpenPhoneBook(Map<String, Set<String>> bookData, int consoleLines, Boolean showHelpMenu,
            Boolean subjoinBackMenuline) {
        if (bookData.size() == 0) {
            PressEnterEvent("Справочник пуст.");
        } else {
            int phoneIndex = SetMenuOptions(ConvertPhonesToList(bookData), consoleLines, showHelpMenu,
                    subjoinBackMenuline, "");
            if (phoneIndex > 0 || phoneIndex < GetAllPhonesCount(bookData)) {
                ToBuffer(phoneIndex, bookData);
            }
        }
    }

    private static void ToBuffer(int index, Map<String, Set<String>> bookData) {
        index -= 1;
        var bufferPhoneBook = new PhoneBookModel(bookData);
        String phone = bufferPhoneBook.GetPhoneByGlobalIndex(index);
        StringSelection selection = new StringSelection(phone);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
        PressEnterEvent("Телефон " + phone + " скопирован в буффер обмена.");
    }

    private static void PressEnterEvent(String message) {
        Map<String, List<String>> menuData = new LinkedHashMap<String, List<String>>();
        menuData.put(message, Arrays.asList("Нажмите Enter..."));
        SetMenuOptions(menuData, 0, false, false, "  ");
    }

    private static int SetMenuOptions(Map<String, List<String>> menuData, int consoleLines, Boolean showHelpMenu,
            Boolean subjoinBackMenuline, String prefix) {
        if (subjoinBackMenuline) {
            menuData.put("---------", Arrays.asList("Вернуться."));
        }
        MenuRender mr = new MenuRender(menuData, consoleLines, true, showHelpMenu, null, null, prefix, "");
        return mr.StartRenderMenu(0);
    }

    private static Map<String, List<String>> ConvertPhonesToList(Map<String, Set<String>> menuData) {
        Map<String, List<String>> result = new LinkedHashMap<>();
        for (String key : menuData.keySet()) {
            Set<String> phoneSet = menuData.get(key);
            List<String> phoneList = new ArrayList<>(phoneSet);
            result.put(key, phoneList);
        }
        return result;
    }

    private static int GetAllPhonesCount(Map<String, Set<String>> bookData) {
        int count = 0;
        for (Set<String> item : bookData.values()) {
            count += item.size();
        }
        return count;
    }
}