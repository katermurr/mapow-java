package de.mapow.java14;

/**
 *
 * https://openjdk.org/jeps/359
 *
 * <pre>
 * - private, final field for each piece of data
 * - getter for each field
 * - public constructor with a corresponding argument for each field
 * - equals method that returns true for objects of the same class when all fields match
 * - hashCode method that returns the same value when all fields match
 * - toString method that includes the name of the class and the name of each field and its corresponding value
 * </pre>
 *
 * @author mapow
 * @since 2024-11-30
 */
public record RecordDto(double latitude, double longitude) {

}
