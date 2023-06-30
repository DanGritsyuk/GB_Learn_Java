package Exercises.FinalProject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Exercises.FinalProject.NotebookComponents.CentralProcessor;
import Exercises.FinalProject.NotebookComponents.GraphicProcessor;
import Exercises.FinalProject.NotebookComponents.RandomAccessMemory;
import Exercises.FinalProject.NotebookComponents.ReadOnlyMemory;
import Exercises.FinalProject.Structs.TypeRAM;
import Exercises.FinalProject.Structs.TypeROM;

public class Catalog {

    private final String NAME_CORSAR = "CORSAR";
    private final String NAME_NVIDIA = "NVIDIA";
    private final String NAME_AMD = "AMD";
    private final String NAME_INTEL = "INTEL";

    private final String NAME_ASUS = "ASUS";
    private final String NAME_LENOVO = "LENOVO";

    private final String OS_WINDOWS10 = "WINDOWS 10";
    private final String OS_WINDOWS11 = "WINDOWS 11";
    private final String OS_LINUX = "LINUX";

    private final RandomAccessMemory[] MEMORIES;
    private final ReadOnlyMemory[] DISKS;
    private final GraphicProcessor[] GPUSES;
    private final CentralProcessor[] CPUSES;

    private Notebook[] _notebooks;

    public Catalog() {

        this.MEMORIES = new RandomAccessMemory[4];

        MEMORIES[0] = new RandomAccessMemory(NAME_CORSAR, "Vengeance", 4, TypeRAM.DDR3);
        MEMORIES[1] = new RandomAccessMemory(NAME_CORSAR, "Vengeance LPX", 8, TypeRAM.DDR4);
        MEMORIES[2] = new RandomAccessMemory(NAME_CORSAR, "Vengeance LPX", 16, TypeRAM.DDR4);
        MEMORIES[3] = new RandomAccessMemory(NAME_CORSAR, "SuperSpeed", 2, TypeRAM.DDR2);

        this.DISKS = new ReadOnlyMemory[2];

        DISKS[0] = new ReadOnlyMemory("SEAGATE", "FireCuda", 3000, TypeROM.HDD);
        DISKS[1] = new ReadOnlyMemory("SAMSUNG", "EVO850", 500, TypeROM.SSD);

        this.GPUSES = new GraphicProcessor[5];

        GPUSES[0] = new GraphicProcessor(NAME_NVIDIA, "RTX 1060", TypeRAM.DDR3, 1100);
        GPUSES[1] = new GraphicProcessor(NAME_NVIDIA, "RTX 2060", TypeRAM.DDR4, 1100);
        GPUSES[2] = new GraphicProcessor(NAME_NVIDIA, "RTX 3060", TypeRAM.DDR5, 1100);
        GPUSES[3] = new GraphicProcessor(NAME_AMD, "RX 480", TypeRAM.DDR5, 1120);
        GPUSES[4] = new GraphicProcessor(NAME_AMD, "RX 580", TypeRAM.DDR5, 1257);

        this.CPUSES = new CentralProcessor[5];

        CPUSES[0] = new CentralProcessor(NAME_INTEL, "i3", 2.8, 2,
                new GraphicProcessor(NAME_INTEL, "HD Graphics 2000", null, 650));
        CPUSES[1] = new CentralProcessor(NAME_INTEL, "i5", 2.8, 4,
                new GraphicProcessor(NAME_INTEL, "HD Graphics 2000", null, 850));
        CPUSES[2] = new CentralProcessor(NAME_INTEL, "i7", 3.6, 4,
                new GraphicProcessor(NAME_INTEL, "HD Graphics 2000", null, 1100));
        CPUSES[3] = new CentralProcessor(NAME_AMD, "Rizen 3", 2.6, 2,
                new GraphicProcessor(NAME_AMD, "Vega", null, 950));
        CPUSES[4] = new CentralProcessor(NAME_AMD, "Rizen 5", 3.6, 2,
                new GraphicProcessor(NAME_AMD, "Vega", null, 1100));
    }

    public String[] GetOperatingSystem() {
        return new String[] { OS_WINDOWS10, OS_WINDOWS11, OS_LINUX };
    }

    public Catalog SetNotebooks() {
        _notebooks = new Notebook[4];

        _notebooks[0] = new Notebook(NAME_ASUS, "Vivobook", OS_WINDOWS10, 10, BigDecimal.valueOf(33990),
                CPUSES[0], null,
                new RandomAccessMemory[] { MEMORIES[0], MEMORIES[0] },
                new ReadOnlyMemory[] { DISKS[1] });
        _notebooks[1] = new Notebook(NAME_LENOVO, "Thinkpad X220", OS_LINUX, 5, BigDecimal.valueOf(23990),
                CPUSES[0], null,
                new RandomAccessMemory[] { MEMORIES[0] },
                new ReadOnlyMemory[] { DISKS[1] });
        _notebooks[2] = new Notebook(NAME_ASUS, "ROG", OS_WINDOWS11, 5, BigDecimal.valueOf(69990),
                CPUSES[4], GPUSES[1],
                new RandomAccessMemory[] { MEMORIES[1], MEMORIES[1], null, null },
                new ReadOnlyMemory[] { DISKS[1], null });
        _notebooks[3] = new Notebook(NAME_LENOVO, "OldSchool", OS_LINUX, 5, BigDecimal.valueOf(15990),
                CPUSES[0], null,
                new RandomAccessMemory[] { MEMORIES[3], null },
                new ReadOnlyMemory[] { DISKS[0] });

        return this;
    }

    public Map<String, List<String>> GetNotebooks() {
        Map<String, List<String>> sortedNB = new TreeMap<String, List<String>>();
        Map<String, Object> filters = new HashMap<>();
        filters.put("brand", NAME_ASUS);
        sortedNB.put(NAME_ASUS, FilterNotebooks(Arrays.asList(_notebooks), filters));
        filters.replace("brand", NAME_LENOVO);
        sortedNB.put(NAME_LENOVO, FilterNotebooks(Arrays.asList(_notebooks), filters));
        return sortedNB;
    }

    public List<String> GetNotebooks(Map<String, Object> filters) {

        return FilterNotebooks(Arrays.asList(_notebooks), filters);
    }

    private static List<String> FilterNotebooks(List<Notebook> notebooks, Map<String, Object> filters) {
        List<String> list = new ArrayList<>();
        for (Notebook notebook : notebooks) {
            boolean isMatched = true;
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                switch (entry.getKey()) {
                    case "ramType":
                        if (notebook.GetRamType() != (TypeRAM) entry.getValue()) {
                            isMatched = false;
                        }
                        break;
                    case "romType":
                        if (notebook.GetRomType() != (TypeROM) entry.getValue()) {
                            isMatched = false;
                        }
                        break;
                    case "os":
                        if (!notebook.GetOs().contains(entry.getValue().toString())) {
                            isMatched = false;
                        }
                    case "brand":
                        if (!notebook.GetOs().contains(entry.getValue().toString())) {
                            isMatched = false;
                        }
                        break;
                }
            }
            if (isMatched) {
                list.add(notebook.toString());
            }
        }
        return list;
    }
}
