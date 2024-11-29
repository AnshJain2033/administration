package sgsits.cse.dis.administration.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

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
