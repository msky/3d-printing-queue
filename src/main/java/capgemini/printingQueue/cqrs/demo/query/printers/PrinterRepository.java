package capgemini.printingQueue.cqrs.demo.query.printers;

import org.springframework.data.jpa.repository.JpaRepository;


interface PrinterRepository extends JpaRepository<PrinterBO, Long> {
	
}
