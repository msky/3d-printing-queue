package capgemini.printingQueue.cqrs.demo.query.printings;

import org.springframework.data.jpa.repository.JpaRepository;

interface PrintingRepository extends JpaRepository<PrintingBO, Long> {
	
}