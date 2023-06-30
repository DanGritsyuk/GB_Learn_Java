package Exercises.FinalProject.Views;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Exercises.FinalProject.Filter;
import Exercises.FinalProject.Structs.OperatingSystem;
import Exercises.FinalProject.Structs.TypeRAM;
import Exercises.FinalProject.Structs.TypeROM;
import Interface.MenuRender;

public class FilterMenuView {

    private List<String> _romType;
    private List<String> _ramType;
    private List<String> _gpuType;
    private List<String> _osType;

    private Filter _filter;

    public void GetFilter(Filter filter) {
        _filter = filter;
    }

    public void SetFilter(Filter filter) {
        _filter = filter;
    }

    public FilterMenuView() {
        _romType = Arrays.asList("  &&SSD&&", "  &&HDD&&");
        _ramType = Arrays.asList("  &&DDR2&&", "  &&DDR3&&", "  &&DDR4&&", "  &&DDR5&&");
        _gpuType = Arrays.asList("  &&Дискретная&&", "  &&Интегрированная&&");
        _gpuType = Arrays.asList("  &&Windows&&", "  &&Linux&&");
    }

    public Filter CheckCriteries() {
        var menuStart = true;
        while (menuStart) {
            var mr = new MenuRender(GetMenuData(), 30, true, true, GetParametersText(), "", "||", "&&");
            int parameterId = mr.StartRenderMenu(0);
            switch (parameterId) {
                case 0:
                    menuStart = false;
                    break;
                case 1:
                    _filter.SetFilter("romType", TypeROM.SSD);
                case 2:
                    _filter.SetFilter("romType", TypeROM.HDD);
                case 3:
                    _filter.SetFilter("ramType", TypeRAM.DDR2);
                case 4:
                    _filter.SetFilter("ramType", TypeRAM.DDR3);
                case 5:
                    _filter.SetFilter("ramType", TypeRAM.DDR4);
                case 6:
                    _filter.SetFilter("ramType", TypeRAM.DDR5);
                case 7:
                    _filter.SetFilter("gpuIsDiscrete", true);
                case 8:
                    _filter.SetFilter("gpuIsDiscrete", false);
                case 9:
                    _filter.SetFilter("os", OperatingSystem.Windows);
                case 10:
                    _filter.SetFilter("os", OperatingSystem.Linux);
            }
        }
        return _filter;
    }

    private Map<String, List<String>> GetMenuData() {
        if (_filter == null) {
            throw new ExceptionInInitializerError("Filter not initialized.");
        }
        var data = new LinkedHashMap<String, List<String>>();
        data.put("Тип диска:", CheckParameter(_romType));
        data.put("Тип ОЗУ:", CheckParameter(_ramType));
        data.put("Тип Видеокарты:", CheckParameter(_gpuType));
        data.put("Операционная система:", CheckParameter(_osType));
        return data;
    }

    private String GetParametersText() {
        StringBuilder sb = new StringBuilder("Выбор: ");
        if (_filter.GetCriteries().size() == 0) {
            sb.append("весь список.");
        } else {
            for (var parameter : _filter.GetCriteries().values()) {
                sb.append(parameter + " ");
            }
        }
        return sb.toString();
    }

    private List<String> CheckParameter(List<String> parameters) {
        var checkedParameters = new LinkedList<String>();
        for (String parameter : parameters) {
            checkedParameters.add(parameter);
        }
        return checkedParameters;
    }
}
