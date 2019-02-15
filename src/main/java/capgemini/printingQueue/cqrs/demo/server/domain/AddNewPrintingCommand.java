package capgemini.printingQueue.cqrs.demo.server.domain;

import java.util.Date;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class AddNewPrintingCommand {
    
    @TargetAggregateIdentifier
    private final String printerId;
    
    private final Long printingId;

    private final String ownerId;
    
    private final Long printingTime;
    
    private final Date printingStartDate;
    
    public AddNewPrintingCommand(String printerId, Long printingId, String ownerId, Long printingTime, Date printingStartDate) {
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
