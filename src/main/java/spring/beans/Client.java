package spring.beans;

import spring.events.EventType;

public class Client {
	private String id;
	private String fullName;
	private String normalGreeting;
	private String errorGreeting;

	public Client(String id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setNormalGreeting(String normalGreeting) {
		this.normalGreeting = normalGreeting;
	}

	public String getNormalGreeting() {
		return normalGreeting;
	}

	public String getErrorGreeting() {
		return errorGreeting;
	}

	public void setErrorGreeting(String errorGreeting) {
		this.errorGreeting = errorGreeting;
	}

	public String getGreeting(EventType type){
		return EventType.INFO.equals(type) ? normalGreeting: errorGreeting;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Client client = (Client) o;

		if (id != null ? !id.equals(client.id) : client.id != null) return false;
		if (fullName != null ? !fullName.equals(client.fullName) : client.fullName != null) return false;
		return normalGreeting != null ? normalGreeting.equals(client.normalGreeting) : client.normalGreeting == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
		result = 31 * result + (normalGreeting != null ? normalGreeting.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Client{" +
				"id='" + id + '\'' +
				", fullName='" + fullName + '\'' +
				", normalGreeting='" + normalGreeting + '\'' +
				'}';
	}
}