package ch.hsz.bowling;
import java.util.ArrayList;

import ch.hsz.bowling.util.Chart;
import ch.hsz.bowling.util.FileWriter;
import ch.hsz.bowling.util.Vector;

/**
 * Ball
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.3
 */
public class Ball {
	
	private Vector power;
	private Vector rotation;
	private Vector position;
	private Court court;
	private double radius;
	private double traegheitsmoment;
	private double mass;
	private double gravity;
	private double friction;
	private double frictionPower;

	public Ball(double gravity, double mass, double radius, double friction, Vector position, Court court) {
		this.mass = mass;
		this.radius = radius;
		this.traegheitsmoment = _calculateTraegheitsmoment();
		this.gravity = gravity;
		this.friction = friction;
		this.position = position;
		this.court = court;
		this.frictionPower = _calculateFrictionPower(this.court);
	}
	
	public void roll() throws Exception {
		ArrayList dataList = new ArrayList();
		
		// Debug
		if (Bowling.debug) FileWriter.writeDebug("Ball::roll", this.toString());

//		System.out.println("Startet Bowlingsimulation");
//		System.out.println();
//		System.out.println("Startwerte X: " + this.position.getX() + "\tY: " + this.position.getY());
//		System.out.println();
//		System.out.println(this.position.getX() + "\t" + this.position.getY());

		for (int i = (int)Bowling.timeStart; i < (int)(Bowling.timeStop / Bowling.timeInterval); i++) {

			if (!this.court.isValidPosition(this.position)) {
				
//				throw new Exception("Ball is outside the court!");
				break;
			}
			
			double frictionPower = _calculateFrictionPower(this.court);
			
			Vector direction = _calculateDirection(frictionPower);
		
			// F = m * a; s = a * t^2   --->   s = F / m * t^2
			
			Vector oldPosition = _calculateOldPosition();
			
			_calculateNewPosition(direction);
			
			Vector deltaPosition = _calculateDeltaPosition(oldPosition);
			
			_calculateNewPower(deltaPosition);
			
			_calculateNewRotation(deltaPosition);
			
//			double multiplierX = 1.0;
//			
//			if (this.rotation.getX() < 0) multiplierX = -1.0;
//
//			double multiplierY = 1.0;
//			
//			if (this.rotation.getY() < 0) multiplierY = -1.0;
//			
//			this.rotation.setX(this.rotation.getX() - multiplierX * (this.mass * Math.abs((this.position.getX() - oldPosition.getX())) * Math.pow(this.rotation.getX() / this.mass * Bowling.timeInterval,2)/2 - this.rotation.getX() * (this.friction + this.court.getFriction())));
//			this.rotation.setY(this.rotation.getY() - multiplierY * (this.mass * Math.abs((this.position.getY() - oldPosition.getY())) * Math.pow(this.rotation.getY() / this.mass * Bowling.timeInterval,2)/2 - this.rotation.getY() * (this.friction + this.court.getFriction())));
			
			
			/*
			
			this.power.setX(this.power.getX() + this.rotation.getX() * (this.court.getFriction() + this.friction) - (Math.sqrt((this.friction + this.court.getFriction()) / 2) *  (this.power.getX() + this.rotation.getX() * this.court.getFriction())));
			this.power.setY(this.power.getY() + this.rotation.getY() * (this.court.getFriction() + this.friction) - (Math.sqrt((this.friction + this.court.getFriction()) / 2) *  (this.power.getY() + this.rotation.getY() * this.court.getFriction())));

            */

			//if ( i % 10 == 0) FileWriter.writeCsv(this.position.getX() + "," + this.position.getY());

			dataList.add(this.position);
			FileWriter.writeCsv(this.position.getX() + ";" + this.position.getY());
			
			FileWriter.writeDebug("Ball::roll", "this.power: " + this.power.getAbsValue() + "this.rotation: " + this.rotation.getAbsValue());

		}
		System.out.println("MUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUH");
		new Chart("TestChart", "X", "Y", dataList);
		
		// Haesslicher geht's nimmer
//		for (int ii = 0; ii == ii; ii++) {
//			//...
//		}
	}
	
	private Vector _calculateDirection(double frictionPower) throws Exception {
		
		Vector direction = new Vector();
		
		direction.setX(this.power.getX() + this.rotation.getX());
		direction.setY(this.power.getY() + this.rotation.getY());
		
		if (frictionPower >= direction.getAbsValue()) {
			throw new Exception("FrictionPower higher than DirectionPower");
		}
		
		direction.setRatio(1-(direction.getAbsValue() / 100 * frictionPower)/100 );
		
		return direction;
	}
	
	private void _calculateNewRotation(Vector deltaPosition){
		
//		this.rotation.setX(this.rotation.getX() - this.traegheitsmoment * Math.pow(deltaPosition.getXAbs() / (this.radius * Bowling.timeInterval), 2) / (_calculateFriction(this.court) * this.gravity));
//		this.rotation.setY(this.rotation.getY() - this.traegheitsmoment * Math.pow(deltaPosition.getYAbs() / (this.radius * Bowling.timeInterval), 2) / (_calculateFriction(this.court) * this.gravity));
//		
		this.rotation.setX(this.rotation.getX() * (1-this.rotation.getXAbs() / 100 * (this.traegheitsmoment * Math.pow(deltaPosition.getXAbs() / (this.radius * Bowling.timeInterval), 2) / (_calculateFriction(this.court) * this.gravity)) / 100));
		this.rotation.setY(this.rotation.getY() * (1-this.rotation.getYAbs() / 100 * (this.traegheitsmoment * Math.pow(deltaPosition.getYAbs() / (this.radius * Bowling.timeInterval), 2) / (_calculateFriction(this.court) * this.gravity)) / 100));

	}
	
	private void _calculateNewPower(Vector deltaPosition){
		this.power.setX(this.power.getX() - Math.sqrt(_calculateFriction(this.court) * this.gravity * deltaPosition.getXAbs()) * this.mass / Bowling.timeInterval);
		this.power.setY(this.power.getY() - Math.sqrt(_calculateFriction(this.court) * this.gravity * deltaPosition.getYAbs()) * this.mass / Bowling.timeInterval);
	}

	private double _calculateFrictionPower(Court court) {
		return (this.mass * this.gravity * _calculateFriction(court));
	}
	
	private double _calculateFriction(Court court) {
		return this.friction + court.getFriction(this.position);
	}
	
	private Vector _calculateDeltaPosition(Vector oldPosition){
		return new Vector (this.position.getX() - oldPosition.getX(), this.position.getY() - oldPosition.getY());
	}
	
	private Vector _calculateOldPosition(){
		return new Vector (this.position.getX(), this.position.getY());
	}
	
	private void _calculateNewPosition(Vector direction) {
		this.position.setX(this.position.getX() + direction.getX() / this.mass * Bowling.timeInterval * Bowling.timeInterval / 2 );
		this.position.setY(this.position.getY() + direction.getY() / this.mass * Bowling.timeInterval * Bowling.timeInterval / 2 );
	}
	
	private double _calculateTraegheitsmoment(){
		
		return 0.4 * this.mass * this.radius * this.radius;

	}
	
	public Vector getRotation() {
		return this.rotation;
	}

	public void setRotation(Vector rotation) {
		this.rotation = rotation;
	}

	public Vector getPower() {
		return this.power;
	}

	public void setPower(Vector power) {
		this.power = power;
	}

	public double getFriction() {
		return this.friction;
	}

	public void setFriction(double friction) {
		this.friction = friction;
	}

	public double getMass() {
		return this.mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public double getFrictionPower() {
		return this.frictionPower;
	}

	public void setFrictionPower(double frictionPower) {
		this.frictionPower = frictionPower;
	}

	public Vector getPosition() {
		return this.position;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}

	public String toString() {
		return "Ball:\npower: " + this.power.toString() + "\nrotation: " + this.rotation.toString() + "\nposition: " + this.position.toString() + "\ncourt: " + this.court.toString() + "\nmass: " + this.mass + "\ngravity: " + this.gravity + "\nfriction: " + this.friction + "\nfrictionPower: " + this.frictionPower;
	}
}