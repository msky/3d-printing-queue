package capgemini.printingQueue.cqrs.demo.server.domain;

public class QueueCreatedEvent {
	
	private final String id;
	
	private final String name;
	
	public QueueCreatedEvent(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
