package Exercises.Homework5;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class PhoneBookModel {
    public PhoneBookModel() {
        _contacts = new TreeMap<String, Set<String>>();
    }

    public PhoneBookModel(Map<String, Set<String>> bookData) {
        _contacts = bookData;
    }

    private Map<String, Set<String>> _contacts;

    public Map<String, Set<String>> GetContacts() {
        return _contacts;
    }

    public void AddContact(String name, String phone) {
        if (_contacts.containsKey(name)) {
            Set<String> phones = _contacts.get(name);
            phones.add(phone);
        } else {
            Set<String> phones = new LinkedHashSet<String>();
            phones.add(phone);
            _contacts.put(name, phones);
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
}
