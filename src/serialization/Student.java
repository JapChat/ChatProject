package serialization;

import java.io.Serializable;

public class Student implements Serializable{
	private transient int id;
	private transient String name;
	private String major;
	
	public Student(int id, String name, String major) {
		super();
		this.id = id;
		this.name = name;
		this.major = major;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getMajor() {
		return major;
	}	
}

