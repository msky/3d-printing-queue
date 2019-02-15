package capgemini.printingQueue.cqrs.demo.domain;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrinterController {

	@PostMapping("/printers")
	public void addNewPrinter(@RequestParam String printerName) {
		System.out.println("new print added: " + printerName);
	}
}
