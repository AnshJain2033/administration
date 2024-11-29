package sgsits.cse.dis.leaveService.implementation;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sgsits.cse.dis.leaveService.model.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sgsits.cse.dis.leaveService.repo.LeaveRepository;
import sgsits.cse.dis.leaveService.repo.StudentRepository;
import sgsits.cse.dis.leaveService.request.LeaveRequestForm;
import sgsits.cse.dis.leaveService.response.LeaveApplicationResponse;
import sgsits.cse.dis.leaveService.service.LeaveApplicationService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("leaveService")
public class LeaveServiceImplementation implements LeaveApplicationService {
    @Autowired
    LeaveRepository leaveRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    FileStorageService fileStorageService;


    @Override
    public String getDescriptionByLeaveId(String leaveId) {
        String description = leaveRepository.findById(leaveId).get().getDescription();
        return description;
    }

    @Override
    public Leave getLeaveDetails(String leaveId) {
        Leave leave = leaveRepository.findById(leaveId).get();
        return leave;
    }

    @Override
    public String checkLeaveStatus(String leaveId) {
        String checkStatus = leaveRepository.findById(leaveId).get().getStatus();
        return checkStatus;
    }

    @Override
    public Date getLeaveStartDate(String leaveId) {
        Date startDate = leaveRepository.findById(leaveId).get().getStartDate();
        return startDate;
    }

    @Override
    public Date getLeaveEndDate(String leaveId) {
        Date endDate = leaveRepository.findById(leaveId).get().getEndDate();
        return endDate;
    }

    @Override
    public LeaveApplicationResponse createLeaveApplcation(LeaveRequestForm leaveApplication) {
        Leave leave = new Leave();

        if (leaveApplication.getEndDate().compareTo(leaveApplication.getStartDate()) > 0) {
            String description = leaveApplication.getDescription();
            String subject = leaveApplication.getSubject();
            String startDate = leaveApplication.getStartDate();
            String endDate = leaveApplication.getEndDate();
            String studentId = leaveApplication.getStudentId();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                leave.setStartDate(formatter.parse(startDate));
                leave.setEndDate(formatter.parse(endDate));
            } catch (Exception e) {
            }
            leave.setDescription(description);
            leave.setSubject(subject);
            leave.setStudentId(studentId);
            leave.setAssignedTo("");
            leave.setStatus("PENDING");
            try {
                leave.setId(UUID.randomUUID().toString());
                leaveRepository.save(leave);

                LeaveApplicationResponse response = new LeaveApplicationResponse();
                response.setLeave(leave);
                response.setMessage("Applied For Leave Successfully");
                return response;
            } catch (Exception e) {

                LeaveApplicationResponse response = new LeaveApplicationResponse();
                response.setLeave(null);
                response.setMessage("Exception Occurred "+e);
                return response;
            }

        } else {
            LeaveApplicationResponse response = new LeaveApplicationResponse();
            response.setLeave(null);
            response.setMessage("Error Creating a Leave");

            return response;
        }
    }

    @Override
    public List<Leave> getAllLeavesOfAStudent(String studentId) {
        List<Leave> studentLeaves = leaveRepository.findLeaveByStudentId(studentId).stream().toList();
        if (!studentLeaves.isEmpty()) {
            return studentLeaves;
        }
        return null;
    }

    public String deleteLeaveByLeaveId(String leaveId) {
        if (!leaveRepository.findById(leaveId).isEmpty()) {
            leaveRepository.deleteById(leaveId);
            return "Deleted Successfully";
        } else return "Error Deleting the Leave";
    }

    @Transactional
    public String putAssignedToByStudentId(String studentId, String assignedTo) {
        leaveRepository.putAssignedToByStudentId(assignedTo, studentId);
            return "Assigned Successfully";
    }
    @Transactional
    public String putLeaveStatusByLeaveId (String status,String leaveId){
        leaveRepository.putLeaveStatusByLeaveId(status,leaveId);
        return "Status Updated Successfully";
    }
    public List<Leave> getLeavesByAssignedId(String assignedId){
        if(!leaveRepository.findLeaveByAssignedId(assignedId).isEmpty()){
            return leaveRepository.findLeaveByAssignedId(assignedId);
        }
        else return null;
    }

    public void postLeaveSupportingDocument(String leaveId,MultipartFile file){
        Optional<Leave> leave = leaveRepository.findById(leaveId);

            try{

                fileStorageService.saveFile(leave.get().getId(),file);
            }catch(Exception e){
                System.out.println("Exception Occurred in file upload"+e.getMessage());
            }


    }

}
