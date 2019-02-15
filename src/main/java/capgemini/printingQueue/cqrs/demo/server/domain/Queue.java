package capgemini.printingQueue.cqrs.demo.server.domain;

import java.util.ArrayList;
import java.util.List;

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
	
	private List<Printing> printingList = new ArrayList<Printing>();

	
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
	
	@CommandHandler
	public void on(AddNewPrintingCommand command) {
        AggregateLifecycle.apply(new NewPrintingAddedEvent(command.getQueueId(), command.getPrintingId(),
                command.getOwnerId(), command.getPrintingTime(), command.getPrintingStartDate()));
	}

	@EventSourcingHandler
	public void on(NewPrintingAddedEvent event) {
        final Printing newPrinting = new Printing(event.getPrintingId(), event.getOwnerId(), event.getPrintingTime(),
                event.getPrintingStartDate());
	    this.printingList.add(newPrinting);
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

    public List<Printing> getPrintingList() {
        return printingList;
    }

    public void setPrintingList(List<Printing> printingList) {
        this.printingList = printingList;
    }
	
}
