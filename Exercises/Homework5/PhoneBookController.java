package Exercises.Homework5;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PhoneBookController {

    public PhoneBookController(PhoneBookModel phoneBook, PhoneBookView pbView) {
        this._phoneBook = phoneBook;
        this._pbView = pbView;

    }

    private String _nameFilter = null;

    private PhoneBookModel _phoneBook;
    private PhoneBookView _pbView;

    public PhoneBookController AddContact() {
        String[] newContact = _pbView.AskNewContact();
        if (newContact[0] != "" && newContact[1] != "") {
            _phoneBook.SetContact(newContact[0], newContact[1]);
        } else {
            _pbView.SayResult("Пошля не должны быть пустыми!");
        }
        return this;
    }

    public PhoneBookController FindContact() {
        if (_phoneBook.GetContacts().size() > 0) {
            String name = _pbView.AskName();
            _nameFilter = _phoneBook.ContainsName(name) ? name : "";
            if (_nameFilter.isEmpty()) {
                _pbView.SayNameNotFound();
            }
        } else {
            _pbView.SayEmptyBook();
        }
        return this;
    }

    public PhoneBookController AllContacts() {
        _nameFilter = null;
        if (_phoneBook.GetContacts().size() == 0) {
            _pbView.SayEmptyBook();
        }
        return this;
    }

    public String GetPhone() {
        int firstPhoneGlobalIndex = 0;
        if (_nameFilter == null && _phoneBook.GetContacts().size() != 0) {
            _pbView.viewMap = ConvertPhonesToList(_phoneBook.GetContacts());
        } else if (_nameFilter != null && !_nameFilter.isEmpty()) {
            List<String> contactPhones = new ArrayList<>(_phoneBook.GetPhonesByName(_nameFilter));
            firstPhoneGlobalIndex = _phoneBook.GetFirstPhoneGlobalIndexByName(_nameFilter);

            _pbView.viewMap = new LinkedHashMap<String, List<String>>();
            _pbView.viewMap.put(_nameFilter, contactPhones);

        } else {
            _nameFilter = null;
            return "";
        }

        int phoneIndex = _pbView.ShowData();
        if (phoneIndex == 0) {
            return "";
        }

        phoneIndex = CollectIndex(phoneIndex, firstPhoneGlobalIndex);

        return _phoneBook.GetPhoneByGlobalIndex(phoneIndex);
    }

    public void ReportResult(String message) {
        _pbView.SayResult(message);
    }

    private static int CollectIndex(int currentIndex, int firstPhoneGlobalIndex) {
        return currentIndex - 1 + firstPhoneGlobalIndex;
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
}
