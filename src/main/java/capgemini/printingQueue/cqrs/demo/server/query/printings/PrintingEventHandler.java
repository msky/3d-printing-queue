package capgemini.printingQueue.cqrs.demo.server.query.printings;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
		System.out.println("Propagation of NewPrintingAddedEvent");
		Integer technicalBreakTime = Integer.valueOf(30);
		LocalDateTime printingStartDate = Instant.ofEpochMilli(event.getPrintingStartDate().getTime())
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
		LocalDateTime estimatingPrintingEndDate = LocalDateTime.now().plusMinutes(event.getPrintingTime());
		LocalDateTime finalPrintingEndDate = estimatingPrintingEndDate.plusMinutes(technicalBreakTime);
		Long userId = Long.valueOf(1);
		Printing printing = new Printing(
				Long.valueOf(event.getPrinterId()), 
				"TODO: name", 
				printingStartDate, 
				estimatingPrintingEndDate,
				finalPrintingEndDate, 
				technicalBreakTime, 
				userId, 
				"TODO email", 
				"TODO name", 
				"TODO surname", 
				TaskType.PRINTING);
	
		
		//TODO should this event have an owner?
		printing.setCreatedBy(userId);
		repository.save(printing);
	}
	
	
	/**
	 * Canel printing.
	 * @param userId User Id
	 * @param printingId Printing Id.
	 
	@Transactional
	public void cancelPrinting(Long userId, Long printingId) {
		repository.findById(printingId).stream().peek(e -> {
			e.setUpdatedBy(userId);
			repository.delete(e);
		});
	}*/

	
	
}
