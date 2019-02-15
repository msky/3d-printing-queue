package capgemini.printingQueue.cqrs.demo.server.query.printings;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import capgemini.printingQueue.cqrs.demo.server.query.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Printing")
@Table(name = "Printing")  
@AttributeOverride(name = "id", column = @Column(name = "printingId",  nullable = false))
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PrintingBO extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Column(nullable = false) private String name;
	@Column(nullable = false) private LocalDateTime printingStartDate;
	@Column(nullable = false) private LocalDateTime estimatingPrintingEndDate;
	@Column(nullable = false) private LocalDateTime finalPrintingEndDate;
	@Column(nullable = false) private Integer technicalBreakTime;
	@Column(nullable = false) private Long userId;	
	@Column(nullable = false) private String email;	
	@Column(nullable = false) private String userName;	
	@Column(nullable = false) private String userSurname;	
	@Column(nullable = false) private TaskType task;	
}
