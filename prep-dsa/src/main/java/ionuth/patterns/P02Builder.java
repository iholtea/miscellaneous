package ionuth.patterns;

/*
 * We might want to avoid having a public constructor with all the 
 * class fields as parameters as the client code might use the wrong 
 * order when providing 5 or 10 parameters
 * as some consecutive parameters might have the same type we will not have a compiler error.
 * Builder patter also offers a fluent API of creating objects
 */

class BankAccount {

    private long accountNumber;
    private String owner;
    private String branch;
    private double balance;
    private double interestRate;
	
    // it can be also private from the Builder pattern pov
    public BankAccount() {}
    
    static class Builder {
    	private long accountNumber;
        private String owner;
        private String branch;
        private double balance;
        private double interestRate;
        
        public Builder withNumber(long accountNumber) {
        	this.accountNumber = accountNumber;
        	return this;
        }
        
        public Builder withOwner(String owner) {
        	this.owner = owner;
        	return this;
        }
        
        public Builder atBranch(String branch) {
        	this.branch = branch;
        	return this;
        }
        
        public Builder openingBalance(double balance) {
        	this.balance = balance;
        	return this;
        }
        
        public Builder withInterest(double interestRate) {
        	this.interestRate = interestRate;
        	return this;
        }
        
        public BankAccount build() {
        	BankAccount account = new BankAccount();
        	account.accountNumber = this.accountNumber;
        	account.owner = this.owner;
        	account.branch = this.branch;
        	account.balance = this.balance;
        	account.interestRate = this.interestRate;
        	return account;
        }
    }
    
    public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", owner=" + owner + ", branch=" + branch + ", balance="
				+ balance + ", interestRate=" + interestRate + "]";
	}
    

}

public class P02Builder {
	
	public static void main(String[] args) {
		
		BankAccount account1 = new BankAccount.Builder()
				.withNumber(123456L)
				.withOwner("Ion Popescu")
				.atBranch("Pantelimon")
				.openingBalance(88999)
				.withInterest(2.5)
				.build();
		System.out.println( account1 );
		
	}
	
}
