package capgemini.printingQueue.cqrs.demo.server.domain;

public class PrinterCreatedEvent {
	
	private final String id;
	
	private final String name;
	
	public PrinterCreatedEvent(String id, String name) {
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
