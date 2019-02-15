package capgemini.printingQueue.cqrs.demo.server.domain;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PrinterCommandController {

	private final CommandGateway commandGateway;
	
	public PrinterCommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/printers")
	public void addNewPrinter(@RequestParam String printerName) throws InterruptedException, ExecutionException {
		final UUID queueId = UUID.randomUUID();
		System.out.println(commandGateway.send(new CreatePrinterCommand(queueId.toString(), printerName)).get());
	}
}
