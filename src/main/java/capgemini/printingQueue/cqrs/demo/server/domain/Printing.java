package capgemini.printingQueue.cqrs.demo.server.domain;

import java.time.LocalDateTime;

public class Printing {
    
    private final String printingId;
    
    private final String printingName;

    private final String ownerId;
    
    /**
     * Printing Time in minutes.
     */
    private final Long durationMinutes;
    
    private final LocalDateTime printingStartDate;
    
    //TODO technical break value
    public Printing(String id,
    		String printingName,
    		String ownerId,
    		Long printingTime,
    		LocalDateTime printingStartDate) {
        this.printingId = id;
        this.printingName = printingName;
        this.ownerId = ownerId;
        this.durationMinutes = printingTime;
        this.printingStartDate = printingStartDate;
    }

    public String getPrintingId() {
        return printingId;
    }
    
    public String getPrinterName() {
        return printingName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public Long getDurationMinutes() {
        return durationMinutes;
    }

    public LocalDateTime getPrintingStartDate() {
        return printingStartDate;
    }
    
}
