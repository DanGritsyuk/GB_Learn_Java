package Exercises.FinalProject.Structs;

public enum TypeRAM {
    DDR2 {
        @Override
        public String toString() {
            return "DDR2";
        }
    },
    DDR3 {
        @Override
        public String toString() {
            return "DDR3";
        }
    },
    DDR4 {
        @Override
        public String toString() {
            return "DDR4";
        }
    },
    DDR5 {
        @Override
        public String toString() {
            return "DDR5";
        }
    }
}