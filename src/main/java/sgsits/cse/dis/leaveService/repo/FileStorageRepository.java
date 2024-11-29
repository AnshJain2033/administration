package sgsits.cse.dis.leaveService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sgsits.cse.dis.leaveService.model.FileStorage;

public interface FileStorageRepository extends JpaRepository<FileStorage,String> {

}
