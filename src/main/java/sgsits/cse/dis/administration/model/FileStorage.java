package sgsits.cse.dis.administration.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "file_uploaded")
public class FileStorage {
    @Id
    @Column(name ="file_id", nullable = false)
    private String fileId;
    @Column(name = "leave_id")
    private String leaveId;
    @Column(name = "file_type")
    private String fileType;
    @Column(name = "file_path")
    private String filePath;
}
