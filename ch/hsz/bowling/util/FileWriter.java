package ch.hsz.bowling.util;
import java.io.*;
import java.util.Date;

/**
 * Logger zum Aufzeichnen aller Aktivitaeten
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 1.0
 */
public class FileWriter {
	private static PrintWriter logFile;
	private static String path;
	
	public FileWriter(String path) {
		if (path != null) {
			FileWriter.path = path + "Bowling"; //TODO unschoen
		}
		
		// delete files
		new File(FileWriter.path + ".csv").delete();
		new File(FileWriter.path + "_Warning.log").delete();
		new File(FileWriter.path + "_Debug.log").delete();
		new File(FileWriter.path + "_Exception.log").delete();
		new File(FileWriter.path + ".log").delete();
	}
	
	/**
     * Prueft den Eintrag des Aufrufs
     * @param entry Log-Eintrag
     * @return Validierter/Korrigierter Log-Eintrag
     */
	private static String checkEntry(String entry) {
		if (entry == null) {
			return "n/a";
		}
		return entry;
	}

	/**
     * Prueft die Herkunft des Aufrufs
     * @param descent Aufrufende Klasse::Methode
     * @return Validierte/Korrigierte Herkunft
     */
	private static String checkDescent(String descent) {
		if (descent == null) {
			return "unknown class::method";
		}
		return descent;
	}

	/**
     * Schreibt physisch in die gewaehlte Log-Datei
     * @param descent Aufrufende Klasse::Methode
     * @param loginId Login-ID des Benutzers
     * @param entry Log-Eintrag
     * @param logFilePfad Pfad der Log-Datei
     */
	private static void writeLogEntry(String descent, String entry, String logFilePfad) {
		try {
			logFile = new PrintWriter(new OutputStreamWriter(new FileOutputStream(logFilePfad, true), "UTF-8"));    
			logFile.println((new Date()).toLocaleString() + " - " + checkDescent(descent) + " - " + checkEntry(entry));
			logFile.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(666);
        }
	}

	/**
     * Schreibt in die WARNING-Datei
     * @param descent Aufrufende Klasse::Methode
     * @param loginId Login-ID des Benutzers
     * @param entry Warnung-Log-Eintrag
     */
	public static void writeWarning(String descent, String entry) {
		writeLogEntry(descent, entry, path + "_Warning.log");
 	}
	
	/**
     * Schreibt in die DEBUG-Datei
     * @param descent Aufrufende Klasse::Methode
     * @param loginId Login-ID des Benutzers
     * @param entry Debug-Log-Eintrag
     */
	public static void writeDebug(String descent, String entry) {
		writeLogEntry(descent, entry, path + "_Debug.log");
 	}

	/**
     * Schreibt in die EXCEPTION-Datei
     * @param descent Aufrufende Klasse::Methode
     * @param loginId Login-ID des Benutzers
     * @param entry Exception-Beschreibung
     */
	public static void writeException(String descent, String entry) {
		writeLogEntry(descent, entry, path + "_Exception.log");
	}

	/**
     * Schreibt in die Log-Datei
     * @param descent Aufrufende Klasse::Methode
     * @param entry Log-Eintrag
     */
	public static void writeLog(String descent, String entry) {
		writeLogEntry(descent, entry, path + ".log");
	}
	
	/**
     * Schreibt eine CSV-Datei
     * @param entry CSV-Zeile
     */
	public static void writeCsv(String entry) {
		try {
			PrintWriter file = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path + ".csv", true), "UTF-8"));    
			file.println(checkEntry(entry));
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(666);
        }
	}
}
