public class ShapeCalculator {

    public static double area(Shape shape) {
        // TODO-00: Return the area for each shape using a pattern-matching switch.
        // Hint: Shape is sealed with exactly three permitted subtypes (Circle, Rectangle,
        // Triangle) — the switch can be exhaustive without a default branch.
        // Circle area = PI * radius^2. Rectangle area = width * height.
        // Triangle area = 0.5 * base * height.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public static String describe(Object obj) {
        // TODO-01: Handle a null obj with an explicit "case null" branch,
        // returning "nothing to describe".
        // TODO-02: Handle a Circle using a record pattern that deconstructs its radius,
        // returning "circle with radius " + radius.
        // TODO-03: Handle a String using a guarded pattern (when) — if its length is
        // exactly 1, return "short string: " + s; otherwise return "string: " + s.
        // TODO-04: Fall back to "unrecognized: " + obj for anything else.
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}

sealed interface Shape permits Circle, Rectangle, Triangle {}
record Circle(double radius) implements Shape {}
record Rectangle(double width, double height) implements Shape {}
record Triangle(double base, double height) implements Shape {}
