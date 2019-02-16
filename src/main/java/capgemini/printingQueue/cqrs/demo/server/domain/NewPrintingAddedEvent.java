package capgemini.printingQueue.cqrs.demo.server.domain;

import java.time.LocalDateTime;

public class NewPrintingAddedEvent {

	private final String printerId;

	private final String printingName;

	private final String printingId;

	private final String ownerId;

	private final Long minutesRequiredForPrinting;

	private final LocalDateTime printingStartDate;

	private final Long technicalBreakDuration;

	public NewPrintingAddedEvent(String printerId, String printingName, String printingId, String ownerId,
			Long printingTime, LocalDateTime printingStartDate, Long technicalBreakDuration) {
		this.printerId = printerId;
		this.printingName = printingName;
		this.printingId = printingId;
		this.ownerId = ownerId;
		this.minutesRequiredForPrinting = printingTime;
		this.printingStartDate = printingStartDate;
		this.technicalBreakDuration = technicalBreakDuration;
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
		return minutesRequiredForPrinting;
	}

	public LocalDateTime getPrintingStartDate() {
		return printingStartDate;
	}

	public Long getTechnicalBreakDuration() {
		return technicalBreakDuration;
	}

}
