package Exercises.FinalProject.NotebookComponents;

public class CentralProcessor {
    public CentralProcessor(String brand, String name, Double clockRate, int cores,
            GraphicProcessor gpu) {
        this._brand = brand;
        this._name = name;
        this._clockRate = clockRate;
        this._cores = cores;
        this._gpu = gpu;
    }

    private String _brand;
    private String _name;
    private Double _clockRate;
    private int _cores;
    private GraphicProcessor _gpu;

    public GraphicProcessor GetIntegratedGPU() {
        return _gpu;
    }

    @Override
    public String toString() {
        return "" + _brand + " " + _name + " x" + _cores + " " + _clockRate + " ГГц";
    }

}
