package Exercises.Homework5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PhoneBook {
    public PhoneBook() {
        _contacts = new TreeMap<String, List<String>>();
    }

    public PhoneBook(Map<String, List<String>> bookData) {
        _contacts = bookData;
    }

    private Map<String, List<String>> _contacts;

    public Map<String, List<String>> GetContacts() {
        return _contacts;
    }

    public void AddContact(String name, String phone) {
        if (_contacts.containsKey(name)) {
            List<String> phones = _contacts.get(name);
            phones.add(phone);
        } else {
            List<String> phones = new LinkedList<String>();
            phones.add(phone);
            _contacts.put(name, phones);
        }
    }

    public List<String> GetPhonesByName(String name) {
        return _contacts.get(name);
    }

    public String GetPhoneByGlobalIndex(int globalIndex) {
        String phone = "";
        for (var key : _contacts.keySet()) {
            List<String> phones = _contacts.get(key);
            int phonesCount = phones.size();
            if (globalIndex >= phonesCount) {
                globalIndex -= phonesCount;
            } else {
                phone = _contacts.get(key).get(globalIndex);
                break;
            }
        }
        return phone;
    }

    public List<String> GetAllNamesList() {
        List<Map.Entry<String, List<String>>> list = new LinkedList<>(_contacts.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, List<String>>>() {
            @Override
            public int compare(Map.Entry<String, List<String>> o1, Map.Entry<String, List<String>> o2) {
                return o2.getValue().size() - o1.getValue().size();
            }
        });
        List<String> result = new ArrayList<String>();
        for (Map.Entry<String, List<String>> entry : list) {
            result.add(entry.getKey());
        }
        return result;
    }
}
