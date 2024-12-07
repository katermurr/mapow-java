package eu.mapow.java17.sealeddemo;

import eu.mapow.java17.sealeddemo.Rectangle.FilledRectangle;
import eu.mapow.java17.sealeddemo.Rectangle.TransparentRectangle;

/**
 * https://openjdk.org/jeps/409 <br>
 * Goals:
 *
 * <pre>
   - Allow the author of a class or interface to control which code is responsible for implementing it.
   - Provide a more declarative way than access modifiers to restrict the use of a superclass.
   - Support future directions in pattern matching by providing a foundation for the exhaustive analysis of patterns.
 * </pre>
 *
 * @author mapow
 * @since 2024-12-03
 */
@SuppressWarnings("java:S6217")
public sealed class Rectangle extends Shape permits TransparentRectangle, FilledRectangle {

  public final class TransparentRectangle extends Rectangle {
  }

  public final class FilledRectangle extends Rectangle {
  }

}