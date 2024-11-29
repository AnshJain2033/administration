package sgsits.cse.dis.administration.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
//    public void init();
    public void saveFile(String leaveId, MultipartFile file) throws Exception;
    public ResponseEntity<?> load(String leaveId);
}
