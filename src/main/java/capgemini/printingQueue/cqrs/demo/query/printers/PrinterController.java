package capgemini.printingQueue.cqrs.demo.query.printers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrinterController {
	
    @Autowired
    private PrinterService printerService;

    @GetMapping("/printers")
    public String findAllPrinters(Map<String, Object> params) {
        final List<Printer> allBooks = printerService.findAllPrinters();
        params.put("printers", allBooks);
        return "printersList";
    }

    @GetMapping("/addPrinter")
    public String addPrinter() {
    	final Printer printer = printerService.createPrinter();
    	return "printersList";
    }
    
}
