package capgemini.printingQueue.cqrs.demo.server.domain

import static org.junit.Assert.*

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import org.axonframework.test.aggregate.AggregateTestFixture
import org.axonframework.test.aggregate.FixtureConfiguration
import org.junit.Test

import spock.lang.Specification

class AddNewPrintingTest extends Specification {

    private FixtureConfiguration<Printer> fixture
    
    def setup() {
        fixture = new AggregateTestFixture<Printer>(Printer.class)
		fixture.setReportIllegalStateChange(false)
    }
    
    def "should call add new printing event"() {
        given:
     
        def printerId = UUID.randomUUID().toString()
        def printerName = "printer1"
        def printingName = "printing"
        def printingId = "printingUUID"
        def ownerId = "123"
        def printingTime = Long.valueOf(360) //6h
        def printingStartDate = LocalDateTime.now()
        
        when:
        def action = fixture.givenCommands(new CreatePrinterCommand(printerId, printerName))
                    .when(new AddNewPrintingCommand(printerId,
						 printingName,
						 printingId,
						 ownerId,
						 printingTime,
						 printingStartDate))
        
        then:
		action.expectEvents(new NewPrintingAddedEvent(printerId, 
			printingName,
			printingId,
			ownerId,
			printingTime,
			printingStartDate,
			Printer.TECHNICAL_BREAK_TIME))
    }
    
    def "should add new printing to list"() {
        given:
        def printer = new Printer()
        def printerId = UUID.randomUUID().toString()
        def printingId = UUID.randomUUID().toString()
        def printerName = "printer1"
        def printingName = "printing"
        def ownerId = "123"
        def printingTime = Long.valueOf(360)  //6h
        def printingStartDate = createDate("2019-02-15 08:00:00")
        def newPrintingStartDate = createDate("2019-02-15 15:00:00")
		
        when:
        def action = fixture.givenCommands(new CreatePrinterCommand(printerId, printerName)).when(new AddNewPrintingCommand(printerId, printingName, printingId, ownerId, printingTime, printingStartDate))
        
        then:
        action.expectEvents(new NewPrintingAddedEvent(printerId,
			printingName,
			printingId,
			ownerId,
			printingTime,
			printingStartDate,
			Printer.TECHNICAL_BREAK_TIME))
    }
	
    def "should not add new printing to list when technical break"() {
        given:
        def printer = new Printer()
        def printerId = UUID.randomUUID().toString()
        def printerName = "printer1"
        def printingName = "printing"
        def printingId = UUID.randomUUID().toString()
        def ownerId = "123"
        def printingTime = Long.valueOf(360)  //6h
        def printingStartDate = createDate("2019-02-15 08:00:00")
        def newPrintingStartDate = createDate("2019-02-15 14:05:00")
        
        when:
        def action = fixture.givenCommands(new CreatePrinterCommand(printerId, printerName))
                    .andGiven(new AddNewPrintingCommand(printerId, printingName, printingId, ownerId, printingTime, printingStartDate))
                    .when(new AddNewPrintingCommand(printerId, printingName, printingId, ownerId, printingTime, newPrintingStartDate))
        
        then:
        action.expectExceptionMessage("Printer busy on this time")
    }
    
    def "should not add new printing to list when printer busy on time"() {
        given:
        def printer = new Printer()
        def printerId = UUID.randomUUID().toString()
        def printingId = UUID.randomUUID().toString()
        def printerName = "printer1"
        def printingName = "printing"
        def ownerId = "123"
        def printingTime = Long.valueOf(360)  //6h
        def printingStartDate = createDate("2019-02-15 08:00:00")
        def newPrintingStartDate = createDate("2019-02-15 10:00:00")
        
        when:
        def action = fixture.givenCommands(new CreatePrinterCommand(printerId, printerName))
                    .andGiven(new AddNewPrintingCommand(printerId, printingName, printingId, ownerId, printingTime, printingStartDate))
                    .when(new AddNewPrintingCommand(printerId, printingName, printingId, ownerId, printingTime, newPrintingStartDate))
        
        then:
        action.expectExceptionMessage("Printer busy on this time")
    }
    
    private LocalDateTime createDate(String date) {
        def createdDate = LocalDateTime.parse(date,
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return createdDate
    }
    
}
