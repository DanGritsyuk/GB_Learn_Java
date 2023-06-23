package Exercises.Homework5;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import Controllers.JsonXMLParseController;
import Controllers.SaveLoadFileController;

public class PhoneBookModel {
    public PhoneBookModel() {
        StringBuilder sb = new StringBuilder();
        try {
            String[] xmlData = SaveLoadFileController.LoadFromFile(_dataPath);
            if (xmlData != null) {
                for (var line : xmlData) {
                    sb.append(line);
                }
                _contacts = JsonXMLParseController.MapStringSetFromXML(sb.toString());
            } else {
                _contacts = new TreeMap<String, Set<String>>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String _dataPath = "Data\\Phonebook.xml";
    private Map<String, Set<String>> _contacts;

    public Map<String, Set<String>> GetContacts() {
        return _contacts;
    }

    public void SetContact(String name, String phone) {
        if (ContainsName(name)) {
            Set<String> phones = _contacts.get(name);
            phones.add(phone);
        } else {
            Set<String> phones = new LinkedHashSet<String>();
            phones.add(phone);
            _contacts.put(name, phones);
        }
        String xmlData = JsonXMLParseController.MapStringSetToXML(_contacts);
        try {
            SaveLoadFileController.SaveToFile(_dataPath, xmlData, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<String> GetPhonesByName(String name) {
        return _contacts.get(name);
    }

    public String GetPhoneByGlobalIndex(int globalIndex) {
        String phone = "";
        for (var key : _contacts.keySet()) {
            Set<String> phones = _contacts.get(key);
            int phonesCount = phones.size();
            if (globalIndex >= phonesCount) {
                globalIndex -= phonesCount;
            } else {
                var setPhones = _contacts.get(key);
                phone = setPhones.toArray(new String[setPhones.size()])[globalIndex];
                break;
            }
        }
        return phone;
    }

    public int GetFirstPhoneGlobalIndexByName(String name) {
        int firstPhoneGlobalIndex = 0;
        for (var key : _contacts.keySet()) {
            if (!key.equals(name)) {
                Set<String> phones = _contacts.get(key);
                int phonesCount = phones.size();
                firstPhoneGlobalIndex += phonesCount;
            } else {
                break;
            }
        }
        return firstPhoneGlobalIndex;
    }

    public int GetAllPhonesCount() {
        int count = 0;
        for (Set<String> item : _contacts.values()) {
            count += item.size();
        }
        return count;
    }

    public Boolean ContainsName(String name) {
        return _contacts.containsKey(name);
    }
}
