package sgsits.cse.dis.leaveService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Table(name="student")
@AllArgsConstructor
@Entity
public class Student {
    @Id
    public String id;
    public String studentName;
    public String enrollmentNumber;
    public String facultyAssigned;
}
