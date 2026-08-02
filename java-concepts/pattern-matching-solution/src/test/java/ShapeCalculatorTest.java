import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ShapeCalculator")
class ShapeCalculatorTest {

    @Test
    @DisplayName("should compute the area of a circle")
    void shouldComputeCircleArea() {
        double area = ShapeCalculator.area(new Circle(2));
        assertEquals(Math.PI * 4, area, 0.0001);
    }

    @Test
    @DisplayName("should compute the area of a rectangle")
    void shouldComputeRectangleArea() {
        double area = ShapeCalculator.area(new Rectangle(3, 4));
        assertEquals(12.0, area, 0.0001);
    }

    @Test
    @DisplayName("should compute the area of a triangle")
    void shouldComputeTriangleArea() {
        double area = ShapeCalculator.area(new Triangle(6, 4));
        assertEquals(12.0, area, 0.0001);
    }

    @Test
    @DisplayName("should describe null as nothing to describe")
    void shouldDescribeNullAsNothingToDescribe() {
        assertEquals("nothing to describe", ShapeCalculator.describe(null));
    }

    @Test
    @DisplayName("should describe a circle with its radius")
    void shouldDescribeCircleWithItsRadius() {
        assertEquals("circle with radius 2.5", ShapeCalculator.describe(new Circle(2.5)));
    }

    @Test
    @DisplayName("should describe a single-character string as short")
    void shouldDescribeShortString() {
        assertEquals("short string: x", ShapeCalculator.describe("x"));
    }

    @Test
    @DisplayName("should describe a multi-character string without the short prefix")
    void shouldDescribeLongString() {
        assertEquals("string: hello", ShapeCalculator.describe("hello"));
    }

    @Test
    @DisplayName("should fall back to a generic description for unrecognized types")
    void shouldDescribeUnrecognizedObject() {
        assertEquals("unrecognized: 42", ShapeCalculator.describe(42));
    }
}
