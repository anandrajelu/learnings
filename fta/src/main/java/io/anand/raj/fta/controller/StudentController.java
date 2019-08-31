package io.anand.raj.fta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.anand.raj.fta.dao.StudentCertificatesDAO;
import io.anand.raj.fta.dao.StudentDAO;
import io.anand.raj.fta.entity.Student;
import io.anand.raj.fta.entity.StudentCertificates;
import io.anand.raj.fta.model.PrimaryKey;

@RestController
@RequestMapping(path = "student")
public class StudentController {
	
	@Autowired
	StudentDAO dao;
	
	@Autowired
	StudentCertificatesDAO certdao;
	
	@RequestMapping(path = "add")
	public boolean add(@RequestBody Student student) {
		return dao.save(student);
	}
	
	@RequestMapping(path = "getById")
	public Student getById(@RequestBody PrimaryKey id) {
		dao.setEntity(Student.class);
		return dao.findById(id.getId());
	}
	
	@RequestMapping(path = "addCertificate")
	public boolean addCertificate(@RequestBody StudentCertificates studentCertificates) {
		certdao.setEntity(StudentCertificates.class);
		return certdao.save(studentCertificates);
	}

}
