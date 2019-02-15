package capgemini.printingQueue.cqrs.demo.server.domain

import static org.junit.Assert.*

import java.text.SimpleDateFormat

import org.axonframework.test.aggregate.AggregateTestFixture
import org.axonframework.test.aggregate.FixtureConfiguration
import org.junit.Test

import spock.lang.Specification

class AddNewPrintingTest extends Specification {

    private FixtureConfiguration<Queue> fixture
    
    def setup() {
        fixture = new AggregateTestFixture<Queue>(Queue.class)
    }
    
    def "should call add new printing event"() {
        given:
     
        def queueId = UUID.randomUUID().toString()
        def queueName = "queue1"
        def printingId = new Random().nextLong()
        def ownerId = "123"
        def printingTime = 21600000 //6h
        def printingStartDate = Calendar.getInstance().getTime()
        fixture.setReportIllegalStateChange(false)
        
        when:
        def action = fixture.givenCommands(new CreateQueueCommand(queueId, queueName))
                    .when(new AddNewPrintingCommand(queueId, printingId, ownerId, printingTime, printingStartDate))
        
        then:
        action.expectEvents(new NewPrintingAddedEvent(queueId, printingId, ownerId, printingTime, printingStartDate))
    }
    
    def "should add new printing to list"() {
        given:
        def queue = new Queue()
        def queueId = UUID.randomUUID().toString()
        def printingId = new Random().nextLong()
        def ownerId = "123"
        def printingTime = 21600000 //6h
        def printingStartDate = createDate("2019-02-15 08:00:00")
        def newPrintingStartDate = createDate("2019-02-15 15:00:00")
        def printing = new Printing(printingId, ownerId, printingTime, printingStartDate)
        queue.getPrintingList().add(printing)
        
        when:
        queue.on(new NewPrintingAddedEvent(queueId, printingId, ownerId, printingTime, newPrintingStartDate))
        
        then:
        queue.getPrintingList().size() == 2
    }
    def "should not add new printing to list when technical break"() {
        given:
        def queue = new Queue()
        def queueId = UUID.randomUUID().toString()
        def printingId = new Random().nextLong()
        def ownerId = "123"
        def printingTime = 21600000 //6h
        def printingStartDate = createDate("2019-02-15 08:00:00")
        def newPrintingStartDate = createDate("2019-02-15 14:05:00")
        def printing = new Printing(printingId, ownerId, printingTime, printingStartDate)
        queue.getPrintingList().add(printing)
        
        when:
        queue.on(new NewPrintingAddedEvent(queueId, printingId, ownerId, printingTime, newPrintingStartDate))
        
        then:
        thrown Exception
    }
    
    def "should not add new printing to list when printer busy on time"() {
        given:
        def queue = new Queue()
        def queueId = UUID.randomUUID().toString()
        def printingId = new Random().nextLong()
        def ownerId = "123"
        def printingTime = 21600000 //6h
        def printingStartDate = createDate("2019-02-15 08:00:00")
        def newPrintingStartDate = createDate("2019-02-15 10:00:00")
        def printing = new Printing(printingId, ownerId, printingTime, printingStartDate)
        queue.getPrintingList().add(printing)
        
        when:
        queue.on(new NewPrintingAddedEvent(queueId, printingId, ownerId, printingTime, newPrintingStartDate))
        
        then:
        thrown Exception
    }
    
    private Date createDate(String date) {
        def format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        def createdDate = format.parse(date)
        return createdDate
    }
    
}
