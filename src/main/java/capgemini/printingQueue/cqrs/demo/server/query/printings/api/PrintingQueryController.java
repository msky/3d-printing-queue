package capgemini.printingQueue.cqrs.demo.server.query.printings.api;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import capgemini.printingQueue.cqrs.demo.server.query.printings.FindPrintingsFromTimeRangeQuery;
import capgemini.printingQueue.cqrs.demo.server.query.printings.LocalDateParser;
import capgemini.printingQueue.cqrs.demo.server.query.printings.Printing;

//TODO delete controller and move printings as a resource to PrinterController
@RestController
@ResponseBody
@RequestMapping("/query")
public class PrintingQueryController {

	private final QueryGateway queryGateway;

	public PrintingQueryController(QueryGateway queryGateway) {
		this.queryGateway = queryGateway;
	}

	@GetMapping("/printings")
	public List<Printing> findPrintings(@RequestParam("printerId") String printerId, @RequestParam("from") String from,
			@RequestParam("to") String to) {
		FindPrintingsFromTimeRangeQuery query = 
				new FindPrintingsFromTimeRangeQuery(LocalDateParser.parse(from),
					LocalDateParser.parse(to),
					printerId);
		return queryGateway.query(query, ResponseTypes.multipleInstancesOf(Printing.class)).join();
	}

}
