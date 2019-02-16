package capgemini.printingQueue.cqrs.demo.server.domain;

import java.util.Date;

public class NewPrintingAddedEvent {

	private final String printerId;

	private final String printingName;

	private final Long printingId;

	private final String ownerId;

	private final Long printingTime;

	private final Date printingStartDate;

	private final Long technicalBreakTime;

	public NewPrintingAddedEvent(String printerId, String printingName, Long printingId, String ownerId,
			Long printingTime, Date printingStartDate, Long technicalBreakTime) {
		this.printerId = printerId;
		this.printingName = printingName;
		this.printingId = printingId;
		this.ownerId = ownerId;
		this.printingTime = printingTime;
		this.printingStartDate = printingStartDate;
		this.technicalBreakTime = technicalBreakTime;
	}

	public String getPrinterId() {
		return printerId;
	}

	public String getPrintingName() {
		return printingName;
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

	public Long getTechnicalBreakTime() {
		return technicalBreakTime;
	}

}
