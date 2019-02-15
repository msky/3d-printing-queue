package capgemini.printingQueue.cqrs.demo.domain

import static org.junit.Assert.*

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
        def printingTime = 6
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
        def printingTime = 6
        def printingStartDate = Calendar.getInstance().getTime()
        def printing = new Printing(printingId, ownerId, printingTime, printingStartDate)
        queue.getPrintingList().add(printing)
        
        when:
        queue.on(new NewPrintingAddedEvent(queueId, printingId, ownerId, printingTime, printingStartDate))
        
        then:
        queue.getPrintingList().size() == 2
    }
}
