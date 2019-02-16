package capgemini.printingQueue.cqrs.demo.server.query.printers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrinterMapper {

	public static Printer map(PrinterBO printerBO) {
		return new Printer(printerBO.getId(), printerBO.getName(), printerBO.getStatusFrom(),
				printerBO.getStatus());
	}
	
	public static List<Printer> map(List<PrinterBO> printerBOs) {
		return Optional.ofNullable(printerBOs)
				.map(Collection::stream).orElseGet(Stream::empty)
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
