package capgemini.printingQueue.cqrs.demo.domain;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;

import spock.lang.Specification;

class NewQueueCreationTest extends Specification {
	
	private FixtureConfiguration<Queue> fixture
	
	def setup() {
		fixture = new AggregateTestFixture<Queue>(Queue.class)
	}

	def "after queue creation id and name should be assigned"() {
		given:
		def id = UUID.randomUUID().toString()
		def name = "queue1"
		def queue = new Queue()
		
		when:
		queue.on(new QueueCreatedEvent(id, name))
		
		then:
		queue.getId() == id
		queue.getName() == name
		
	}
}
