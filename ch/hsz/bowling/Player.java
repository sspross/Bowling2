package ch.hsz.bowling;
import ch.hsz.bowling.util.FileWriter;
import ch.hsz.bowling.util.Vector;

/**
 * Player
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.3
 */
public class Player {

	private String 	name;
	private Vector 	power;
	private Vector 	rotation;
	private Ball	ball;
	private Court	court;
	
	public Player(String name) {
		this.name = name;
	}
	
	public void play(Vector power, Vector rotation, Ball ball, Court court) {
		this.power = power;
		this.rotation = rotation;
		this.ball = ball;
		this.court = court;

		FileWriter.writeDebug("Player::play", power.getX() + ";" + power.getY() + "\t" + rotation.getX() + ";" + rotation.getY());
		
		this.ball.setPower(power);
		this.ball.setRotation(rotation);
		
		// Debug
		if (Bowling.debug) FileWriter.writeDebug("Player::play", this.toString());
		
		try {
			this.ball.roll();
		} catch (Exception e){
			FileWriter.writeException("Player::play", e.getMessage());
			e.printStackTrace();
			System.exit(666);
		}
	}

	public Ball getBall() {
		return this.ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public Vector getRotation() {
		return this.rotation;
	}

	public void setRotation(Vector rotation) {
		this.rotation = rotation;
	}

	public Vector getpower() {
		return this.power;
	}

	public void setpower(Vector power) {
		this.power = power;
	}

	public Court getCourt() {
		return this.court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

	public Vector getPower() {
		return this.power;
	}

	public void setPower(Vector power) {
		this.power = power;
	}
	
	public String toString() {
		return "<Player>:\nname: " + this.name + "\npower: " + this.power.toString() + "\nrotation: " + this.rotation.toString() + "\nball: " + this.ball.toString()+ "\ncourt: " + this.court.toString();
	}
}
