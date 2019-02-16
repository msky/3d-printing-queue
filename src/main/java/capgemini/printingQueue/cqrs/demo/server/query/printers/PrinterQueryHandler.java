package capgemini.printingQueue.cqrs.demo.server.query.printers;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrinterQueryHandler {

	@Autowired
	PrinterJpaRepository repository;

	@QueryHandler
	public List<Printer> handle(FindAllPrintersQuery query) {
		return PrinterMapper.map(repository.findAll());
	}
}
