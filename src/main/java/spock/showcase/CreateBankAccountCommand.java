package spock.showcase;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateBankAccountCommand {

	@TargetAggregateIdentifier
	private final String accoundId;

	private final String ownerName;

	public CreateBankAccountCommand(String accoundId, String ownerName) {
		super();
		this.accoundId = accoundId;
		this.ownerName = ownerName;
	}

	public String getAccountId() {
		return accoundId;
	}

	public String getAccountOwner() {
		return ownerName;
	}

}
