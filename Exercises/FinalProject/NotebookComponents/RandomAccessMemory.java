package Exercises.FinalProject.NotebookComponents;

import Exercises.FinalProject.Structs.Brand;
import Exercises.FinalProject.Structs.TypeRAM;

public class RandomAccessMemory {
    public RandomAccessMemory(Brand brand, String name, int size, TypeRAM type) {
        this._brand = brand;
        this._name = name;
        this._size = size;
        this._typeRAM = type;
    }

    private Brand _brand;
    private String _name;
    private int _size;
    private TypeRAM _typeRAM;

    public TypeRAM GetType() {
        return _typeRAM;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ReadOnlyMemory) {
            var anotherROM = (RandomAccessMemory) obj;
            return this._brand == anotherROM._brand &&
                    this._name == anotherROM._name &&
                    this._size == anotherROM._size &&
                    this._typeRAM == anotherROM._typeRAM;
        }
        return false;
    }

    @Override
    public String toString() {
        return "" + _typeRAM + " " + _size + "ГБ: " + _brand + " " + _name;
    }
}
