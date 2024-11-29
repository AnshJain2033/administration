package sgsits.cse.dis.leaveService.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@Data
public class LeaveRequestForm {
    String subject;
    String description;
    String studentId;
    //    List<File> uploadedFiles;
    String startDate;
    String endDate;
//    MultipartFile file;
}
