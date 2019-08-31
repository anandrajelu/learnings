package io.anand.raj.springh2db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.anand.raj.springh2db.entity.EftTransaction;

@Repository
public interface EftTransactionRepository extends JpaRepository<EftTransaction, Long> {

}
