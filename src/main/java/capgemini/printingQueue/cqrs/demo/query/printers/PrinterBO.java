package capgemini.printingQueue.cqrs.demo.query.printers;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import capgemini.printingQueue.cqrs.demo.query.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Printer")
@Table(name = "Printer")  
@AttributeOverride(name = "id", column = @Column(name = "printerId",  nullable = false))
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PrinterBO extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Size(max = 100)  
    @Column(name = "name", length = 100)  
	private String name;
	
    @Column(name = "statusFrom")  
	private LocalDateTime statusFrom;
	
    @Column(name = "status")  
	private PrinterStatus status;
}
