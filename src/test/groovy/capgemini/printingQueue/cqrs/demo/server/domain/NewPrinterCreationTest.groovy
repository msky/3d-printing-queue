package capgemini.printingQueue.cqrs.demo.server.domain;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;

import spock.lang.Specification;

class NewPrinterCreationTest extends Specification {
	
	private FixtureConfiguration<Printer> fixture
	
	def setup() {
		fixture = new AggregateTestFixture<Printer>(Printer.class)
	}

	def "after printer creation id and name should be assigned"() {
		given:
		def id = UUID.randomUUID().toString()
		def name = "printer1"
		def queue = new Printer()
		
		when:
		queue.on(new PrinterCreatedEvent(id, name))
		
		then:
		queue.getId() == id
		queue.getName() == name
		
	}
    
    def "should call creation event"() {
        
        given:
        def id = UUID.randomUUID().toString()
        def name = "printer1"
        
        when:
        def action = fixture.given().when(new CreatePrinterCommand(id, name))
        
        then:
        action.expectEvents(new PrinterCreatedEvent(id, name))
    }
    
}
