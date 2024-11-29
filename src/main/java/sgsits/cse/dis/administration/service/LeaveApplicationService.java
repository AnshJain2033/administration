package sgsits.cse.dis.administration.service;

import org.springframework.web.multipart.MultipartFile;
import sgsits.cse.dis.administration.model.Leave;
import sgsits.cse.dis.administration.request.LeaveRequestForm;
import sgsits.cse.dis.administration.response.LeaveApplicationResponse;

import java.util.Date;
import java.util.List;


public interface LeaveApplicationService {
    public String getDescriptionByLeaveId(String leaveId);
    public Leave getLeaveDetails(String leaveId);
    public String checkLeaveStatus(String leaveId);
    public Date getLeaveStartDate(String leaveId);
    public Date getLeaveEndDate(String leaveId);
    public LeaveApplicationResponse createLeaveApplcation(LeaveRequestForm leaveApplication);
    public List<Leave> getAllLeavesOfAStudent(String studentId);
    public String deleteLeaveByLeaveId(String leaveId);
    public String putAssignedToByStudentId(String studentId,String assignedTo);
    public String putLeaveStatusByLeaveId(String status,String leaveId);
    public List<Leave> getLeavesByAssignedId(String assignedId);
    public void postLeaveSupportingDocument(String leaveId,MultipartFile file);
}
