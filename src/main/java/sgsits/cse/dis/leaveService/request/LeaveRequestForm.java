package sgsits.cse.dis.leaveService.request;

import lombok.Data;

import java.io.File;
import java.util.Date;
import java.util.List;

@Data
public class LeaveRequestForm {
    String subject;
    String description;
    String studentId;
    //    List<File> uploadedFiles;
    Date startDate;
    Date endDate;
}
