package Exercises.FinalProject.Structs;

public enum TypeROM {
    SSD {
        @Override
        public String toString() {
            return "SSD";
        }
    },
    HDD {
        @Override
        public String toString() {
            return "HDD";
        }
    }
}