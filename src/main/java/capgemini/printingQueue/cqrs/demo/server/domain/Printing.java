package capgemini.printingQueue.cqrs.demo.server.domain;

import java.util.Date;

public class Printing {
    
    private final Long printingId;

    private final String ownerId;
    
    /**
     * Printing Time in miliseconds.
     */
    private final Long printingTime;
    
    private final Date printingStartDate;
    
    public Printing(Long id, String ownerId, Long printingTime, Date printingStartDate) {
        this.printingId = id;
        this.ownerId = ownerId;
        this.printingTime = printingTime;
        this.printingStartDate = printingStartDate;
    }

    public Long getId() {
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
