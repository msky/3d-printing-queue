package capgemini.printingQueue.cqrs.demo.server.query.printings;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Printing {
	
	private String id;
	private String printerId;
	private String name;
	private LocalDateTime printingStartDate;
	private LocalDateTime estimatingPrintingEndDate;
	private LocalDateTime finalPrintingEndDate;
	private Long technicalBreakTime;
	private String ownerId;
	private String ownerEmail;
	private TaskType task;	
}
