package capgemini.printingQueue.cqrs.demo.server.query.printings;

import java.util.List;
import java.util.stream.Collectors;

public class PrintingMapper {

	public static Printing map(PrintingBO printing) {
		return new Printing(
				printing.getId(),
				printing.getPrinterId(),
				printing.getName(),
				printing.getPrintingStartDate(),
				printing.getEstimatingPrintingEndDate(),
				printing.getFinalPrintingEndDate(),
				printing.getTechnicalBreakDuration(),
				printing.getOwnerId(),
				printing.getOwnerEmail(),
				printing.getTask());
	}
	
	public static List<Printing> map(List<PrintingBO> printings) {
		return printings.stream().map(PrintingMapper::map).collect(Collectors.toList());
	}
	
	public static PrintingBO map(Printing printing) {
		return new PrintingBO(
				printing.getId(),
				printing.getPrinterId(),
				printing.getName(),
				printing.getPrintingStartDate(),
				printing.getEstimatingPrintingEndDate(),
				printing.getFinalPrintingEndDate(),
				printing.getTechnicalBreakDuration(),
				printing.getOwnerId(),
				printing.getOwnerEmail(),
				printing.getTask());
	}

}
