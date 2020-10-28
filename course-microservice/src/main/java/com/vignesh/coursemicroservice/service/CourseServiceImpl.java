package com.vignesh.coursemicroservice.service;

import com.vignesh.coursemicroservice.model.Course;
import com.vignesh.coursemicroservice.model.Transaction;
import com.vignesh.coursemicroservice.repository.CourseRepository;
import com.vignesh.coursemicroservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public List<Course> allCourse()
    {
        return courseRepository.findAll();
    }
    @Override


    public Course findByCourseId(Long courseId){
        return courseRepository.findById(courseId).orElse(null);
    }
    @Override

    public List<Transaction> findTransactionOfCourse(Long courseId){
        return transactionRepository.findAllByCourseId(courseId);
    }
    @Override
    public List<Transaction> findTransactionOfUser(Long UserId){
        return transactionRepository.findAllByUserId(UserId);
    }
    @Override
    public  Transaction saveTransaction(Transaction transaction)
    {
        return  transactionRepository.save(transaction);
    }
}
