import java.util.ArrayList;

//*******************************************************
// Student.java		Author: Prakash Acharya
//
// This is an abstract class. 
//
// Defines a general student object.
//*******************************************************

public abstract class Student implements Comparable<Student> {
	private Faculty fac;
	private int id;
	private String name, major, degree;
	private int numOfUnitsEnrolled;
	protected static final int maxUnitsAllowed = 18;
	
	// M2 HOMEWORK STATIC VARIABLE 
	// ArrayList of Student type contains all the students(Resident, NonResident, International)
	private static ArrayList<Student> students = new ArrayList<Student>();
	
	public static int count = 0;
	public static final int START_ID = 100000;
	public static final double PER_UNIT_COST = 576.0;
	public static final int DEFAULT_NUM_UNITS_ENROLLED = 0;
	
	
	// this constructor is invoked when all the parameters are provided
	// student id starts from 100000, and is incremented by 1 for each new student
	public Student(String name, String major, String degree, 
			int numOfUnitsEnrolled) {
		this.id = START_ID + count;
		this.name = name;
		this.major = major;
		this.degree = degree;
		this.numOfUnitsEnrolled = numOfUnitsEnrolled;
	
		// M2 HOMEWORK ENUM USE
		switch (major) {
			case "Computer Science":
			case "Microbiology": 
			case "Civil Engineering":
				this.fac = Faculty.SCIENCE_AND_ENGINEERING;
				break;
			case "Business Administration":
			case "Accounting":
				this.fac = Faculty.BUSINESS;
				break;
				
			case "Poetry":
			case "Photography":
				this.fac = Faculty.ARTS;
				break;
			default:
				this.fac = Faculty.ARTS;
				break;
		}
		count++;
		students.add(this);
		
	}
	
	
	// this constructor will be invoked if no id and number number of units enrolled is provided
	public Student(String name, String major, String degree) {
		this(name, major, degree, DEFAULT_NUM_UNITS_ENROLLED);
		
	}
	
	// M2 HOMEWORK STATIC METHOD
	//static method that returns a list of all students
	public static ArrayList<Student> getAllStudents(){
		return students;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public int getId() {
		return id;
	}

	public int getNumOfUnitsEnrolled() {
		return numOfUnitsEnrolled;
	}

	public void setNumOfUnitsEnrolled(int numOfUnitsEnrolled) {
		if(numOfUnitsEnrolled > 0 && numOfUnitsEnrolled < 18)
			this.numOfUnitsEnrolled = numOfUnitsEnrolled;
	}
	
	public String getFaculty() {
		return fac.toString();
	}
	
	@Override
	public int compareTo(Student s) {
		if(name.compareToIgnoreCase(s.name)!=0) {
			return name.compareToIgnoreCase(s.name);
		} else {
			return Integer.compare(id, s.id);
		}
	}
	
	@Override
	public String toString() {
		String student = "Name: " + getName() + "\nStudent Id: " + id + "\nMajor: " + getMajor();
		student += "\nDegree: " + getDegree() + "\nFaculty/School: " + this.getFaculty() +
				"\nNumber of Units Enrolled: " + getNumOfUnitsEnrolled();
		return student;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Student) {
			Student univStu = (Student) obj;
			return id==univStu.id && name.equalsIgnoreCase(univStu.name) && 
					degree.equals(univStu.degree) && major.equals(univStu.major);
		}else {
			return false;
		}
	}
	
	// Generate total tuition cost to pay for a student
	// To be implemented by child classes
	public abstract String generateTutionCost();

}