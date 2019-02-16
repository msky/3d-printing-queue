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
        def technicalBreakDuration = 15L
        
        when:
        def action = fixture.givenCommands(new CreatePrinterCommand(printerId, printerName))
                    .when(new AddNewPrintingCommand(printerId,
						 printingName,
						 printingId,
						 ownerId,
						 printingTime,
						 printingStartDate,
                         technicalBreakDuration))
        
        then:
		action.expectEvents(new NewPrintingAddedEvent(printerId, 
			printingName,
			printingId,
			ownerId,
			printingTime,
			printingStartDate,
			technicalBreakDuration))
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
        def newPrintingStartDate = createDate("2019-02-15 14:31:00")
        def technicalBreakDuration = 15L
		
        when:
        def action = fixture.givenCommands(new CreatePrinterCommand(printerId, printerName))
                        .andGivenCommands(new AddNewPrintingCommand(printerId, 
                            printingName, 
                            printingId, 
                            ownerId, 
                            printingTime, 
                            printingStartDate,
                            technicalBreakDuration))
                        .when(new AddNewPrintingCommand(printerId, 
                            printingName, 
                            printingId, 
                            ownerId, 
                            printingTime, 
                            newPrintingStartDate,
                            technicalBreakDuration))
        
        then:
        action.expectEvents(new NewPrintingAddedEvent(printerId,
			printingName,
			printingId,
			ownerId,
			printingTime,
			newPrintingStartDate,
			technicalBreakDuration))
    }
	
    def "should not add new printing to list when technical break"() {
        given:
        def printer = new Printer()
        def printerId = UUID.randomUUID().toString()
        def printerName = "printer1"
        def printingName = "printing"
        def secondPrintingName = "second printing"
        def printingId = UUID.randomUUID().toString()
        def ownerId = "123"
        def printingTime = Long.valueOf(360)  //6h
        def printingStartDate = createDate("2019-02-15 08:00:00")
        def newPrintingStartDate = createDate("2019-02-15 14:25:00")
        def technicalBreakDuration = 15L
        
        when:
        def action = fixture.givenCommands(new CreatePrinterCommand(printerId, printerName))
                    .andGivenCommands(new AddNewPrintingCommand(printerId, printingName, printingId, ownerId, printingTime, printingStartDate, technicalBreakDuration))
                    .when(new AddNewPrintingCommand(printerId, secondPrintingName, printingId, ownerId, printingTime, newPrintingStartDate, technicalBreakDuration))
        
        then:
        action.expectException(Exception.class)
                .expectExceptionMessage("Printer busy on this time")
    }
    
    def "should not add new printing to list when printer busy on time"() {
        given:
        def printer = new Printer()
        def printerId = UUID.randomUUID().toString()
        def printingId = UUID.randomUUID().toString()
        def printerName = "printer1"
        def printingName = "printing"
        def secondPrintingName = "second printing"
        def ownerId = "123"
        def printingTime = Long.valueOf(360)  //6h
        def printingStartDate = createDate("2019-02-15 08:00:00")
        def newPrintingStartDate = createDate("2019-02-15 10:00:00")
        def technicalBreakDuration = 15L
        
        when:
        def action = fixture.givenCommands(new CreatePrinterCommand(printerId, printerName))
                    .andGivenCommands(new AddNewPrintingCommand(printerId, printingName, printingId, ownerId, printingTime, printingStartDate, technicalBreakDuration))
                    .when(new AddNewPrintingCommand(printerId, secondPrintingName, printingId, ownerId, printingTime, newPrintingStartDate, technicalBreakDuration))
        
        then:
        action.expectException(Exception.class)
                .expectExceptionMessage("Printer busy on this time")
    }
    
    private LocalDateTime createDate(String date) {
        def createdDate = LocalDateTime.parse(date,
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return createdDate
    }
    
}
