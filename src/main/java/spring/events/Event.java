package spring.events;

import java.text.DateFormat;
import java.util.Date;

public class Event extends AbstractEvent {
	public Event() {
	}

	public Event(Date date, DateFormat dateFormat) {
		super(date, dateFormat);
	}
}
