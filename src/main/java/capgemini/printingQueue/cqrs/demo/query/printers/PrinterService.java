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

	/**
	 * Read all printers from database.
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Printer> findAllPrinters() {
		return PrinterMapper.map(repository.findAll());
	}

	/**
	 * Change status of Printer.
	 * 
	 * @param userId Modificator Id
	 * @param printerId  Printer Id
	 * @param status Status to change.
	 * @throws PrinterModificationExeption
	 */
	@Transactional
	public Printer changeStatus(Long userId, Long printerId, PrinterStatus status) throws PrinterModificationExeption {
		return repository.findById(printerId).map(p -> {
			p.setStatus(status);
			p.setStatusFrom(LocalDateTime.now());
			p.setUpdatedBy(userId);
			return p;
		}).map(PrinterMapper::map).orElseThrow(() -> new PrinterModificationExeption("Status can not be changed."));
	}

	/**
	 * Create new printer.
	 * 
	 * @param userId  User Id - Creator of printer
	 * @param printer Printer
	 * @return Persisted printer
	 */
	@Transactional
	public Printer createPrinter(Long userId, Printer printer) {
		PrinterBO printerBO = new PrinterBO();
		printerBO.setCreatedBy(userId);
		printerBO.setName(printer.getName());
		printerBO.setStatus(PrinterStatus.DEACTIVATE);
		printerBO.setStatusFrom(LocalDateTime.now());
		printerBO = repository.save(printerBO);
		return PrinterMapper.map(printerBO);
	}
}
