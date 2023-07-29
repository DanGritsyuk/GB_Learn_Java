package Exercises.FinalProject.NotebookComponents;

import Exercises.FinalProject.Structs.TypeRAM;

public class GraphicProcessor {
    private String _brand;
    private String _name;
    private TypeRAM _typeRAM;
    private int _clockRate;
    private int _videoRAM;

    public GraphicProcessor(String brand, String name, TypeRAM typeRAM, int clockRate) {
        this._brand = brand;
        this._name = name;
        this._typeRAM = typeRAM;
        this._clockRate = clockRate;
    }

    @Override
    public String toString() {
        return "" + _brand + " " + _name + " " + _clockRate + " МГц" +
                (_videoRAM > 0 ? " и " + _videoRAM + "Гб видеопамяти G" + _typeRAM : "");
    }

}
