package capgemini.printingQueue.cqrs.demo.domain;

import java.util.Date;

public class NewPrintingAddedEvent {
    
    private final String queueId;
    
    private final Long printingId;

    private final String ownerId;
    
    private final Long printingTime;
    
    private final Date printingStartDate;
    
    public NewPrintingAddedEvent(String queueId, Long printingId, String ownerId, Long printingTime, Date printingStartDate) {
        this.queueId = queueId;
        this.printingId = printingId;
        this.ownerId = ownerId;
        this.printingTime = printingTime;
        this.printingStartDate = printingStartDate;
    }

    public String getQueueId() {
        return queueId;
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
