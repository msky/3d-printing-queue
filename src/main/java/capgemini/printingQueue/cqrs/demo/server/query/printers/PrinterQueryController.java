package capgemini.printingQueue.cqrs.demo.server.query.printers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@RequestMapping("/query")
public class PrinterQueryController {
	
    @Autowired
    private PrinterService printerService;

    @GetMapping("/printers")
    public List<Printer> findAllPrinters() {
        return printerService.findAllPrinters();
    }
    
}