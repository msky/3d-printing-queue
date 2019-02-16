package capgemini.printingQueue.cqrs.demo.server.query.printers;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Printer")
@Table(name = "PRINTERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrinterBO {

	@Id
	private String id;

	@Size(max = 100)
	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "statusFrom")
	private LocalDateTime statusFrom;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private PrinterStatus status;
}
