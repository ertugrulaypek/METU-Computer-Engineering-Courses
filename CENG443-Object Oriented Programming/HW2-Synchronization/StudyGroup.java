package hw2;

public class StudyGroup {
	private Lab lab;
	private String name;
	
	public StudyGroup(String name, Lab lab) {
		this.name = name;
		this.lab = lab;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Lab getLab() {
		return this.lab;
	}

	// This method is simply requests a seat from the lab. It is blocked until it gets the seat.
	public void startStudyingWith() {
		this.lab.getSeat(this);
	}

	// This method simply acknowledges the lab that it is done with the given seat. 
	public void stopStudyingWith() {
		this.lab.releaseSeat(this);
	}
}
