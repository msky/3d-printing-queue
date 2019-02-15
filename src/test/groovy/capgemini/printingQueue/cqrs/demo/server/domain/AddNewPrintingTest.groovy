package capgemini.printingQueue.cqrs.demo.server.domain

import static org.junit.Assert.*

import java.text.SimpleDateFormat

import org.axonframework.test.aggregate.AggregateTestFixture
import org.axonframework.test.aggregate.FixtureConfiguration
import org.junit.Test

import spock.lang.Specification

class AddNewPrintingTest extends Specification {

    private FixtureConfiguration<Printer> fixture
    
    def setup() {
        fixture = new AggregateTestFixture<Printer>(Printer.class)
    }
    
    def "should call add new printing event"() {
        given:
     
        def printerId = UUID.randomUUID().toString()
        def printerName = "printer1"
        def printingId = new Random().nextLong()
        def ownerId = "123"
        def printingTime = 21600000 //6h
        def printingStartDate = Calendar.getInstance().getTime()
        fixture.setReportIllegalStateChange(false)
        
        when:
        def action = fixture.givenCommands(new CreatePrinterCommand(printerId, printerName))
                    .when(new AddNewPrintingCommand(printerId, printingId, ownerId, printingTime, printingStartDate))
        
        then:
        action.expectEvents(new NewPrintingAddedEvent(printerId, printingId, ownerId, printingTime, printingStartDate))
    }
    
    def "should add new printing to list"() {
        given:
        def printer = new Printer()
        def printerId = UUID.randomUUID().toString()
        def printingId = new Random().nextLong()
        def printerName = "printer1"
        def ownerId = "123"
        def printingTime = 21600000 //6h
        def printingStartDate = createDate("2019-02-15 08:00:00")
        def newPrintingStartDate = createDate("2019-02-15 15:00:00")
        fixture.setReportIllegalStateChange(false)
        
        when:
        def action = fixture.givenCommands(new CreatePrinterCommand(printerId, printerName)).when(new AddNewPrintingCommand(printerId, printingId, ownerId, printingTime, printingStartDate))
        
        then:
        action.expectEvents(new NewPrintingAddedEvent(printerId, printingId, ownerId, printingTime, printingStartDate))
    }
    def "should not add new printing to list when technical break"() {
        given:
        def printer = new Printer()
        def printerId = UUID.randomUUID().toString()
        def printerName = "printer1"
        def printingId = new Random().nextLong()
        def ownerId = "123"
        def printingTime = 21600000 //6h
        def printingStartDate = createDate("2019-02-15 08:00:00")
        def newPrintingStartDate = createDate("2019-02-15 14:05:00")
        fixture.setReportIllegalStateChange(false)
        
        when:
        def action = fixture.givenCommands(new CreatePrinterCommand(printerId, printerName))
                    .andGiven(new AddNewPrintingCommand(printerId, printingId, ownerId, printingTime, printingStartDate))
                    .when(new AddNewPrintingCommand(printerId, printingId, ownerId, printingTime, newPrintingStartDate))
        
        then:
        action.expectExceptionMessage("Printer busy on this time")
    }
    
    def "should not add new printing to list when printer busy on time"() {
        given:
        def printer = new Printer()
        def printerId = UUID.randomUUID().toString()
        def printingId = new Random().nextLong()
        def printerName = "printer1"
        def ownerId = "123"
        def printingTime = 21600000 //6h
        def printingStartDate = createDate("2019-02-15 08:00:00")
        def newPrintingStartDate = createDate("2019-02-15 10:00:00")
        fixture.setReportIllegalStateChange(false)
        
        when:
        def action = fixture.givenCommands(new CreatePrinterCommand(printerId, printerName))
                    .andGiven(new AddNewPrintingCommand(printerId, printingId, ownerId, printingTime, printingStartDate))
                    .when(new AddNewPrintingCommand(printerId, printingId, ownerId, printingTime, newPrintingStartDate))
        
        then:
        action.expectExceptionMessage("Printer busy on this time")
    }
    
    private Date createDate(String date) {
        def format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        def createdDate = format.parse(date)
        return createdDate
    }
    
}
