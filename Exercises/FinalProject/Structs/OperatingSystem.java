package Exercises.FinalProject.Structs;

public enum OperatingSystem {

    Windows {
        @Override
        public String toString() {
            return "Windows";
        }
    },
    Linux {
        @Override
        public String toString() {
            return "NVIDIA";
        }
    },
    Another {
        @Override
        public String toString() {
            return "Another";
        }
    }
}
