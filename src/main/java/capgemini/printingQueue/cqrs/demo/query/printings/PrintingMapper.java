package capgemini.printingQueue.cqrs.demo.query.printings;

import java.util.List;
import java.util.stream.Collectors;

public class PrintingMapper {

	public static Printing map(PrintingBO printing) {
		return new Printing(printing.getName(), printing.getPrintingStartDate(),
				printing.getEstimatingPrintingEndDate(), printing.getFinalPrintingEndDate(),
				printing.getTechnicalBreakTime(), printing.getUserId(), printing.getEmail(), printing.getUserName(),
				printing.getUserSurname(), printing.getTask());
	}
	
	public static List<Printing> map(List<PrintingBO> printings) {
		return printings.stream().map(PrintingMapper::map).collect(Collectors.toList());
	}
	
	public static PrintingBO map(Printing printing) {
		return new PrintingBO(printing.getName(), printing.getPrintingStartDate(),
				printing.getEstimatingPrintingEndDate(), printing.getFinalPrintingEndDate(),
				printing.getTechnicalBreakTime(), printing.getUserId(), printing.getEmail(), printing.getUserName(),
				printing.getUserSurname(), printing.getTask());
	}

}
