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
		System.out.println("Propagation of PrinterCreatedEvent");
		Printer printer = new Printer(
				event.getName(),
				LocalDateTime.now(),
				PrinterStatus.ACTIVE);
		
		//TODO should this event have an owner?
		printer.setCreatedBy(1L);
		repository.save(printer);
	}

}
