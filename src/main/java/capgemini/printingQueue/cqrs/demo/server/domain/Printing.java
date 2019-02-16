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
    
    private final Long technicalBrekDuration;
    
    public Printing(String id,
    		String printingName,
    		String ownerId,
    		Long printingTime,
    		LocalDateTime printingStartDate,
    		Long technicalBreakDuration) {
        this.printingId = id;
        this.printingName = printingName;
        this.ownerId = ownerId;
        this.durationMinutes = printingTime;
        this.printingStartDate = printingStartDate;
        this.technicalBrekDuration = technicalBreakDuration;
    }

    public String getPrintingId() {
        return printingId;
    }
    
    public String getPrintingName() {
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

    public Long getTechnicalBrekDuration() {
        return technicalBrekDuration;
    }
}
