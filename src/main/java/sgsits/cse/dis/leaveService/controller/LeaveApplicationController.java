package sgsits.cse.dis.leaveService.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import sgsits.cse.dis.leaveService.constants.RestAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sgsits.cse.dis.leaveService.model.Leave;
import sgsits.cse.dis.leaveService.request.LeaveRequestForm;
import sgsits.cse.dis.leaveService.response.LeaveApplicationResponse;
import sgsits.cse.dis.leaveService.service.FileStorageService;
import sgsits.cse.dis.leaveService.service.LeaveApplicationService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/home")
public class LeaveApplicationController {
    @Autowired
    LeaveApplicationService leaveApplicationService;
    @Autowired
    FileStorageService fileStorageService;

    @GetMapping(value = RestAPI.GET_HELLO)
    public String getCheck(){
       return "hello";
    }

    @PostMapping(value = RestAPI.POST_LEAVE_SUPPORTING_DOCUMENT)
    public ResponseEntity<?> postLeaveSupportingDocument(@RequestParam("id") String leaveId,@RequestPart("file") MultipartFile file){
            leaveApplicationService.postLeaveSupportingDocument(leaveId,file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping(value= RestAPI.POST_LEAVE_APPLICATION)
    public ResponseEntity<?> postLeaveApplication(@RequestBody LeaveRequestForm leaveRequestForm)
    {
        LeaveApplicationResponse response = leaveApplicationService.createLeaveApplcation(leaveRequestForm);
        return new ResponseEntity<LeaveApplicationResponse>(response,HttpStatus.OK);
    }
    @GetMapping(value = RestAPI.GET_LEAVE_OF_A_STUDENT)
    public ResponseEntity<List<Leave>> getLeavesByStudentId(@RequestParam("studentId") String studentId){
        return ResponseEntity.ok(leaveApplicationService.getAllLeavesOfAStudent(studentId));
    }
    @DeleteMapping(value = RestAPI.DELETE_LEAVE_BY_LEAVE_ID)
    public ResponseEntity<String> deleteLeaveByLeaveId(@RequestParam("leaveId") String leaveId){
        if(leaveApplicationService.deleteLeaveByLeaveId(leaveId).equals("Deleted Successfully")){
         return ResponseEntity.ok("Deleted Successfully");
        }
       else return ResponseEntity.badRequest().body("Error Deleting");
    }
    @PutMapping(value = RestAPI.PUT_ASSIGNED_TO_BY_STUDENT_ID)
    public ResponseEntity<String> putAssignedToByStudentId(@RequestParam("studentId")String studentId,@RequestParam("assignedTo")String assignedTo){
            return ResponseEntity.ok(leaveApplicationService.putAssignedToByStudentId(studentId,assignedTo));
    }
    @PutMapping(value = RestAPI.PUT_LEAVE_STATUS_BY_LEAVE_ID)
    public ResponseEntity<String> putLeaveStatusByLeaveId(@RequestParam("leaveId")String leaveId,@RequestParam("status")String status){
        return ResponseEntity.ok(leaveApplicationService.putLeaveStatusByLeaveId(status,leaveId));
    }
    @GetMapping(value = RestAPI.GET_LEAVE_BY_ASSIGNED_ID)
    public ResponseEntity<List<Leave>> getLeaveByAssignedId(@RequestParam("assignedId")String assignedId){
        if(!leaveApplicationService.getLeavesByAssignedId(assignedId).isEmpty()){
            return ResponseEntity.ok(leaveApplicationService.getLeavesByAssignedId(assignedId));
        }
        else return ResponseEntity.ok(null);
    }
    @GetMapping(value = RestAPI.GET_LEAVE_SUPPORTING_DOCUMENTS)
    public ResponseEntity<?> getLeaveSupportingDocumentById(@RequestParam("leaveId")String leaveId){
//        if(!leaveApplicationService.getLeavesByAssignedId(leaveId).isEmpty()){
            return fileStorageService.load(leaveId);
//        }
//        else return ResponseEntity.ok(null);
    }
}
