package capgemini.printingQueue.cqrs.demo.server.domain;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.EntityId;

public class Queue {
        
    @EntityId(routingKey="printerId")
    private String id;

    private List<Printing> printingsList;
    
    public Queue(String printerId) {
        this.id = printerId;
        this.printingsList = new ArrayList<Printing>();
    }
    
    @EventSourcingHandler
    public void on(NewPrintingAddedEvent event) throws Exception {
        final Printing newPrinting = new Printing(event.getPrintingId(), event.getPrintingName(), event.getOwnerId(),
                event.getPrintingTime(), event.getPrintingStartDate(), event.getTechnicalBreakDuration());
        this.printingsList.add(newPrinting);
    }
    public List<Printing> getPrintingsList() {
        return printingsList;
    }
    
    /**
	 * Validates if new printing can be added. If printer will be free for his
	 * reservation time.
	 * 
	 * @param newPrinting                 new Printing to add
	 * @param requiredTechnicalBreakeTimeMinutes required time of technical break, that
	 *                                    should be added before and after printing
	 *                                    to prepare/clean the printer
	 * @return true if printing can be added, otherwise false
	 */
    protected boolean isNewPrintingAddPossible(Printing newPrinting, long requiredTechnicalBreakeTimeMinutes) {
    	final Long requiredTechnicalBreakMilis = requiredTechnicalBreakeTimeMinutes * 60000;
    	final Instant newPrintingStartDate = newPrinting.getPrintingStartDate().atZone(ZoneId.systemDefault()).toInstant();
        final Instant newPrintingEstimatedEndDate = newPrintingStartDate
                .plusMillis(newPrinting.getDurationMinutes() * 60000).plusMillis(2 * requiredTechnicalBreakMilis);
        for (Printing printing : printingsList) {
            final Instant printingStartDate = printing.getPrintingStartDate().atZone(ZoneId.systemDefault()).toInstant();
            final Instant estimatedEndDate = printingStartDate.plusMillis(printing.getDurationMinutes() * 60000)
                    .plusMillis(2 * requiredTechnicalBreakMilis);
            if (newPrintingStartDate.isBefore(estimatedEndDate) && newPrintingEstimatedEndDate.isAfter(printingStartDate)) {
                return false;
            }
        }
        return true;
    }
}
