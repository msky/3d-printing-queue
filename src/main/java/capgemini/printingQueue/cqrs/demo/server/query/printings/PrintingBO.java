package capgemini.printingQueue.cqrs.demo.server.query.printings;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Printing")
@Table(name = "PRINTINGS")  
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
class PrintingBO {
	
	@Id private String id;
	@Column(nullable = false) private String printerId;
	@Column(nullable = false) private String name;
	@Column(nullable = false) private LocalDateTime printingStartDate;
	@Column(nullable = false) private LocalDateTime estimatingPrintingEndDate;
	@Column private LocalDateTime finalPrintingEndDate;
	@Column(nullable = false) private Long technicalBreakTime;
	@Column(nullable = false) private String ownerId;	
	@Column(nullable = false) private String ownerEmail;	
	@Column(nullable = false) private TaskType task;	
}
