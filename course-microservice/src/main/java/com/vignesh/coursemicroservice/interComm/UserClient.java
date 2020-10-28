package com.vignesh.coursemicroservice.interComm;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.cloud.openfeign.FeignClient;
import java.util.List;

@FeignClient("user-services")
public interface UserClient {


    @RequestMapping(method = RequestMethod.POST,value = "/service/names",consumes = "application/json")
    List<String> getUserNames(@RequestBody List<Long > userIdList);

}
