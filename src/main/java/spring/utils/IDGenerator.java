package spring.utils;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

public class IDGenerator {
	private static final String PROPERTY_FILENAME = "spring/utils/utils.properties";
	private static final String ID_LENGTH_PROPERTY_NAME = "idLength";

	private static final Logger LOGGER = LogManager.getLogger(IDGenerator.class);

	private static int idLength;

	private static final String DEFAULT_VALUE = "10";
	private static final String CANDIDATE_CHARS = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

	private IDGenerator(){

	}

	static {
		Properties properties = new Properties();
		try (InputStream resourceStream = Thread.currentThread()
				.getContextClassLoader().getResourceAsStream(PROPERTY_FILENAME)) {
			properties.load(resourceStream);
		} catch (IOException e) {
			LOGGER.error(e);
		}
		String idLengthProperty = properties.getProperty(ID_LENGTH_PROPERTY_NAME, DEFAULT_VALUE);
		idLength = NumberUtils.isDigits(idLengthProperty)? Integer.parseInt(idLengthProperty)
				: Integer.parseInt(DEFAULT_VALUE);
		LOGGER.info("id generated");
	}

	public static String generateID(){
		StringBuilder id = new StringBuilder();
		for (int i = 0; i<idLength; i++){
			id.append(getChar());
		}
		return id.toString();
	}

	private static char getChar(){
		Random random = new Random();
		return CANDIDATE_CHARS.charAt(random.nextInt(CANDIDATE_CHARS.length()));
	}
}