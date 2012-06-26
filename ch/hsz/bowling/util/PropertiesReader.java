package ch.hsz.bowling.util;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Lesen der Properties
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 1.0
 */
public class PropertiesReader {
    private Properties properties;

    public PropertiesReader(String propertiesFile) {
    	this.properties = new Properties();
    	try {
    		this.properties.load(new FileInputStream(propertiesFile));
		} catch (Exception e) {
//			FileWriter.writeException("PropertiesReader::<constructor>", e.getMessage());
			e.printStackTrace();
			System.exit(666);
		}
    }
    
    public String getProperty(String propertyName) {
        String propertyValue = this.properties.getProperty(propertyName);
        return propertyValue;
    }
    
    public double getPropertyDouble(String propertyName) {
        double propertyValue = Double.parseDouble(this.properties.getProperty(propertyName));
        return propertyValue;
    }

    public int getPropertyInt(String propertyName) {
        int propertyValue = Integer.parseInt(this.properties.getProperty(propertyName));
        return propertyValue;
    }
    
    public boolean getPropertyBoolean(String propertyName) {
        boolean propertyValue = Boolean.getBoolean(this.properties.getProperty(propertyName));
        return propertyValue;
    }
    
    public void diagProperties() {
        Enumeration enumeration = this.properties.propertyNames();
        System.out.println("## SystemProperties ##");
        
        while (enumeration.hasMoreElements()) {
        	String propertyName = (String)enumeration.nextElement(); 
        	System.out.println(propertyName + ": " + this.properties.getProperty(propertyName));
        }
    }
}   

