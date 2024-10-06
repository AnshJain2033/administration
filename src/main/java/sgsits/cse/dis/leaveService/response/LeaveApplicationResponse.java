package sgsits.cse.dis.leaveService.response;

import lombok.Data;
import sgsits.cse.dis.leaveService.model.Leave;

@Data
public class LeaveApplicationResponse {
    public Leave leave;
    public String status;
    public String message;
}
