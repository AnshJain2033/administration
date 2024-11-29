package sgsits.cse.dis.administration.response;

import lombok.Data;
import sgsits.cse.dis.administration.model.Leave;

@Data
public class LeaveApplicationResponse {
    public Leave leave;
//    public String status;
    public String message;
}
