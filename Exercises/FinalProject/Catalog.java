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
import Exercises.FinalProject.Structs.Brand;
import Exercises.FinalProject.Structs.OperatingSystem;
import Exercises.FinalProject.Structs.TypeRAM;
import Exercises.FinalProject.Structs.TypeROM;

public class Catalog {
    public Catalog() {

        this.MEMORIES = new RandomAccessMemory[3];

        MEMORIES[0] = new RandomAccessMemory(Brand.CORSAR, "Vengeance", 4, TypeRAM.DDR3);
        MEMORIES[1] = new RandomAccessMemory(Brand.CORSAR, "Vengeance LPX", 8, TypeRAM.DDR4);
        MEMORIES[2] = new RandomAccessMemory(Brand.CORSAR, "Vengeance LPX", 16, TypeRAM.DDR4);

        this.DISKS = new ReadOnlyMemory[2];

        DISKS[0] = new ReadOnlyMemory(Brand.SEAGATE, "FireCuda", 3000, TypeROM.HDD);
        DISKS[1] = new ReadOnlyMemory(Brand.SAMSUNG, "EVO850", 500, TypeROM.SSD);

        this.GPUSES = new GraphicProcessor[5];

        GPUSES[0] = new GraphicProcessor(Brand.NVIDIA, "RTX 1060", TypeRAM.DDR3, 1100);
        GPUSES[1] = new GraphicProcessor(Brand.NVIDIA, "RTX 2060", TypeRAM.DDR4, 1100);
        GPUSES[2] = new GraphicProcessor(Brand.NVIDIA, "RTX 3060", TypeRAM.DDR5, 1100);
        GPUSES[3] = new GraphicProcessor(Brand.AMD, "RX 480", TypeRAM.DDR5, 1120);
        GPUSES[4] = new GraphicProcessor(Brand.AMD, "RX 580", TypeRAM.DDR5, 1257);

        this.CPUSES = new CentralProcessor[5];

        CPUSES[0] = new CentralProcessor(Brand.Intel, "i3", 2.8, 2,
                new GraphicProcessor(Brand.Intel, "HD Graphics 2000", null, 650));
        CPUSES[1] = new CentralProcessor(Brand.Intel, "i5", 2.8, 4,
                new GraphicProcessor(Brand.Intel, "HD Graphics 2000", null, 850));
        CPUSES[2] = new CentralProcessor(Brand.Intel, "i7", 3.6, 4,
                new GraphicProcessor(Brand.Intel, "HD Graphics 2000", null, 1100));
        CPUSES[3] = new CentralProcessor(Brand.AMD, "Rizen 3", 2.6, 2,
                new GraphicProcessor(Brand.AMD, "Vega", null, 950));
        CPUSES[4] = new CentralProcessor(Brand.AMD, "Rizen 5", 3.6, 2,
                new GraphicProcessor(Brand.AMD, "Vega", null, 1100));
    }

    private final RandomAccessMemory[] MEMORIES;
    private final ReadOnlyMemory[] DISKS;
    private final GraphicProcessor[] GPUSES;
    private final CentralProcessor[] CPUSES;

    private Notebook[] _notebooks;

    public Catalog SetNotebooks() {
        _notebooks = new Notebook[3];

        _notebooks[0] = new Notebook(Brand.ASUS, "Vivobook", OperatingSystem.Windows, 10, BigDecimal.valueOf(33990),
                CPUSES[0], null,
                new RandomAccessMemory[] { MEMORIES[0], MEMORIES[0] },
                new ReadOnlyMemory[] { DISKS[1] });
        _notebooks[1] = new Notebook(Brand.Lenovo, "Thinkpad X220", OperatingSystem.Windows, 5,
                BigDecimal.valueOf(23990),
                CPUSES[0], null,
                new RandomAccessMemory[] { MEMORIES[0] },
                new ReadOnlyMemory[] { DISKS[1] });
        _notebooks[2] = new Notebook(Brand.ASUS, "ROG", OperatingSystem.Windows, 5, BigDecimal.valueOf(69990),
                CPUSES[4], GPUSES[1],
                new RandomAccessMemory[] { MEMORIES[1], MEMORIES[1], null, null },
                new ReadOnlyMemory[] { DISKS[1], null });

        return this;
    }

    public Map<String, List<Notebook>> GetNotebooksData() {
        Map<String, List<Notebook>> sortedNB = new TreeMap<String, List<Notebook>>();

        sortedNB.put(Brand.ASUS.toString(), GetByBrand(Brand.ASUS));
        sortedNB.put(Brand.Lenovo.toString(), GetByBrand(Brand.Lenovo));
        return sortedNB;
    }

    public List<Notebook> GetNotebooks(Filter filter) {

        return FilterNotebooks(Arrays.asList(_notebooks), filter.GetCriteries());
    }

    public List<Notebook> GetByBrand(Brand brandName) {
        Map<String, Object> filters = new HashMap<>();
        filters.put("brand", brandName);
        return FilterNotebooks(Arrays.asList(_notebooks), filters);
    }

    private static List<Notebook> FilterNotebooks(List<Notebook> notebooks, Map<String, Object> filter) {
        List<Notebook> list = new ArrayList<>();
        for (Notebook notebook : notebooks) {
            boolean isMatched = true;
            for (Map.Entry<String, Object> entry : filter.entrySet()) {
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
                        if (!notebook.GetOs().equals(entry.getValue())) {
                            isMatched = false;
                        }
                        break;
                }
            }
            if (isMatched) {
                list.add(notebook);
            }
        }
        return list;
    }
}
