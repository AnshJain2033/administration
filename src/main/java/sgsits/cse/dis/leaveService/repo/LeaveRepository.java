package sgsits.cse.dis.leaveService.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import sgsits.cse.dis.leaveService.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,String> {
    @Query("SELECT leave FROM Leave leave WHERE leave.studentId = :studentId")
    List<Leave> findLeaveByStudentId(@Param("studentId") String studentId);

    @Modifying
    @Query("UPDATE Leave leave SET leave.assignedTo = :assignedTo WHERE leave.studentId = :studentId")
    void putAssignedToByStudentId(@Param("assignedTo")String assignedTo,@Param("studentId")String studentId);
    @Modifying
    @Query("UPDATE Leave leave SET leave.status =:status WHERE leave.id =:id")
    void putLeaveStatusByLeaveId(@Param("status")String status,@Param("id")String id);

    @Query("SELECT leave FROM Leave leave WHERE leave.assignedTo =:assignedTo AND leave.status = 'PENDING'")
    List<Leave> findLeaveByAssignedId(@Param("assignedTo") String assignedTO);
}
