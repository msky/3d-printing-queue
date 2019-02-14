package spock.showcase;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DepositMoneyCommand {

	@TargetAggregateIdentifier
	private final String accountId;

	private final long ammount;

	public DepositMoneyCommand(String accountId, long ammount) {
		super();
		this.accountId = accountId;
		this.ammount = ammount;
	}

	public String getAccountId() {
		return accountId;
	}

	public long getAmmount() {
		return ammount;
	}
	
}
