package com.vignesh.coursemicroservice.controller;

import com.google.inject.internal.cglib.core.$CollectionUtils;
import com.vignesh.coursemicroservice.interComm.UserClient;
import com.vignesh.coursemicroservice.model.Transaction;
import com.vignesh.coursemicroservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CourseController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private CourseService courseService;
    @Autowired
    private Environment env;

    @Value("course_service")
    private  String serviceId;

    @GetMapping("/service/port")
    public String getPort()
    {
        return "service working:"+env.getProperty("local.server.port");
    }
    @GetMapping("/service/instances")
    public ResponseEntity<?> getInstances() {
        return new ResponseEntity<>(discoveryClient.getInstances(serviceId), HttpStatus.OK);
    }


    @GetMapping("/service/user/{userId}")
    public ResponseEntity<?> findTransactionOfUsers(@PathVariable Long userId)
    {
        return ResponseEntity.ok(courseService.findTransactionOfUser(userId));
    }
    @GetMapping("/service/all")
    public ResponseEntity<?> findAllCourse(){
        return ResponseEntity.ok(courseService.allCourse());
    }
    @PostMapping("/service/enroll")
  public  ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction)
  {
   transaction.setDateOfIssue(LocalDateTime.now());
   transaction.setCourse(courseService.findByCourseId(transaction.getCourse().getId()));
   return  new ResponseEntity<>(courseService.saveTransaction(transaction), HttpStatus.CREATED);
  }

  public  ResponseEntity<?> findStudentsOfCourse(@PathVariable Long courseId)
  {
      List<Transaction>  transactions=courseService.findTransactionOfCourse(courseId);
      if(CollectionUtils.isEmpty(transactions)){
          return ResponseEntity.notFound().build();
      }
      List<Long> userIdLIst=transactions.parallelStream().map(t->t.getUserId()).collect(Collectors.toList());
      List<String> students =userClient.getUserNames(userIdLIst);
      return  ResponseEntity.ok(students);
  }


}
