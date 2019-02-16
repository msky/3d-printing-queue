package capgemini.printingQueue.cqrs.demo.server.query.printings;

import java.time.LocalDateTime;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capgemini.printingQueue.cqrs.demo.server.domain.NewPrintingAddedEvent;

@Service
class PrintingEventHandler {

	@Autowired
	private PrintingJpaRepository repository;

	@EventHandler
	public void on(NewPrintingAddedEvent event) {
		Long technicalBreakTime = event.getTechnicalBreakTime();
		LocalDateTime printingStartDate = event.getPrintingStartDate();
		LocalDateTime estimatedPrintingEndDate = LocalDateTime.now().plusMinutes(event.getPrintingTime());
		LocalDateTime finalPrintingEndDate = estimatedPrintingEndDate.plusMinutes(technicalBreakTime);
		String userId = event.getOwnerId();
		
		Printing printing = new Printing(
				event.getPrintingId(),
				event.getPrinterId(), 
				"TODO: name", 
				printingStartDate, 
				estimatedPrintingEndDate,
				finalPrintingEndDate, 
				technicalBreakTime, 
				userId, 
				"TODO email", 
				TaskType.PRINTING);
	
		repository.save(PrintingMapper.map(printing));
	}
	
}
