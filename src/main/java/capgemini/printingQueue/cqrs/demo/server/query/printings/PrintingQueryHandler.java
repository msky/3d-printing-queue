package capgemini.printingQueue.cqrs.demo.server.query.printings;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrintingQueryHandler {

	@Autowired
	private PrintingJpaRepository repository;

	@QueryHandler
	public List<Printing> handle(FindPrintingsFromTimeRangeQuery query) {
		return PrintingMapper.map(
				repository.findAllPrintingReservations(query.getPrinterId(), query.getFromDate().atStartOfDay(), query.getToDate().atStartOfDay()));
	}

}
