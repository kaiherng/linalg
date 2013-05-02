package oldfrontend.shapes;

import java.awt.Dimension;

/**
 * A 2D screen coordinate
 * @author Kai
 *
 */
public final class Coord {

	/**
	 * The x and y values of the coordinate
	 */
	public final int x, y;
	
	/**
	 * Creates a new vector from an x and y component.
	 * @param x      the x-component of the vector
	 * @param y      the y-component of the vector
	 */
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Creates a new vector from a Dimension object
	 * @param dim the dimension to create the vector from
	 */
	public Coord(Dimension dim) {
		this.x = (int) dim.getWidth();
		this.y = (int) dim.getHeight();
	}
	

	/*
	 * Vector ops
	 */
	
	/**
	 * Multiplies the vector by a scalar.
	 * @param s      the scalar by which to multiply this vector
	 * @return       a new {@link Coord} instance where each component has been multiplied by {@code s}
	 */
	public final Coord smult(int s) {
		return new Coord(x*s, y*s);
	}
	
	/**
	 * Divides the vector by a scalar. Note that this is integer division.
	 * @param s		the scalar by which to divide this vector
	 * @return		a new {@link Coord} instance where each component has been divided by
	 * 				{@code s}
	 */
	public final Coord sdiv(int s) {
		return new Coord(x/s, y/s);
	}
	
	/**
	 * Multiplies the vector piecewise by another vector. NOT A DOT PRODUCT.
	 * @param v      the vector by which to multiply this vector
	 * @return       a new {@link Coord} instance where each component has been multiplied by the corresponding component in {@code v}
	 */
	public final Coord pmult(Coord v) {
		return new Coord(x*v.x, y*v.y);
	}
	
	/**
	 * Primitive version of {@link #pmult(Coord)}.
	 */
	public final Coord pmult(int x, int y) {
		return new Coord(this.x * x, this.y * y);
	}
	
	/**
	 * Divides the vector piecewise by another vector.
	 * @param v      the vector by which to divide this vector
	 * @return       a new {@link Coord} instance where each component has been divided by the corresponding component in {@code v}
	 */
	public final Coord pdiv(Coord v) {
		return new Coord(x/v.x, y/v.y);
	}
	
	/**
	 * Primitive version of {@link #pdiv(Coord)}.
	 */
	public final Coord pdiv(int x, int y) {
		return new Coord(this.x/x, this.y/y);
	}
	
	/**
	 * Adds another vector to this vector.
	 * @param v      the vector to add to this vector
	 * @return       a new {@link Coord} instance where each component has added the corresponding component in {@code v}
	 */
	public final Coord plus(Coord v) {
		return new Coord(x + v.x, y + v.y);
	}
	
	/**
	 * Primitive version of {@link #plus(Coord)}.
	 */
	public final Coord plus(int x, int y) {
		return new Coord(this.x + x, this.y + y);
	}
	
	/**
	 * Subtracts another vector from this vector.
	 * @param v      the vector to subtract from this vector
	 * @return       a new {@link Coord} instance where each component has added the corresponding component in {@code v}
	 */
	public final Coord minus(Coord v) {
		return new Coord(x - v.x, y - v.y);
	}
	
	/**
	 * Primitive version of {@link #minus(Coord)}.
	 */
	public final Coord minus(int x, int y) {
		return new Coord(this.x - x, this.y - y);
	}
	
	/**
	 * Returns the two-dimensional cross product of this vector and {@code v}, 
	 * which will be positive if the result is in the positive z-direction 
	 * ("out of the plane") and negative if the result is in the negative z-
	 * direction ("into the plane").
	 * @param v		the vector with which to take the cross product
	 * @return		the cross-product of this vector and {@code v}
	 */
	public final float cross(Coord v) {
		return x*v.y - y*v.x;
	}
	
	/*
	 * Object overrides
	 */

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (obj == null || obj.getClass() != Coord.class)
			return false;
		Coord other = (Coord) obj;
		return x == other.x && y == other.y;
	}

	@Override
	public final String toString() {
		return new StringBuilder("(").append(x).append(", ").append(y).append(")").toString();
	}
}
