package Exercises.FinalProject;

import java.util.HashMap;
import java.util.Map;
import Exercises.Exercise;

public class ExerciseEnd extends Exercise {
    public ExerciseEnd(String description) {
        super(description);
    }

    private Catalog _catalog = new Catalog();

    @Override
    public boolean Solution() {

        super.cmdManager.PrintText("Вбрать критерий:");
        super.cmdManager.PrintText("1 - тип RAM");
        super.cmdManager.PrintText("2 - тип ROM");
        super.cmdManager.PrintText("3 - Операционная система");

        int filterCriteria = Integer.parseInt(super.cmdManager.InputText("ВВОД: "));
        Map<String, Object> filters = new HashMap<>();
        switch (filterCriteria) {
            case 1:
                System.out.println("Выбор RAM:");
                int minRam = Integer.parseInt(super.cmdManager.InputText("ВВОД: "));
                filters.put("ramType", minRam);
                break;
            case 2:
                System.out.println("Выбор ROM:");
                int minHdd = Integer.parseInt(super.cmdManager.InputText("ВВОД: "));
                filters.put("romType", minHdd);
                break;
            case 3:
                System.out.println("Выбор ОС:");
                String os = super.cmdManager.InputText("ВВОД: ");
                filters.put("os", os);
                break;
        }

        var print = _catalog.SetNotebooks().GetNotebooks(filters);

        super.cmdManager.PrintArray(print.toArray());

        return false;
    }
}
