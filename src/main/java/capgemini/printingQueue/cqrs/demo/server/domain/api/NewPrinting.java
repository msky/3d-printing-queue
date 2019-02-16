package capgemini.printingQueue.cqrs.demo.server.domain.api;

public class NewPrinting {

	private String guid;

	private String name;

	private Long duration;

	private String startDate;

	private String ownerId;

	public String getGUID() {
		return guid;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public Long getDuration() {
		return duration;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getName() {
		return name;
	}

}
