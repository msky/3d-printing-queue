package spock.showcase

import org.axonframework.test.aggregate.AggregateTestFixture
import org.axonframework.test.aggregate.FixtureConfiguration
import spock.lang.Specification

class BankAccountTest extends Specification {

	private FixtureConfiguration<BankAccount> fixture

	def setup() {
		fixture = new AggregateTestFixture<>(BankAccount.class)
	}

	/**
	 * Testing raw java code, without involving axon.
	 */
	def "balance should be increased after money was deposited"() {
		given:
		def accountId = UUID.randomUUID().toString()
		def accountOwner = "Max"
		def depositedMoneyAmmount = 12
		def account = new BankAccount()

		when:
		account.on(new BankAccountCreatedEvent(accountId, accountOwner))
		account.on(new MoneyDepositedEvent("id", depositedMoneyAmmount))
		
		then:
		account.getBalance() == depositedMoneyAmmount
	}
	
}
