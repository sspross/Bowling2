package ch.hsz.bowling;
import ch.hsz.bowling.util.FileWriter;
import ch.hsz.bowling.util.PropertiesReader;
import ch.hsz.bowling.util.Vector;

/**
 * Bowling-Simulator
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.3
 */
public class Bowling {
	public static final String PROP_PROGRAM_NAME    = "program.name";
	public static final String PROP_PROGRAM_VERSION = "program.version";
	
	public static final String PROP_DEBUG = "debug";
	
	public static final String PROP_PATH = "path";

	public static final String PROP_GRAVITY = "global.gravity";

	public static final String PROP_TIME_START    = "time.start";
	public static final String PROP_TIME_STOP     = "time.stop";
	public static final String PROP_TIME_INTERVAL = "time.interval";

	public static final String PROP_PLAYER_NAME 	  = "player.name";
	public static final String PROP_PLAYER_POWER_X 	  = "player.power.x";
	public static final String PROP_PLAYER_POWER_Y 	  = "player.power.y";
	public static final String PROP_PLAYER_ROTATION_X = "player.rotation.x";
	public static final String PROP_PLAYER_ROTATION_Y = "player.rotation.y";

	public static final String PROP_COURT_FRICTION = "court.friction";
	public static final String PROP_COURT_SIZE_X   = "court.size.x";
	public static final String PROP_COURT_SIZE_Y   = "court.size.y";
	
	public static final String PROP_BALL_FRICTION   = "ball.friction";
	public static final String PROP_BALL_POSITION_X = "ball.position.x";
	public static final String PROP_BALL_POSITION_Y = "ball.position.y";
	public static final String PROP_BALL_MASS       = "ball.mass";
	public static final String PROP_BALL_RADIUS     = "ball.radius";

	private static String programName;
	private static String programVersion;
	
	public static boolean debug = true;
	
	private static String path = "C:/Temp/";
	
	public static double timeStart;
	public static double timeStop;
	public static double timeInterval;
	
	private static double gravity;
	
	private static String playerName;
	private static double playerPowerX;
	private static double playerPowerY;
	private static double playerRotationX;
	private static double playerRotationY;
	
	private static double courtFriction;
	private static double courtSizeX;
	private static double courtSizeY;
	
	private static double ballFriction;
	private static double ballPositionX;
	private static double ballPositionY;
	private static double ballMass;
	private static double ballRadius;
	
	public static FileWriter fileWriter;
	
	public static void main(String[] args) {
		// read Properties
		if (args.length != 1) {
			String message = "Wrong number of parameters - must be like: -java Bowling standard.properties";
//			FileWriter.writeException("PropertiesReader::main", message);
			System.err.println(message);
			System.exit(666);
		}
		readProperties(args[0]);
		
		// init FileWriter
		fileWriter = new FileWriter(path);
		
		// log start
		FileWriter.writeLog("Bowling::main", "### Program started: " + programName + " " + programVersion + " ###");
		
		// create player
		Player player = new Player(playerName);
		
		// player state
		Vector power = new Vector(playerPowerX, playerPowerY);
		Vector rotation = new Vector(playerRotationX, playerRotationY);
		
		// create court with dimension
		Court court = new Court(new Vector(courtSizeX, courtSizeY), courtFriction);
		
		// create ball
		Vector startPosition = new Vector(ballPositionX, ballPositionY);
		Ball ball = new Ball(gravity, ballMass, ballRadius, ballFriction, startPosition, court);

		// start simulation
		FileWriter.writeLog("Bowling::main", "start simulation");
		player.play(power, rotation, ball, court);
		
		// log end
		FileWriter.writeLog("Bowling::main", "### Program successful ended ###");
		System.exit(0);
	}
	
	private static void readProperties(String fileName) {
		PropertiesReader propertiesReader = new PropertiesReader(fileName);
		
		programName = propertiesReader.getProperty(PROP_PROGRAM_NAME);
		programVersion = propertiesReader.getProperty(PROP_PROGRAM_VERSION);

//		debug = propertiesReader.getPropertyBoolean(PROP_DEBUG);
		
		path = propertiesReader.getProperty(PROP_PATH);
		
		timeStart = propertiesReader.getPropertyDouble(PROP_TIME_START);
		timeStop = propertiesReader.getPropertyDouble(PROP_TIME_STOP);
		timeInterval = propertiesReader.getPropertyDouble(PROP_TIME_INTERVAL);
		
		gravity = propertiesReader.getPropertyDouble(PROP_GRAVITY);
		
		playerName = propertiesReader.getProperty(PROP_PLAYER_NAME);
		playerPowerX = propertiesReader.getPropertyDouble(PROP_PLAYER_POWER_X);
		playerPowerY = propertiesReader.getPropertyDouble(PROP_PLAYER_POWER_Y);
		playerRotationX = propertiesReader.getPropertyDouble(PROP_PLAYER_ROTATION_X);
		playerRotationY = propertiesReader.getPropertyDouble(PROP_PLAYER_ROTATION_Y);
		
		courtFriction = propertiesReader.getPropertyDouble(PROP_COURT_FRICTION);
		courtSizeX = propertiesReader.getPropertyDouble(PROP_COURT_SIZE_X);
		courtSizeY = propertiesReader.getPropertyDouble(PROP_COURT_SIZE_Y);
		
		ballFriction = propertiesReader.getPropertyDouble(PROP_BALL_FRICTION);
		ballPositionX = propertiesReader.getPropertyDouble(PROP_BALL_POSITION_X);
		ballPositionY = propertiesReader.getPropertyDouble(PROP_BALL_POSITION_Y);
		ballMass = propertiesReader.getPropertyDouble(PROP_BALL_MASS);
		ballRadius = propertiesReader.getPropertyDouble(PROP_BALL_RADIUS);
		
		if (debug) propertiesReader.diagProperties();
	}
}
