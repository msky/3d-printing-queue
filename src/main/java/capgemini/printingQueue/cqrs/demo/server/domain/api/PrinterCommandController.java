package capgemini.printingQueue.cqrs.demo.server.domain.api;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import capgemini.printingQueue.cqrs.demo.server.domain.CreatePrinterCommand;


@RestController
public class PrinterCommandController {

	private final CommandGateway commandGateway;
	
	public PrinterCommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}
	
	/* @PreAuthorize("hasRole('ADMIN')") */
	@PostMapping("/printers")
	public String addNewPrinter(@RequestParam String printerName) throws InterruptedException, ExecutionException {
		final UUID pritnerId = UUID.randomUUID();
		commandGateway.send(new CreatePrinterCommand(pritnerId.toString(), printerName));
		return pritnerId.toString();
	}
	
}
