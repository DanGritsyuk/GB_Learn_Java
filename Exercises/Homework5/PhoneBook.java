package Exercises.Homework5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    public PhoneBook() {
        _contacts = new HashMap<String, List<String>>();
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

    public List<String> getAllNamesList() {
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
