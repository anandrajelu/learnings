package io.anand.raj.springh2db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import io.anand.raj.springh2db.entity.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

    @Query(value = "select p from Person p where userName like :pattern or type = :type")
    public List<Person> findUsersByMatching(@Param("pattern") String pattern, @Param("type") String type);
}
