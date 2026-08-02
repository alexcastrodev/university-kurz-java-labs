public class ShapeCalculator {

    public static double area(Shape shape) {
        return switch (shape) {
            case Circle c -> Math.PI * c.radius() * c.radius();
            case Rectangle r -> r.width() * r.height();
            case Triangle t -> 0.5 * t.base() * t.height();
        };
    }

    public static String describe(Object obj) {
        return switch (obj) {
            case null -> "nothing to describe";
            case Circle(double radius) -> "circle with radius " + radius;
            case String s when s.length() == 1 -> "short string: " + s;
            case String s -> "string: " + s;
            default -> "unrecognized: " + obj;
        };
    }
}

sealed interface Shape permits Circle, Rectangle, Triangle {}
record Circle(double radius) implements Shape {}
record Rectangle(double width, double height) implements Shape {}
record Triangle(double base, double height) implements Shape {}
