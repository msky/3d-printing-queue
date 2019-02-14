package spock.showcase;

public class BankAccountCreatedEvent {

	private final String accountId;

	private final String accountOwner;

	public BankAccountCreatedEvent(String accoundId, String accountOwner) {
		super();
		this.accountId = accoundId;
		this.accountOwner = accountOwner;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getAccountOwner() {
		return accountOwner;
	}
}
