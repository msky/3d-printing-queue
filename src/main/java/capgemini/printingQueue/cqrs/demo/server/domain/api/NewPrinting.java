package capgemini.printingQueue.cqrs.demo.server.domain.api;

import lombok.Data;

@Data
public class NewPrinting {

	private String guid;

	private String name;

	private Long duration;

	private String startDate;

	private String ownerId;
	
	private Long technicalBreakDuration;

}
