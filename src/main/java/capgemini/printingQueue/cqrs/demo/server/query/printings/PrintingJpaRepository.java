package capgemini.printingQueue.cqrs.demo.server.query.printings;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import capgemini.printingQueue.cqrs.demo.server.query.model.BaseJpaRepository;

interface PrintingJpaRepository extends BaseJpaRepository<Printing> {
	
    @Query("from Printing p where "
    		+ "p.printerId = :printerId and "
    		+ "(p.printingStartDate >= :from or p.estimatingPrintingEndDate >= :from) and "
    		+ "(p.estimatingPrintingEndDate <= :to or p.printingStartDate <= :to) and "
    		+ "p.deleted = 0"
    		)
	List<Printing> findAllPrintingReservations(Long printerId, LocalDate from, LocalDate to);
    
 
}