package capgemini.printingQueue.cqrs.demo.query.printers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrinterService {

	@Autowired
	private PrinterRepository repository;
	
	@Transactional(readOnly = true)
	public List<Printer> findAllPrinters() {
		return PrinterMapper.map(repository.findAll());
	}
		
	@Transactional
	public Printer createPrinter() {
		PrinterBO printerBO = new PrinterBO();
		printerBO.setCreatedBy(Long.valueOf(1));
		printerBO.setUpdatedBy(Long.valueOf(1));
		printerBO.setName("Drukarka1");
		printerBO.setStatus(PrinterStatus.ACTIVE);
		printerBO.setStatusFrom(LocalDateTime.now());
		printerBO = repository.save(printerBO);
		return PrinterMapper.map(printerBO);
	}
}
