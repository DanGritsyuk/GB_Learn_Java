package Exercises.FinalProject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import Exercises.FinalProject.NotebookComponents.CentralProcessor;
import Exercises.FinalProject.NotebookComponents.GraphicProcessor;
import Exercises.FinalProject.NotebookComponents.RandomAccessMemory;
import Exercises.FinalProject.NotebookComponents.ReadOnlyMemory;
import Exercises.FinalProject.Structs.TypeRAM;
import Exercises.FinalProject.Structs.TypeROM;

public class Notebook {
    private String _brand;
    private String _name;
    private String _os;
    private int _inStock;
    private BigDecimal _price;
    private CentralProcessor _cpu;
    private GraphicProcessor _gpu;
    private RandomAccessMemory[] _ram;
    private ReadOnlyMemory[] _rom;

    private Boolean _gpuIsDiscrete;

    public Notebook(String brand,
            String name,
            String os,
            int inStock,
            BigDecimal price,
            CentralProcessor cpu,
            GraphicProcessor gpu,
            RandomAccessMemory[] ram,
            ReadOnlyMemory[] rom) {
        this._brand = brand;
        this._name = name;
        this._os = os;
        this._inStock = inStock;
        this._price = price;
        this._cpu = cpu;

        SetGPU(cpu, gpu);

        this._ram = ram;
        this._rom = rom;
    }

    public int GetSataCount() {
        return _rom.length;
    }

    public ReadOnlyMemory GetROM(int sata) {
        return _rom[sata - 1];
    }

    public int GetRamSlotCount() {
        return _ram.length;
    }

    public RandomAccessMemory GetRAM(int slot) {
        return _ram[slot - 1];
    }

    public BigDecimal GetPrice() {
        return _price;
    }

    public int GetCountInStock() {
        return _inStock;
    }

    public TypeRAM GetRamType() {
        return _ram[0].getType();
    }

    public TypeROM GetRomType() {
        return _rom[0].getType();
    }

    public String GetOs() {
        return _os;
    }

    @Override
    public String toString() {
        String title = _brand + " " + _name + ": ";
        title += _gpuIsDiscrete ? "" : "интегрированный чип ";
        title += _gpu.toString() + ", ";
        title += _cpu.toString() + ", ";
        title += GetStringMemory(_ram) + ", ";
        title += GetStringMemory(_rom) + ", ";
        title += _os;

        return title;
    }

    private void SetGPU(CentralProcessor cpu, GraphicProcessor gpu) {
        if (gpu == null) {
            this._gpu = cpu.getIntegratedGPU();
            this._gpuIsDiscrete = false;
        } else {
            this._gpu = gpu;
            this._gpuIsDiscrete = true;
        }
    }

    private static <T> String GetStringMemory(T[] slots) {
        var usedSlots = RemoveNullValues(slots);
        if (usedSlots.length == 0) {
            return "количество слотов ОЗУ: " + slots.length;
        }
        int count = 1;
        var currentROM = usedSlots[0];
        String result = currentROM.toString();
        for (int i = 1; i < usedSlots.length; i++) {
            if (usedSlots[i] != null && usedSlots[i].equals(currentROM)) {
                count++;
            } else {
                result += count > 1 ? "x" + count : "";
                result += ", " + usedSlots[i].toString();
                currentROM = usedSlots[i];
            }
        }
        result += count > 1 ? "x" + count : "";
        return result;
    }

    private static Object[] RemoveNullValues(Object[] array) {
        List<Object> list = new ArrayList<>(Arrays.asList(array));
        list.removeIf(Objects::isNull);
        return list.toArray();
    }

}