package tools;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log
{
	static final Logger logger = Logger.getLogger("appLog");
	
	public static void info(String message) {
		logger.log(Level.INFO, (String) message);
	}
	
	public static void warn(String message) {
		logger.log(Level.WARNING, (String) message);
	}

	public static void message(String message, Level lvl) {
		logger.log(lvl, (String) message);
	}
}
