package Exercises.Homework5;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Interface.ConsoleManager;
import Interface.MenuRender;

public class PhoneBookView {

    public PhoneBookView(ConsoleManager cm) {
        this._cm = cm;
    }

    public Map<String, List<String>> viewMap;
    private ConsoleManager _cm;

    public int ShowData() {
        return StartRender(viewMap, 18, true, "");
    }

    public String AskName() {
        var name = _cm.InputText("Введите фамилию: ");
        ClearField(1);
        return name;
    }

    public String[] AskNewContact() {
        String[] contactMap = new String[2];
        contactMap[0] = _cm.InputText("Введите фамилию: ");
        contactMap[1] = _cm.InputText("Введите телефон: ");
        ClearField(2);
        return contactMap;
    }

    public void SayEmptyBook() {
        PressEnterEvent("Справочник пуст.");
    }

    public void SayNameNotFound() {
        PressEnterEvent("Контакт не найден.");
    }

    public void SayResult(String message) {
        PressEnterEvent(message);
    }

    private void PressEnterEvent(String message) {
        Map<String, List<String>> menuData = new LinkedHashMap<String, List<String>>();
        menuData.put(message, Arrays.asList("Нажмите Enter..."));
        StartRender(menuData, 0, false, "  ");
    }

    private void ClearField(int linescount) {
        for (int i = 1; i <= linescount; i++) {
            _cm.PrintText("\033[F", " ".repeat(100) + "\b".repeat(99));
        }
    }

    private static int StartRender(Map<String, List<String>> menuData, int consoleLines, Boolean showHelpMenu,
            String prefix) {
        MenuRender mr = new MenuRender(menuData, consoleLines, true, showHelpMenu, null, null, prefix, "");
        return mr.StartRenderMenu(0);
    }
}
