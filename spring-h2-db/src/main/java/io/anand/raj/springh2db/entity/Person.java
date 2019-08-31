package io.anand.raj.springh2db.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Person {
	@SequenceGenerator(sequenceName = "PERSON_SEQ", name = "PERSON_GENERATOR", allocationSize = 50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_GENERATOR")
	@Id
	private long id;
	private String userName;
	private String firstName;
	private String lastName;
	private String type;
	private Date joinedOn;
	private Date modifiedOn;
	@OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
//	@JsonIgnoreProperties({"person", "type", "initiatedOn", "modifiedOn"})
	private List<EftTransaction> eftTransactions;
}
