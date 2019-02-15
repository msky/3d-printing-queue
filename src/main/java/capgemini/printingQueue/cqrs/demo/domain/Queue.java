package capgemini.printingQueue.cqrs.demo.domain;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class Queue {
	
	@AggregateIdentifier
	private String id;
	
	private String name;

	
	public Queue() {
	}
	
	@CommandHandler
	public Queue(CreateQueueCommand command) {
		AggregateLifecycle.apply(new QueueCreatedEvent(command.getId(), command.getName()));
	}
	
	@EventSourcingHandler
	public void on(QueueCreatedEvent event) {
		id = event.getId();
		name = event.getName();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
