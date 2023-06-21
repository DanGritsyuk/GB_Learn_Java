package Exercises.Homework5;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

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
        return SetMenuOptions(menuData, 10, false, "");
    }

    private void SetContact() {
        var name = _cm.InputText("Введите фамилию: ");
        var phone = _cm.InputText("Введите телефон: ");
        ClearField(2);
        _phoneBook.AddContact(name, phone);

    }

    private void FindContact() {
        int linesCount = 0;
        Map<String, List<String>> menuData = new LinkedHashMap<String, List<String>>();
        if (_phoneBook.GetContacts().size() > 0) {
            var name = _cm.InputText("Введите фамилию: ");
            ClearField(1);

            List<String> contactPhones = _phoneBook.GetPhonesByName(name);

            if (contactPhones != null) {
                menuData.put("Телефоны " + name + ":", contactPhones);
                menuData.put("---------", Arrays.asList("Вернуться."));
                linesCount = contactPhones.size() + 7;
            }
        }

        OpenPhoneBook(menuData, linesCount, false);
    }

    private void GetAllContacts() {
        OpenPhoneBook(_phoneBook.GetContacts(), 20, true);
    }

    private void ClearField(int linescount) {
        for (int i = 1; i <= linescount; i++) {
            _cm.PrintText("\033[F", " ".repeat(100) + "\b".repeat(99));
        }
    }

    private static void OpenPhoneBook(Map<String, List<String>> bookData, int consoleLines, Boolean showHelpMenu) {
        if (bookData.size() == 0) {
            PressEnterEvent("Справочник пуст.");
        } else {
            int phoneIndex = SetMenuOptions(bookData, consoleLines, showHelpMenu, "");
            if (phoneIndex > 0) {
                ToBuffer(phoneIndex, bookData);
            }
        }
    }

    private static void ToBuffer(int index, Map<String, List<String>> bookData) {
        index -= 1;
        var bufferPhoneBook = new PhoneBook(bookData);
        String phone = bufferPhoneBook.GetPhoneByGlobalIndex(index);
        StringSelection selection = new StringSelection(phone);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
        PressEnterEvent("Телефон " + phone + " скопирован в буффер обмена.");
    }

    private static void PressEnterEvent(String message) {
        Map<String, List<String>> menuData = new LinkedHashMap<String, List<String>>();
        menuData.put(message, Arrays.asList("Нажмите Enter..."));
        SetMenuOptions(menuData, 0, false, "  ");
    }

    private static int SetMenuOptions(Map<String, List<String>> menuData, int consoleLines, Boolean showHelpMenu,
            String prefix) {
        MenuRender mr = new MenuRender(menuData, consoleLines, true, showHelpMenu, null, null, prefix, "");
        return mr.StartRenderMenu(0);
    }
}