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
    
    /**
     * Time for single technical break - preparing or cleaning - in miliseconds.
     * 15mins.
     */
    private static final Long TECHNICAL_BREAK_TIME = 900000L; 
	
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
	public void on(NewPrintingAddedEvent event) throws Exception {
        final Printing newPrinting = new Printing(event.getPrintingId(), event.getOwnerId(), event.getPrintingTime(),
                event.getPrintingStartDate());
        if (isNewPrintingAddPossible(newPrinting)) {
            this.printingList.add(newPrinting);
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

    public List<Printing> getPrintingList() {
        return printingList;
    }

    /**
     * Validates if new printing can be added. If printer will be free for his
     * reservation time.
     * 
     * @param newPrinting new Printing to add
     * @return true if printing can be added, otherwise false
     */
    private boolean isNewPrintingAddPossible(Printing newPrinting) {
        final Long newPrintingStartDate = newPrinting.getPrintingStartDate().getTime();
        final Long newPrintingEstimatedEndDate = newPrintingStartDate + newPrinting.getPrintingTime() + 2 * TECHNICAL_BREAK_TIME;
        for (Printing printing : printingList) {
            final Long printingStartDate = printing.getPrintingStartDate().getTime();
            final Long estimatedEndDate = printingStartDate + printing.getPrintingTime() + 2 * TECHNICAL_BREAK_TIME;
            if (newPrintingStartDate < estimatedEndDate && newPrintingEstimatedEndDate > printingStartDate) {
                return false;
            }
        }
        return true;
    }
	
}
