package ch.hsz.bowling;
import ch.hsz.bowling.util.FileWriter;
import ch.hsz.bowling.util.Vector;

/**
 * Court
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.3
 */
public class Court {

	private Vector dimension;
	private double friction;
	
	public Court(Vector dimension, double friction) {
		this.dimension = dimension;
		this.friction = friction;
	}
	
	public boolean isValidPosition(Vector position){
		// Debug
		if (Bowling.debug) FileWriter.writeDebug("Court::isValidPosition", position.toString());
		
		if ( (position.getX() <= this.dimension.getX() && position.getX() >= 0) && (position.getY() <= this.dimension.getY() && position.getY() >= 0) ){
			return true;
		}
		return false;
	}
	
	public double getFriction(Vector position){
		
		if (position.getY() <= this.dimension.getY() / 3) {
			
			return this.friction;
				
		}else if (position.getY() <= this.dimension.getY() / 3 * 2) {
			
			return this.friction * 2;
				
		}else if (position.getY() <= this.dimension.getY()) {
			
			return this.friction * 5;
				
		} else {
			
			return 0.0;
				
		}
		
	}

	public Vector getDimension() {
		return this.dimension;
	}

	public void setDimension(Vector dimension) {
		this.dimension = dimension;
	}

	public double getFriction() {
		return this.friction;
	}

	public void setFriction(double friction) {
		this.friction = friction;
	}
	
	public String toString() {
		return "<Court>:\ndimension: " + this.dimension.toString() + "\nfriction: " + this.friction;
	}
}