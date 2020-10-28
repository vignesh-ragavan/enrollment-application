package com.vignesh.coursemicroservice.model;
import com.vignesh.coursemicroservice.model.Course;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "transaction")

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    @JoinColumn(name  ="course_id",referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private  Course course;

    @Column(name  ="userId")
    private  Long userId;

    @Column(name  ="date_of_issue")
    private LocalDateTime dateOfIssue;
}
