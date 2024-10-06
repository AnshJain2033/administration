package sgsits.cse.dis.leaveService.implementation;

import sgsits.cse.dis.leaveService.model.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sgsits.cse.dis.leaveService.repo.LeaveRepository;
import sgsits.cse.dis.leaveService.repo.StudentRepository;
import sgsits.cse.dis.leaveService.request.LeaveRequestForm;
import sgsits.cse.dis.leaveService.response.LeaveApplicationResponse;
import sgsits.cse.dis.leaveService.service.LeaveApplicationService;

import java.util.Date;
import java.util.List;

@Service("leaveService")
public class LeaveServiceImplementation implements LeaveApplicationService {
    @Autowired
    LeaveRepository leaveRepository;
    @Autowired
    StudentRepository studentRepository;
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
        Leave leave=new Leave();

        if(leaveApplication.getEndDate().compareTo(leaveApplication.getStartDate())>0){
            String description = leaveApplication.getDescription();
            String subject =leaveApplication.getSubject();
            Date startDate =leaveApplication.getStartDate();
            Date endDate =leaveApplication.getEndDate();
            String studentId =leaveApplication.getStudentId();

            leave.setDescription(description);
            leave.setSubject(subject);
            leave.setStartDate(startDate);
            leave.setEndDate(endDate);
//            leave.setStudentID(studentId);
            leaveRepository.save(leave);
            LeaveApplicationResponse response =new LeaveApplicationResponse();
            response.setLeave(leave);
            response.setMessage("Applied For Leave Successfully");
            return response;
        }else{
            LeaveApplicationResponse response =new LeaveApplicationResponse();
            response.setLeave(null);
            response.setMessage("Error Creating a Leave");
            return response;
        }
    }

    @Override
    public List<Leave> getAllLeavesOfAStudent(String studentId) {
        List<Leave>studentLeaves = leaveRepository.findLeaveByStudentId(studentId).stream().toList();
        if(!studentLeaves.isEmpty()){
            return studentLeaves;
        }
        return null;
    }
}
