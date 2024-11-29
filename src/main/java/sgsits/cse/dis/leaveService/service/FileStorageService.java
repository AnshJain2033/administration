package sgsits.cse.dis.leaveService.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {
//    public void init();
    public void saveFile(String leaveId, MultipartFile file) throws Exception;
    public ResponseEntity<?> load(String leaveId);
}
