package tools;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log
{
	static final Logger logger = Logger.getLogger("appLog");
	
	public Log() {
		
	}
	
	public void info(String message) {
		logger.log(Level.INFO, (String) message);
	}
	
	public void warn(String message) {
		logger.log(Level.WARNING, (String) message);
	}

	public void message(String message, Level lvl) {
		logger.log(lvl, (String) message);
	}
}
