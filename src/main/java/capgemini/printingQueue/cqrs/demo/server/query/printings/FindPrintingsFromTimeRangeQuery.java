package capgemini.printingQueue.cqrs.demo.server.query.printings;

import java.time.LocalDate;

public class FindPrintingsFromTimeRangeQuery {

	private final LocalDate fromDate;
	private final LocalDate toDate;
	private final String printerId;

	public FindPrintingsFromTimeRangeQuery(LocalDate fromDate, LocalDate toDate, String printerId) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.printerId = printerId;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public String getPrinterId() {
		return printerId;
	}
}
