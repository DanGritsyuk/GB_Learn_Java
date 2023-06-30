package Exercises.FinalProject;

import java.util.HashMap;
import java.util.Map;

public class Filter {
    public Filter() {
        this._parameters = new HashMap<>();
    }

    public static final String ROM_TYPE = "typeROM";
    public static final String RAM_TYPE = "typeRAM";
    public static final String GPU_TYPE = "typeGPU";
    public static final String OS = "os";
    public static final String TEXT_TITLE = "findtext";

    private Map<String, Object> _parameters;

    public void SetFilter(String key, Object value) {
        Boolean parameterExist = _parameters.containsKey(key);
        if (parameterExist && _parameters.get(key) != value) {
            _parameters.replace(key, value);
        } else if (parameterExist && _parameters.get(key) == value) {
            _parameters.remove(key);
        } else {
            _parameters.put(key, value);
        }
    }

    public Map<String, Object> GetCriteries() {
        return _parameters;
    }
}
