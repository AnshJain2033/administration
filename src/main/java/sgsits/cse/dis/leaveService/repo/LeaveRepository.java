package sgsits.cse.dis.leaveService.repo;

import org.springframework.data.repository.query.Param;
import sgsits.cse.dis.leaveService.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,String> {
    List<Leave> findLeaveByStudentId(@Param("studentId") String studentId);
}
