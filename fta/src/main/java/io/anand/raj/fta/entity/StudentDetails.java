package io.anand.raj.fta.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "STUDENT_DETAILS")
@Table(name = "STUDENT_DETAILS")
public class StudentDetails {
	
	@Id
	@Column(name = "STUDENT_DETAILS_ID")
	@SequenceGenerator(name = "STUDENT_DETAILS_ID_GENERATOR", sequenceName = "STUDENT_DETAILS_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "STUDENT_DETAILS_ID_GENERATOR", strategy = GenerationType.SEQUENCE)
	private long studentDetailsId;
	
	@Column(name = "FATHERS_NAME")
	private String fathersName;
	
	@Column(name = "FEES_PAID")
	private char feesPaid;
	
	@Column(name = "FEES_PAID_ON")
	@Temporal(TemporalType.DATE)
	private Date feesPaidOn;
	
	@Column(name = "ANNUAL_MARKS")
	private int annualMarks;

	public long getStudentDetailsId() {
		return studentDetailsId;
	}

	public void setStudentDetailsId(long studentDetailsId) {
		this.studentDetailsId = studentDetailsId;
	}

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public char getFeesPaid() {
		return feesPaid;
	}

	public void setFeesPaid(char feesPaid) {
		this.feesPaid = feesPaid;
	}

	public Date getFeesPaidOn() {
		return feesPaidOn;
	}

	public void setFeesPaidOn(Date feesPaidOn) {
		this.feesPaidOn = feesPaidOn;
	}

	public int getAnnualMarks() {
		return annualMarks;
	}

	public void setAnnualMarks(int annualMarks) {
		this.annualMarks = annualMarks;
	}
	
}
