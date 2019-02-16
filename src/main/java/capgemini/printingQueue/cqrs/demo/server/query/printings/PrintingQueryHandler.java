package capgemini.printingQueue.cqrs.demo.server.query.printings;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrintingQueryHandler {

	@Autowired
	private PrintingJpaRepository repository;

	@Transactional(readOnly = true)
	public List<Printing> handle(FindPrintingsFromTimeRangeQuery query) {
		return PrintingMapper.map(
				repository.findAllPrintingReservations(query.getPrinterId(), query.getFromDate(), query.getToDate()));
	}

}
