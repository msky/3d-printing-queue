package spock.showcase;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class BankAccount {

	@AggregateIdentifier
	private String accountId;

	private String accountOwner;

	private long balance;

	public BankAccount() {
	};

	@CommandHandler
	public BankAccount(CreateBankAccountCommand command) {
		AggregateLifecycle.apply(new BankAccountCreatedEvent(command.getAccountId(), command.getAccountOwner()));
	}

	@EventSourcingHandler
	public void on(BankAccountCreatedEvent event) {
		accountId = event.getAccountId();
		accountOwner = event.getAccountOwner();
	}

	@CommandHandler
	public void on(DepositMoneyCommand command) {
		AggregateLifecycle.apply(new MoneyDepositedEvent(command.getAccountId(), command.getAmmount()));
	}

	@EventSourcingHandler
	public void on(MoneyDepositedEvent event) {
		this.balance += event.getAmmount();
	}

	public long getBalance() {
		return this.balance;
	}
}
