package capgemini.printingQueue.cqrs.demo.server.query.printers;

import org.springframework.data.jpa.repository.JpaRepository;

interface PrinterJpaRepository extends JpaRepository<PrinterBO, String> {
	
}
