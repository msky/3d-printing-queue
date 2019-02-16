package capgemini.printingQueue.cqrs.demo.server.domain;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.modelling.command.ForwardMatchingInstances;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class Printer {
    
	@AggregateIdentifier
	private String id;
	
	private String name;
	
	@AggregateMember(eventForwardingMode = ForwardMatchingInstances.class)
	private Queue queue;
	
	
	public Printer() {
	    queue = new Queue(id);
	}
	
	@CommandHandler
	public Printer(CreatePrinterCommand command) {
		AggregateLifecycle.apply(new PrinterCreatedEvent(command.getId(), command.getName()));
	}
	
	@EventSourcingHandler
	public void on(PrinterCreatedEvent event) {
		id = event.getId();
		name = event.getName();
		queue = new Queue(id);
	}
	
    @CommandHandler
    public void on(AddNewPrintingCommand command) throws Exception {
        final Printing newPrinting = new Printing(command.getPrintingId(), command.getPrintingName(),
                command.getOwnerId(), command.getPrintingTime(), command.getPrintingStartDate(),
                command.getTechnicalBreakDuration());
        if (queue.isNewPrintingAddPossible(newPrinting, command.getTechnicalBreakDuration())) {
			AggregateLifecycle.apply(new NewPrintingAddedEvent(command.getPrinterId(), command.getPrintingName(),
					command.getPrintingId(), command.getOwnerId(), command.getPrintingTime(),
					command.getPrintingStartDate(), command.getTechnicalBreakDuration()));
        } else {
            throw new Exception("Printer busy on this time");
        }
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
	
    public Queue getQueue() {
        return queue;
    }

}
