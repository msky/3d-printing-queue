package capgemini.printingQueue.cqrs.demo.server.query.printings;

import java.time.LocalDateTime;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
class Printing {
	private static final long serialVersionUID = 1L;
	private Long printerId;
	private String name;
	private LocalDateTime printingStartDate;
	private LocalDateTime estimatingPrintingEndDate;
	private LocalDateTime finalPrintingEndDate;
	private Integer technicalBreakTime;
	private Long userId;	
	private String email;	
	private String userName;	
	private String userSurname;	
	private TaskType task;	
}
