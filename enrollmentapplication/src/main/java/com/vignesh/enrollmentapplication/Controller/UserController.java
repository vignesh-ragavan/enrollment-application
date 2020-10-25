package com.vignesh.enrollmentapplication.Controller;

import com.vignesh.enrollmentapplication.model.Role;
import com.vignesh.enrollmentapplication.model.User;
import com.vignesh.enrollmentapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private Environment env;
    @GetMapping("/service/port")
    public  String getPort(){
        return  "Service port number:"+env.getProperty("local.server.port");
    }

    @Value("${spring.application.name}")
    private String serviceId;

    @GetMapping("/service/instances")
    public ResponseEntity<?> getInstances() {
        return new ResponseEntity<>(discoveryClient.getInstances(serviceId), HttpStatus.OK);
    }

        @GetMapping("/service/services")
    public ResponseEntity<?> getServices(){
        return new ResponseEntity<>(discoveryClient.getServices(),HttpStatus.OK);

    }

    @PostMapping("/service/registration")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.findByUserName(user.getUserName()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        user.setRole(Role.USER);
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/service/login")
    public ResponseEntity<?> getUser(Principal principal) {

        if (principal == null || principal.getName() == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return  ResponseEntity.ok(userService.findByUserName(principal.getName()));

    }

    @PostMapping("/service/names")
    public ResponseEntity<?> getUsers(@RequestBody List<Long> idList) {
        return ResponseEntity.ok(userService.findUsers(idList));
    }
}

