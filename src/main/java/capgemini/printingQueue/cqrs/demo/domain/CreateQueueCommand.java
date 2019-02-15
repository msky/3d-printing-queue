package capgemini.printingQueue.cqrs.demo.domain;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateQueueCommand {
	
	@TargetAggregateIdentifier
	private final String id;
	
	private final String name;

	public CreateQueueCommand(String queueId, String queueName) {
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
