package io.anand.raj.fta.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "STUDENT")
public class Student {

	@Id
	@Column(name = "STUDENT_ID")
	@SequenceGenerator(name = "STUDENT_ID_GENERATOR", sequenceName = "STUDENT_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "STUDENT_ID_GENERATOR", strategy = GenerationType.SEQUENCE)
	private long studentId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "JOIN_DATE")
	@Temporal(TemporalType.DATE)
	private Date joinDate;
	
	@Column(name = "STANDARD")
	private String standard;
	
	@Column(name = "GENDER", nullable = true)
	private char gender;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "STUDENT_DETAILS_ID", nullable = true)
	private StudentDetails details;
	
	@OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
	private List<StudentCertificates> certificates;

	public StudentDetails getDetails() {
		return details;
	}

	public void setDetails(StudentDetails details) {
		this.details = details;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public List<StudentCertificates> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<StudentCertificates> certificates) {
		this.certificates = certificates;
	}

}
