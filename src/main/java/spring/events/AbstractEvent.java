package spring.events;

import spring.utils.IDGenerator;

import java.text.DateFormat;
import java.util.Date;

public abstract class AbstractEvent {
	private String id;
	private String message;
	private Date date;
	private DateFormat dateFormat;

	public AbstractEvent() {
		this.id=IDGenerator.generateID();
	}

	public AbstractEvent(Date date, DateFormat dateFormat) {
		this.id = IDGenerator.generateID();
		this.date = date;
		this.dateFormat = dateFormat;
	}

	public String getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AbstractEvent that = (AbstractEvent) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (message != null ? !message.equals(that.message) : that.message != null) return false;
		if (date != null ? !date.equals(that.date) : that.date != null) return false;
		return dateFormat != null ? dateFormat.equals(that.dateFormat) : that.dateFormat == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (message != null ? message.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (dateFormat != null ? dateFormat.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "AbstractEvent{" +
				"id='" + id + '\'' +
				", message='" + message + '\'' +
				", date=" + dateFormat.format(date) +
				'}';
	}
}