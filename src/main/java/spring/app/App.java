package spring.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.beans.Client;
import spring.events.AbstractEvent;
import spring.events.Event;
import spring.events.EventType;
import spring.loggers.EventLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class App {
	private static final String SPRING_XML_PROPERTY_NAME = "springConf";
	private static final String PROPERTY_FILENAME = "spring/app/app.properties";
	private static final Logger LOGGER = LogManager.getLogger(App.class);

	private static Properties properties;
	private static ConfigurableApplicationContext context;

	private final Client client;
	private final EventLogger defaultLogger;
	private final int eventCount;
	private final Map<EventType, EventLogger> loggerMap;

	public App(Client client, EventLogger defaultLogger, int eventCount, Map<EventType, EventLogger> loggerMap) {
		this.client = client;
		this.defaultLogger = defaultLogger;
		this.eventCount = eventCount;
		this.loggerMap = loggerMap;
	}

	public static void main(String[] args) {
		init();
		context = new ClassPathXmlApplicationContext(properties.getProperty(SPRING_XML_PROPERTY_NAME));
		App app = context.getBean(App.class);
		logFewEvents(app);
		context.close();
	}

	private static void logFewEvents(App app) {
		for (int i = 0; i < app.eventCount; i++) {
			EventType type = i % 3 == 0 ? EventType.ERROR : EventType.INFO;
			app.logEvent(type);
		}
	}

	private static void init() {
		properties = new Properties();
		try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTY_FILENAME)) {
			properties.load(is);
		} catch (IOException e) {
			LOGGER.error(e);
		}
	}

	private void logEvent(EventType eventType) {
		AbstractEvent event = context.getBean(Event.class);
		event.setMessage(client.getGreeting(eventType).replace(client.getId(), client.getFullName()).concat(System.lineSeparator()));
		EventLogger logger = loggerMap.get(eventType);
		if (logger != null) {
			logger.logEvent(event);
		} else {
			defaultLogger.logEvent(event);
		}
	}
}
