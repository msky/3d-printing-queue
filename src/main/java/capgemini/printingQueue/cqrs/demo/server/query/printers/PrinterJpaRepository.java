package capgemini.printingQueue.cqrs.demo.server.query.printers;

import org.springframework.stereotype.Repository;

import capgemini.printingQueue.cqrs.demo.server.query.model.BaseJpaRepository;

@Repository
interface PrinterJpaRepository extends BaseJpaRepository<PrinterBO> {
	
}
