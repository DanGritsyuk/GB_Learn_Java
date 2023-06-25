package Exercises.Homework5;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Exercises.Exercise;
import Interface.MenuRender;

public class Exercise11 extends Exercise {
    public Exercise11(String description) {
        super(description);

        var phoneBook = new PhoneBookModel();
        this._pbController = new PhoneBookController(phoneBook, new PhoneBookView(cmdManager));

        _startMenu.put("ГЛАВНОЕ МЕНЮ", Arrays.asList("Добавить номер", "Найти контакт", "Просмотреть весь список"));
        _startMenu.put("---------", Arrays.asList("ВЫХОД"));
        this._mr = new MenuRender(_startMenu, 10, true, false, null, null, null, null);
    }

    private PhoneBookController _pbController;
    private MenuRender _mr;
    private Map<String, List<String>> _startMenu = new LinkedHashMap<String, List<String>>();
    private int _taskId = 1;

    @Override
    public boolean Solution() {
        var phoneBookIsOpen = true;
        while (phoneBookIsOpen) {
            _taskId = _mr.StartRenderMenu(_taskId - 1);
            switch (_taskId) {
                case 0, 4:
                    phoneBookIsOpen = false;
                    break;
                case 1:
                    _pbController.AddContact();
                    break;
                case 2:
                    SaveToBuffer(_pbController.FindContact().GetPhone());
                    break;
                case 3:

                    SaveToBuffer(_pbController.AllContacts().GetPhone());
                    break;

                default:
                    cmdManager.PrintText("Неизвестная команда");
                    break;
            }
        }

        return true;
    }

    private void SaveToBuffer(String phone) {
        if (!phone.isEmpty()) {
            StringSelection selection = new StringSelection(phone);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
            _pbController.ReportResult("Телефон " + phone + " скопирован в буффер обмена.");
        }
    }
}