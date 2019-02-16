package capgemini.printingQueue.cqrs.demo.server.domain.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import capgemini.printingQueue.cqrs.demo.server.domain.AddNewPrintingCommand;
import capgemini.printingQueue.cqrs.demo.server.domain.CreatePrinterCommand;

@RestController
public class PrinterCommandController {

	private final CommandGateway commandGateway;

	public PrinterCommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	/* @PreAuthorize("hasRole('ADMIN')") */
	@PostMapping("/printers")
	public void addNewPrinter(@RequestBody NewPrinter newPrinter
			) throws InterruptedException, ExecutionException {
		commandGateway.send(new CreatePrinterCommand(newPrinter.getGuid(), newPrinter.getName()));
	}

	@PostMapping("/printers/{printerId}/printings")
	public void submitNewPrinting(@PathVariable String printerId,
			@RequestBody NewPrinting printing) {
		commandGateway.send(new AddNewPrintingCommand(printerId,
				printing.getName(),
				printing.getGuid(),
				printing.getOwnerId(),
				printing.getDuration(),
				LocalDateTime.parse(printing.getStartDate(), DateTimeFormatter.ISO_DATE_TIME)));
	}

}
