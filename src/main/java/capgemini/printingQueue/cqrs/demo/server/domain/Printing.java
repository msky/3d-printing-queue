package capgemini.printingQueue.cqrs.demo.server.domain;

import java.util.Date;

public class Printing {
    
    private final Long id;

    private final String ownerId;
    
    /**
     * Printing Time in miliseconds.
     */
    private final Long printingTime;
    
    private final Date printingStartDate;
    
    public Printing(Long id, String ownerId, Long printingTime, Date printingStartDate) {
        this.id = id;
        this.ownerId = ownerId;
        this.printingTime = printingTime;
        this.printingStartDate = printingStartDate;
    }

    public Long getId() {
        return id;
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
