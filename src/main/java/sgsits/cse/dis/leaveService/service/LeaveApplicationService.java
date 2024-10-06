package sgsits.cse.dis.leaveService.service;

import sgsits.cse.dis.leaveService.model.Leave;
import sgsits.cse.dis.leaveService.request.LeaveRequestForm;
import sgsits.cse.dis.leaveService.response.LeaveApplicationResponse;

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

}
