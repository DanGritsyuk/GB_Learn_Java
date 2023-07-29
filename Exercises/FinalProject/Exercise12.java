package Exercises.FinalProject;

import java.util.HashMap;
import java.util.Map;
import Exercises.Exercise;
import Exercises.FinalProject.Structs.TypeRAM;
import Exercises.FinalProject.Structs.TypeROM;

public class Exercise12 extends Exercise {
    private Catalog _catalog = new Catalog();
    private String _textRAM = "";
    private String _textROM = "";
    private String _os = "";
    private String _brand = "";

    public Exercise12(String description) {
        super(description);
    }

    @Override
    public boolean solution() {
        Map<String, Object> filters = new HashMap<>();

        var openFilterMenu = true;
        while (openFilterMenu) {

            super.cmdManager.consoleClear();
            super.drawHeader();
            super.cmdManager.printText("Вбрать критерий: " + _textRAM + " " + _textROM + " " + _os + " " + _brand);
            super.cmdManager.printText("1 - тип RAM");
            super.cmdManager.printText("2 - тип ROM");
            super.cmdManager.printText("3 - Операционная система");
            super.cmdManager.printText("4 - Бренд");
            super.cmdManager.printText();
            super.cmdManager.printText("0 - Найти ноутбуки.");

            int filterCriteria = Integer.parseInt(super.cmdManager.inputText("ВВОД: "));

            switch (filterCriteria) {
                case 0:
                    openFilterMenu = false;
                    break;
                case 1:
                    String bufferRAM = super.cmdManager.inputText("Укажите тип RAM (DDR2, DDR3 и т. д.): ")
                            .toUpperCase();
                    TypeRAM typeRAM = null;
                    for (var ram : TypeRAM.values()) {
                        if (ram.toString().equals(bufferRAM)) {
                            typeRAM = ram;
                            _textRAM = bufferRAM;
                            break;
                        }
                    }
                    if (typeRAM != null) {
                        filters.put("ramType", typeRAM);
                    } else {
                        super.cmdManager.printText("Такого критерия нет...");
                        super.cmdManager.inputText("Нажмите Enter");
                    }
                    break;
                case 2:
                    String bufferROM = super.cmdManager.inputText("Укажите тип ROM (HDD или SSD): ").toUpperCase();
                    TypeROM typeROM = null;
                    for (var rom : TypeROM.values()) {
                        if (rom.toString().equals(bufferROM)) {
                            typeROM = rom;
                            _textROM = bufferROM;
                            break;
                        }
                    }
                    if (typeROM != null) {
                        filters.put("romType", typeROM);
                    } else {
                        super.cmdManager.printText("Такого критерия нет...");
                        super.cmdManager.inputText("Нажмите Enter");
                    }
                    break;
                case 3:
                    _os = super.cmdManager.inputText("Укажите О/С: ").toUpperCase();
                    filters.put("os", _os);
                    break;
                case 4:
                    _brand = super.cmdManager.inputText("Укажите марку: ").toUpperCase();
                    filters.put("brand", _brand);
                    break;
            }
        }

        var print = _catalog.setNotebooks().getNotebooks(filters);
        super.cmdManager.printText();
        if (print.size() == 0) {
            super.cmdManager.printText("По данным критериям ничего не нашлось :(");
        } else {
            for (String string : print) {
                super.cmdManager.printText(string);
            }
        }
        return false;
    }
}
