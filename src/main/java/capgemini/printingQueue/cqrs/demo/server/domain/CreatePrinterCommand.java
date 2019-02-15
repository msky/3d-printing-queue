package capgemini.printingQueue.cqrs.demo.server.domain;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreatePrinterCommand {
	
	@TargetAggregateIdentifier
	private final String id;
	
	private final String name;

	public CreatePrinterCommand(String queueId, String queueName) {
		this.id = queueId;
		this.name = queueName;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
