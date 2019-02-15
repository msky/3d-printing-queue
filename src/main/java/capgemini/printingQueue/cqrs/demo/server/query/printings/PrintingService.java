package capgemini.printingQueue.cqrs.demo.server.query.printings;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class PrintingService {

	@Autowired
	private PrintingJpaRepository repository;
	/**
	 * Find all printings reservation in a range of dates for a printer.
	 * @param printerId Printer ID
	 * @param fromDate From Date
	 * @param toDate To Date
	 * @return All printings reservation in a range of dates for a printer
	 */
	@Transactional(readOnly = true)
	public List<Printing> findPrintings(Long printerId, LocalDate fromDate, LocalDate toDate) {
		return PrintingMapper.map(repository.findAllPrintingReservations(printerId, fromDate, toDate));
	}

	/**
	 * Add new printing.
	 * @param printing
	 */
	@Transactional
	public void addPrinting(Printing printing) {
		repository.save(PrintingMapper.map(printing));
	}

	/**
	 * Canel printing.
	 * @param userId User Id
	 * @param printingId Printing Id.
	 */
	@Transactional
	public void cancelPrinting(Long userId, Long printingId) {
		repository.findById(printingId).stream().peek(e -> {
			e.setUpdatedBy(userId);
			repository.delete(e);
		});
	}

	
	
}
