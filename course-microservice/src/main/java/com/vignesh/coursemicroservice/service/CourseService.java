package com.vignesh.coursemicroservice.service;

import com.vignesh.coursemicroservice.model.Course;
import com.vignesh.coursemicroservice.model.Transaction;

import java.util.List;

public interface CourseService {
    public List<Transaction> findTransactionOfUser(Long UserId);
    public List<Transaction> findTransactionOfCourse(Long courseId);
    public Course findByCourseId(Long courseId);
    public List<Course> allCourse();
    public  Transaction saveTransaction(Transaction transaction);



}
