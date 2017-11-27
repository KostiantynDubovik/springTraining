package spring.loggers;

import spring.events.AbstractEvent;

public class ConsoleEventLogger implements EventLogger {
	@Override
	public void logEvent(AbstractEvent event) {
		System.err.println(event.getMessage());
	}
}
