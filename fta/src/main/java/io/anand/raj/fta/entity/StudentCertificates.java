package io.anand.raj.fta.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "STUDENT_CERTIFICATES")
public class StudentCertificates {
	
	@Id
	@Column(name = "CERTIFICATE_ID")
	@SequenceGenerator(name = "STUDENT_CERTIFICATE_ID_GENERATOR", sequenceName = "STUDENT_CERTIFICATE_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "STUDENT_CERTIFICATE_ID_GENERATOR", strategy = GenerationType.SEQUENCE)
	private long certificateId;
	
	private String description;

	@Column(name = "CERTIFICATE_LEVEL")
	private long certificateLevel;
	
	@Column(name = "VALID_TILL")
	@Temporal(TemporalType.DATE)
	private Date validTill;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STUDENT_ID")
	private Student student;

	public long getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(long certificateId) {
		this.certificateId = certificateId;
	}

	public Date getValidTill() {
		return validTill;
	}

	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCertificateLevel() {
		return certificateLevel;
	}

	public void setCertificateLevel(long certificateLevel) {
		this.certificateLevel = certificateLevel;
	}
	
}
