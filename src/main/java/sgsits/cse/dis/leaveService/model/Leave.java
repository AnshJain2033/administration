package sgsits.cse.dis.leaveService.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
@Table(name="leaveTable")
@Entity
public class Leave {
    @Id
    public String id;
    public Date startDate;
    public Date endDate;
    public String description;
    public String subject;
    @Column(name = "studentId")
    public String studentId;
    @Column(name = "status")
    public String status;
    @Column(name = "assignedTo")
    public String assignedTo;
    @Column(name = "fileId")
    public String fileId;
}