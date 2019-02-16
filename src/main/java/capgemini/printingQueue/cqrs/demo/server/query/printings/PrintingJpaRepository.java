package capgemini.printingQueue.cqrs.demo.server.query.printings;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface PrintingJpaRepository extends JpaRepository<PrintingBO, String> {
	
    @Query("from Printing p where "
    		+ "p.printerId = :printerId and "
    		+ "(p.printingStartDate >= :from or p.estimatingPrintingEndDate >= :from) and "
    		+ "(p.estimatingPrintingEndDate <= :to or p.printingStartDate <= :to)"
    		)
	List<PrintingBO> findAllPrintingReservations(String printerId, LocalDateTime from, LocalDateTime to);
    
 
}