package capgemini.printingQueue.cqrs.demo.server.query.printers;

import java.util.List;
import java.util.stream.Collectors;

public class PrinterMapper {

	public static Printer map(PrinterBO printerBO) {
		return new Printer(printerBO.getId(), printerBO.getName(), printerBO.getStatusFrom(),
				printerBO.getStatus());
	}
	
	public static List<Printer> map(List<PrinterBO> printerBOs) {
		return printerBOs.stream()
				.map(PrinterMapper::map)
				.collect(Collectors.toList());
	}

	public static PrinterBO map(Printer printer) {
		return new PrinterBO(printer.getId(),
				printer.getName(),
				printer.getStatusFrom(),
				printer.getStatus());
	}
}