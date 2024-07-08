package Toolbox.Math;

public class Clamp {
    public static float clamp(float value, float min, float max) {
        return Math.min(max, Math.max(min, value));
    }

    public static double clamp(double value, double min, double max) {
        return Math.min(max, Math.max(min, value));
    }

    // Optionally add overloaded methods for integer types if needed
}
