package Exercises.FinalProject.NotebookComponents;

import Exercises.FinalProject.Structs.TypeROM;

public class ReadOnlyMemory {
    public ReadOnlyMemory(String brand, String name, int size, TypeROM type) {
        this._brand = brand;
        this._name = name;
        this._size = size;
        this._typeROM = type;
    }

    private String _brand;
    private String _name;
    private int _size;
    private TypeROM _typeROM;

    public TypeROM GetType() {
        return _typeROM;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ReadOnlyMemory) {
            var anotherROM = (ReadOnlyMemory) obj;
            return this._brand == anotherROM._brand &&
                    this._name == anotherROM._name &&
                    this._size == anotherROM._size &&
                    this._typeROM == anotherROM._typeROM;
        }
        return false;
    }

    @Override
    public String toString() {
        return "" + _typeROM + " " + _size + "ГБ: " + _brand + " " + _name;
    }

}
