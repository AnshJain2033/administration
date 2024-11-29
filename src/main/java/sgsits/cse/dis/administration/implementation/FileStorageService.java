package sgsits.cse.dis.administration.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sgsits.cse.dis.administration.model.FileStorage;
import sgsits.cse.dis.administration.model.Leave;
import sgsits.cse.dis.administration.model.Student;
import sgsits.cse.dis.administration.repo.FileStorageRepository;
import sgsits.cse.dis.administration.repo.LeaveRepository;
import sgsits.cse.dis.administration.repo.StudentRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileStorageService implements sgsits.cse.dis.administration.service.FileStorageService{
//    private final Path root = Paths.get("uploads");
    @Autowired
    LeaveRepository leaveRepository;
    @Autowired
    FileStorageRepository fileStorageRepository;
    private SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private StudentRepository studentRepository;

    @Value("${file.upload.base.path}")
    private String baseUploadPath;
//    @Override
//    public void init()
//    {
//        try
//        {
//            if(!Files.exists(root))
//                Files.createDirectory(root);
//        } catch (IOException e)
//        {
//            throw new RuntimeException("Could not initialize upload directory.");
//
//        }
//    }
    @Override
    public void saveFile(String leaveId, MultipartFile file) throws IOException {

        Optional<Leave> leaveOptional = leaveRepository.findById(leaveId);
        if (leaveOptional.isEmpty()) {
            throw new IllegalArgumentException("Leave not found");
        }

        Leave leave = leaveOptional.get();
        Student student = studentRepository.findById(leave.getStudentId()).get();
        System.out.println(student);
        if (student == null) {
            throw new IllegalArgumentException("Student not found");
        }

        String fileName = student.getStudentName() + "_" + sdf3.format(leave.getStartDate()) + "_" + UUID.randomUUID() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filePath = baseUploadPath + "/" + fileName;

        try {
            File uploadedFile = new File(filePath);
            uploadedFile.getParentFile().mkdirs();
            file.transferTo(uploadedFile);

            FileStorage fileStorage = new FileStorage();
            fileStorage.setFileId(UUID.randomUUID().toString());
            fileStorage.setLeaveId(leave.getId());
            fileStorage.setFileType(file.getContentType());
            fileStorage.setFilePath(filePath);
            // Save fileStorage to the database
            fileStorageRepository.save(fileStorage);
        } catch (IOException e) {
            // Handle file I/O exceptions
            System.out.println("Error saving file: {}"+ e.getMessage());
            throw new IOException("Error saving file", e);
        }
    }

//        try
//        {
//            if(!leave.isEmpty()){
//                Files.copy(file.getInputStream(), this.root.resolve(leave.get().getId()+ ".pdf"));
//            }
//        } catch(Exception e)
//        {
//            throw new RuntimeException("Could not upload the file. Error: "+e.getMessage());
//        }



    @Override
    public ResponseEntity<? extends Object> load(String leaveId) {
        try {
            File file = new File("/home/uploads/" + leaveId);
            // Check if the file exists
            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }
            // Read file content as byte array
            byte[] content = Files.readAllBytes(file.toPath());
//            Path file = root.resolve(leaveId);
//            Resource resource = new UrlResource(file.toUri());
//
//            if (resource.exists() || resource.isReadable()) {
//                return resource;
//            } else {
//                throw new RuntimeException("Could not read the file!");
//            }
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(content);
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
