package com.vignesh.coursemicroservice.repository;

import com.vignesh.coursemicroservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository  extends JpaRepository<Transaction,Long> {
    List<Transaction> findAllByUser_id(Long userId);
    List<Transaction> findAllByCourseId(Long courseId);

}
