package spock.showcase;

public class MoneyDepositedEvent {

	private final String accoundId;

	private final long ammount;

	public MoneyDepositedEvent(String accoundId, long ammount) {
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
