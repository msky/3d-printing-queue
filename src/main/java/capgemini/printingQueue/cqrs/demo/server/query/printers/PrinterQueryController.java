package capgemini.printingQueue.cqrs.demo.server.query.printers;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@RequestMapping("/query")
public class PrinterQueryController {

	private final QueryGateway queryGateway;

	public PrinterQueryController(QueryGateway queryGateway) {
		this.queryGateway = queryGateway;
	}

	@GetMapping("/printers")
	public List<Printer> findAllPrinters() {
		return queryGateway.query(new FindAllPrintersQuery(),
				ResponseTypes.multipleInstancesOf(Printer.class)).join();
	}

}
