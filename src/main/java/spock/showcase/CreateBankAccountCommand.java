package spock.showcase;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateBankAccountCommand {

	@TargetAggregateIdentifier
	private final String accountId;

	private final String ownerName;

	public CreateBankAccountCommand(String accountId, String ownerName) {
		super();
		this.accountId = accountId;
		this.ownerName = ownerName;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getAccountOwner() {
		return ownerName;
	}

}
