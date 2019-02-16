package capgemini.printingQueue.cqrs.demo.server.query.printers;

import java.time.LocalDateTime;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capgemini.printingQueue.cqrs.demo.server.domain.PrinterCreatedEvent;

@Service
public class PrinterEventHandler {

	@Autowired
	private PrinterJpaRepository repository;

	@EventHandler
	public void on(PrinterCreatedEvent event) {
		Printer printer = new Printer(event.getId(),
				event.getName(),
				LocalDateTime.now(),
				PrinterStatus.ACTIVE);

		repository.save(PrinterMapper.map(printer));
	}

}
