package spring.loggers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spring.events.AbstractEvent;
import spring.events.Event;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
	private static final Logger LOGGER = LogManager.getLogger(FileEventLogger.class);
	private String filename;
	private File logFile;

	public FileEventLogger(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	private void init() throws IOException {
		logFile = new File(filename);
		if (!logFile.createNewFile() && !logFile.canWrite()) {
			throw new IOException();
		}
	}

	@Override
	public void logEvent(AbstractEvent event) {
		try (FileOutputStream fos = new FileOutputStream(logFile, true)) {
			fos.write(event.getMessage().getBytes());
		} catch (IOException e) {
			LOGGER.error(e);
		}
	}
}
