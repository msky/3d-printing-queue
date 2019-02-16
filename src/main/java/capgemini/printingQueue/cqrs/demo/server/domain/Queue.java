package capgemini.printingQueue.cqrs.demo.server.domain;

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
                event.getPrintingTime(), event.getPrintingStartDate());
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
	 * @param requiredTechnicalBreakeTime required time of technical breake, that
	 *                                    should be added before and after printing
	 *                                    to prepare/clean the printer
	 * @return true if printing can be added, otherwise false
	 */
    protected boolean isNewPrintingAddPossible(Printing newPrinting, long requiredTechnicalBreakeTime) {
        final Long newPrintingStartDate = newPrinting.getPrintingStartDate().getTime();
        final Long newPrintingEstimatedEndDate = newPrintingStartDate + newPrinting.getPrintingTime() + 2 * requiredTechnicalBreakeTime;
        for (Printing printing : printingsList) {
            final Long printingStartDate = printing.getPrintingStartDate().getTime();
            final Long estimatedEndDate = printingStartDate + printing.getPrintingTime() + 2 * requiredTechnicalBreakeTime;
            if (newPrintingStartDate < estimatedEndDate && newPrintingEstimatedEndDate > printingStartDate) {
                return false;
            }
        }
        return true;
    }
}
