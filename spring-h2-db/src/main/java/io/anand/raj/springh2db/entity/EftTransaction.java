package io.anand.raj.springh2db.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class EftTransaction {
	@Id
	@SequenceGenerator(sequenceName = "EFT_TXN_SEQ", name = "EFT_TXN_GENERATOR", allocationSize = 50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EFT_TXN_GENERATOR")
	private long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
//	@JsonIdentityReference(alwaysAsId = true)
	private Person person;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	@JsonIdentityReference(alwaysAsId = true)
	private Person toPerson;
	private double amount;
	private String type;
	private Date initiatedOn;
	private Date modifiedOn;
}
