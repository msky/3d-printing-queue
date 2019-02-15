package capgemini.printingQueue.cqrs.demo.server.query.printings;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@RequestMapping("/query")
public class PrintingQueryController {

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
	@GetMapping("/printings")
    public List<Printing> findPrintings(
    		@RequestParam("printerId") String printerId,
    		@RequestParam("from") String from, 
    		@RequestParam("to") String to
    		) {
    	final LocalDate fromDate = LocalDateParser.parse(from);
    	final LocalDate toDate = LocalDateParser.parse(to);
    	final Long id = Long.valueOf(printerId);
        return repository.findAllPrintingReservations(id, fromDate, toDate);
    }

}
