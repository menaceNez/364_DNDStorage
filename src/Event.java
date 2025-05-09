
public class Event {
	int id;
	String event;
	public Event(int id, String event) {
		this.id = id;
		this.event = event;
	}
	
	
	@Override
	public String toString() {
		return this.event;
	}
}
