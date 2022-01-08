package hw2;

/*
 * Since obtaining a seat in labs is the actual objective of many students which run on different threads,
 		it is a must for this class to be synchronized.
 */
public class Lab {
	private String name;
	private int capacity;
	
	// The current number of students in this lab. It is zero at the beginning.
	private int usedSeats;
	
	// This attribute is a reference to current study group. It is null at the beginning.
	// It is used to determine a newcomer student can or can not study with this student group.
	private StudyGroup currentOwner;
	
	public Lab(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
		this.usedSeats = 0;
		this.currentOwner = null;
	}
	
	public int getCapacity() { return this.capacity; }
	
	public String getName() { return this.name; }
	
	/* 
	 * This method is called when a student wants to study.
	 * It is a blocking method until the lab gets available for this student.
	 * It returns only when the seat is acquired by this student.
	 * However, while waiting for the lab to be available it does not poll, it simply waits to be notified.
	 * 
	 */
	public synchronized void getSeat(StudyGroup studyGroup) {
		
		// This while loop is exited only when a seat is acquired.
		while(true) {
			
			// If the student who belongs to given studyGroup is the first comer to the lab, 
			// 		update currentOwner of this lab with his/her group. Acquire the seat and return.
			if(this.currentOwner == null && this.usedSeats==0) {
				this.currentOwner = studyGroup;
				this.usedSeats++;
				return;
			}
			
			// If the ownership of the lab belongs to another group or the capacity of the lab is full, 
			//		then this student should release the lock and wait until he/she is notified by students who stops studying.
			else if( !(this.currentOwner == studyGroup) || this.usedSeats == this.capacity) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			// If the currentOwner is this group and there is free space in the lab, then the student is able to acquire the seat.
			else {
				this.usedSeats++;
				this.currentOwner = studyGroup;
				return;
			}
		}
	}
	
	 /*
	  * This method is called whenever the student is finished studying.
	  * It simply decrements the usedSeats and checks if the student who stopped studying is the last student remaining in the lab.
	  * 		If so, then it changes the current owner of the lab to null
	  * 				in order to allow students with different study groups to observe a seat.
	  * After then, it notifies all the students waiting for a seat.
	  */
	 public synchronized void releaseSeat(StudyGroup studyGroup) {
		this.usedSeats--;
		if(this.usedSeats == 0) {
			this.currentOwner = null;
		}
		notifyAll();
	}
}
