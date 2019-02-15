package capgemini.printingQueue.cqrs.demo.server.domain;

import java.util.Date;

public class NewPrintingAddedEvent {
    
    private final String printerId;
    
    private final Long printingId;

    private final String ownerId;
    
    private final Long printingTime;
    
    private final Date printingStartDate;
    
    public NewPrintingAddedEvent(String printerId, Long printingId, String ownerId, Long printingTime, Date printingStartDate) {
        this.printerId = printerId;
        this.printingId = printingId;
        this.ownerId = ownerId;
        this.printingTime = printingTime;
        this.printingStartDate = printingStartDate;
    }

    public String getPrinterId() {
        return printerId;
    }

    public Long getPrintingId() {
        return printingId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public Long getPrintingTime() {
        return printingTime;
    }

    public Date getPrintingStartDate() {
        return printingStartDate;
    }
    
}
