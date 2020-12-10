package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable. Students have a first name, last
 * name, id number, number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 * @author Sanjana Cheerla
 */
public class Student implements Comparable<Student>, Identifiable {
	/**
	 * Student's first name
	 */
	private String first;
	/**
	 * Student's last name
	 */
	private String last;
	/**
	 * Student's ID
	 */
	private int id;
	/**
	 * Student's credit hours
	 */
	private int creditHours;
	/**
	 * Student's GPA
	 */
	private double gpa;
	/**
	 * Student's unity id
	 */
	private String unityID;

	/**
	 * Constructs a student object based on first, last name, id, credit hours, gpa
	 * and unity id
	 * 
	 * @param first       name of student
	 * @param last        name of student
	 * @param id          of student
	 * @param creditHours of student
	 * @param gpa         of student
	 * @param unityId     of student
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityId) {
		this.setFirst(first);
		this.setLast(last);
		this.setId(id);
		this.setCreditHours(creditHours);
		this.setGpa(gpa);
		this.setUnityID(unityId);
	}

	/**
	 * Returns first name of student
	 * 
	 * @return first name of student
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * Sets the first name of the student
	 * 
	 * @param first name of student
	 * @throws IllegalArgumentException if first name is empty or null
	 */
	public void setFirst(String first) {
		if (first == null || first.isEmpty())
			throw new IllegalArgumentException();
		this.first = first;
	}

	/**
	 * Returns last name of student
	 * 
	 * @return last name of student
	 */
	public String getLast() {
		return last;
	}

	/**
	 * Sets the last name of the student
	 * 
	 * @param last name of student
	 * @throws IllegalArgumentException if last name is empty or null
	 */
	public void setLast(String last) {
		if (last == null || last.isEmpty())
			throw new IllegalArgumentException();
		this.last = last;
	}

	/**
	 * Returns ID number of student
	 * 
	 * @return id number of student
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * Sets ID number of student
	 * 
	 * @param id number of the student
	 * @throws IllegalArgumentException if id number is negative
	 */
	public void setId(int id) {
		if (id < 0)
			throw new IllegalArgumentException();
		this.id = id;
	}

	/**
	 * Returns credit hours of student
	 * 
	 * @return credit hours of student
	 */
	public int getCreditHours() {
		return creditHours;
	}

	/**
	 * Sets credit hours of student
	 * 
	 * @param creditHours of the student
	 * @throws IllegalArgumentException if creditHours number is negative
	 */
	public void setCreditHours(int creditHours) {
		if (creditHours < 0)
			throw new IllegalArgumentException();
		this.creditHours = creditHours;
	}

	/**
	 * Returns GPA of student
	 * 
	 * @return the gpa
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * Sets gpa of student
	 * 
	 * @param gpa of student
	 * @throws IllegalArgumentException if gpa is negative
	 */
	public void setGpa(double gpa) {
		if (gpa < 0)
			throw new IllegalArgumentException();
		this.gpa = gpa;
	}

	/**
	 * Returns unity ID of student
	 * 
	 * @return unity ID of student
	 */
	public String getUnityID() {
		return unityID;
	}

	/**
	 * Sets the unity id of the student
	 * 
	 * @param unityId of student
	 * @throws IllegalArgumentException if unityId is empty or null
	 */
	public void setUnityID(String unityId) {
		if (unityId == null || unityId.isEmpty())
			throw new IllegalArgumentException();
		this.unityID = unityId;
	}

	/**
	 * generates hash code for student using first, last and student id number
	 * 
	 * @return hash code value for student
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + creditHours;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + id;
		return result;
	}

	/**
	 * checks if two students are equal based on first, last name and student id
	 * number
	 * 
	 * @return true if students are equal, false if they are not equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (creditHours != other.creditHours)
			return false;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * Generates a string representation of student
	 * 
	 * @return String representation of student in the format: firstName lastName,
	 *         id= , creditHours= , gpa=. unityId=)
	 */
	@Override
	public String toString() {
		return first + " " + last + ", id=" + id + ", creditHours=" + creditHours + ", gpa=" + gpa + ", unityId="
				+ unityID;
	}

	/**
	 * Compares two student objects
	 * 
	 * @return 0 if students are the same, return negative if this student is less
	 *         than student being compared and positive if this student is greater
	 *         than student being compared.
	 */
	@Override
	public int compareTo(Student o) {
		if (this.equals(o)) {
			return 0;
		}
		if (this.last.compareTo(o.last) < 0) {
			return -1;
		} else if (this.last.compareTo(o.last) > 0) {
			return 1;
		}
		if (this.first.compareTo(o.first) < 0) {
			return -1;
		} else if (this.first.compareTo(o.last) > 0) {
			return 1;
		}
		if (this.id < o.id) {
			return -1;
		} else if (this.id > o.id) {
			return 1;
		}
		return 0;
	}

}
