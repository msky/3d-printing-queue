package capgemini.printingQueue.cqrs.demo.server.domain;

import java.util.Date;

public class Printing {
    
    private final Long printingId;
    
    private final String printingName;

    private final String ownerId;
    
    /**
     * Printing Time in miliseconds.
     */
    private final Long printingTime;
    
    private final Date printingStartDate;
    
    public Printing(Long id, String printingName, String ownerId, Long printingTime, Date printingStartDate) {
        this.printingId = id;
        this.printingName = printingName;
        this.ownerId = ownerId;
        this.printingTime = printingTime;
        this.printingStartDate = printingStartDate;
    }

    public Long getPrintingId() {
        return printingId;
    }
    
    public String getPrinterName() {
        return printingName;
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
