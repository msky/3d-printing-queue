package capgemini.printingQueue.cqrs.demo.query.printers;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
public class Printer {
	private Long id;
	private String name;
	private LocalDateTime statusFrom;
	private PrinterStatus status;
}
