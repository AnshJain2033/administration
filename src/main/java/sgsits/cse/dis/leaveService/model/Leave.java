package sgsits.cse.dis.leaveService.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
@Table(name="leaveTable")
@Entity
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;
    public Date startDate;
    public Date endDate;
    public String description;
    public String subject;
    public String studentID;
    public String status;
}