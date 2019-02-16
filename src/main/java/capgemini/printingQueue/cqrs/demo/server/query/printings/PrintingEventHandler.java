package capgemini.printingQueue.cqrs.demo.server.query.printings;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

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
		Integer technicalBreakTime = Integer.valueOf(30);
		LocalDateTime printingStartDate = Instant.ofEpochMilli(event.getPrintingStartDate().getTime())
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
		LocalDateTime estimatedPrintingEndDate = LocalDateTime.now().plusMinutes(event.getPrintingTime());
		LocalDateTime finalPrintingEndDate = estimatedPrintingEndDate.plusMinutes(technicalBreakTime);
		
		//TODO
		Long userId = Long.valueOf(1);
		
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
				"TODO name", 
				"TODO surname", 
				TaskType.PRINTING);
	
		repository.save(PrintingMapper.map(printing));
	}
	
}
