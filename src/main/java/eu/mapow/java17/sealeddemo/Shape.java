package eu.mapow.java17.sealeddemo;

import eu.mapow.java17.sealeddemo.Shape.Square;
import eu.mapow.java17.sealeddemo.Shape.WeirdShape;

/**
 * @author mapow
 * @since 2024-12-03
 */
public abstract sealed class Shape permits Circle, Rectangle, Square, WeirdShape {

    /**
     * Without extends Shape: "Permitted class Square does not declare de.mapow.java17.Shape as direct super class"
     */
    public final class Square extends Shape {
    }

    /**
     * Without extends Shape: "Class WeirdShape declared as non-sealed should have either a sealed direct superclass or
     * a sealed direct superinterface"
     */
    public non-sealed class WeirdShape extends Shape {
    }

}
