package capgemini.printingQueue.cqrs.demo.server.domain;

import java.time.LocalDateTime;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class AddNewPrintingCommand {
    
    @TargetAggregateIdentifier
    private final String printerId;
    
    private final String printingId;
    
    private final String printingName;

    private final String ownerId;
    
    private final Long printingTime;
    
    private final LocalDateTime printingStartDate;
    
    public AddNewPrintingCommand(String printerId,
    		String printingName,
    		String printingId,
    		String ownerId,
    		Long printingTime,
    		LocalDateTime printingStartDate) {
        this.printerId = printerId;
        this.printingName = printingName;
        this.printingId = printingId;
        this.ownerId = ownerId;
        this.printingTime = printingTime;
        this.printingStartDate = printingStartDate;
    }
    
    public String getPrinterId() {
        return printerId;
    }
    
    public String getPrintingName() {
        return printingName;
    }

    public String getPrintingId() {
        return printingId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public Long getPrintingTime() {
        return printingTime;
    }

    public LocalDateTime getPrintingStartDate() {
        return printingStartDate;
    }
}
