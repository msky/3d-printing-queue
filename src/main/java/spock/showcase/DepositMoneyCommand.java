package spock.showcase;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DepositMoneyCommand {

	@TargetAggregateIdentifier
	private final String accoundId;

	private final long ammount;

	public DepositMoneyCommand(String accoundId, long ammount) {
		super();
		this.accoundId = accoundId;
		this.ammount = ammount;
	}

	public String getAccountId() {
		return accoundId;
	}

	public long getAmmount() {
		return ammount;
	}
	
}
