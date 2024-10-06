package sgsits.cse.dis.leaveService.repo;

import sgsits.cse.dis.leaveService.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {

}
