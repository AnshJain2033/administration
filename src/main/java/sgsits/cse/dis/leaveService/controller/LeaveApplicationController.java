package sgsits.cse.dis.leaveService.controller;

import sgsits.cse.dis.leaveService.constants.RestAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sgsits.cse.dis.leaveService.model.Leave;
import sgsits.cse.dis.leaveService.request.LeaveRequestForm;
import sgsits.cse.dis.leaveService.response.LeaveApplicationResponse;
import sgsits.cse.dis.leaveService.service.LeaveApplicationService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/home")
public class LeaveApplicationController {
    @Autowired
    LeaveApplicationService leaveApplicationService;

    @GetMapping(value = RestAPI.GET_HELLO)
    public String getCheck(){
       return "hello";
    }

    //@ApiOperation(value="Get Experts' Names and Designations", response = ExpertNamesAndDesignationsResponse.class, httpMethod = "GET", produces = "application/json")
    @PostMapping(value= RestAPI.POST_LEAVE_APPLICATION, produces = "application/json")
    public ResponseEntity<?> postLeaveApplication(@RequestBody LeaveRequestForm leaveApplication)
    {
        LeaveApplicationResponse response = leaveApplicationService.createLeaveApplcation(leaveApplication);
        return new ResponseEntity<LeaveApplicationResponse>(response,HttpStatus.OK);
    }
    @GetMapping(value = RestAPI.GET_LEAVE_OF_A_STUDENT)
    public ResponseEntity<List<Leave>> getLeavesByStudentId(@RequestParam("studentId") String studentId){
        return ResponseEntity.ok(leaveApplicationService.getAllLeavesOfAStudent(studentId));
    }


}
