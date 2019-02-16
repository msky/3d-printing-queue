package capgemini.printingQueue.cqrs.demo.server.query.printings;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateParser {
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static LocalDate parse(String date) {
		return LocalDate.parse(date, formatter);
	}
}
