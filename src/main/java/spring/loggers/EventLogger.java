package spring.loggers;

import spring.events.AbstractEvent;

public interface EventLogger {
	void logEvent(AbstractEvent event);
}
