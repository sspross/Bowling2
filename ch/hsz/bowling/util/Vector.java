package ch.hsz.bowling.util;
/**
 * Vector
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.5
 */
public class Vector {

	private double x;
	private double y;
	private double ratio = 1.0;
	
	public Vector() {
		//pass
	}

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getAbsValue(){
		
		return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
		
	}
	
	public double getX() {
		return this.x * this.ratio;
	}
	
	public double getXAbs() {
		return Math.abs(this.x * this.ratio);
	}
	
	public double getYAbs() {
		return Math.abs(this.x * this.ratio);
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return this.y * this.ratio;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "<Vector>:\nx: " + this.x + "\ny: " + this.y + "\nratio: " + this.ratio;
	}
}