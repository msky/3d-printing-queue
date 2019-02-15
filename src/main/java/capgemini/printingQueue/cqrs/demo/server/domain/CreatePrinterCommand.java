package capgemini.printingQueue.cqrs.demo.server.domain;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreatePrinterCommand {
	
	@TargetAggregateIdentifier
	private final String printerId;
	
	private final String name;

	public CreatePrinterCommand(String printerId, String printerName) {
		this.printerId = printerId;
		this.name = printerName;
	}

	public String getId() {
		return printerId;
	}

	public String getName() {
		return name;
	}
}
