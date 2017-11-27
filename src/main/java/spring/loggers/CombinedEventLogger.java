package spring.loggers;

import spring.events.AbstractEvent;

import java.util.Collection;

public class CombinedEventLogger implements EventLogger {
	private Collection<EventLogger> loggers;

	public CombinedEventLogger(Collection<EventLogger> loggers) {
		this.loggers = loggers;
	}

	public Collection<EventLogger> getLoggers() {
		return loggers;
	}

	public void setLoggers(Collection<EventLogger> loggers) {
		this.loggers = loggers;
	}

	@Override
	public void logEvent(AbstractEvent event) {
		for (EventLogger logger : loggers) {
			logger.logEvent(event);
		}
	}
}
