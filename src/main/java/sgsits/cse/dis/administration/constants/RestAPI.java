package sgsits.cse.dis.administration.constants;

public class RestAPI {
    public static final String POST_LEAVE_APPLICATION = "/addLeave/{id}";
    public static final String GET_HELLO="/hello";
    public static final String GET_LEAVE_OF_A_STUDENT="/getLeaveByStudentId";
    public static final String DELETE_LEAVE_BY_LEAVE_ID="/deleteLeaveByLeaveId";
    public static  final String PUT_ASSIGNED_TO_BY_STUDENT_ID="/putAssignedToByStudentId";
    public static final String PUT_LEAVE_STATUS_BY_LEAVE_ID = "/putLeaveStatusByLeaveId";
    public static final String GET_LEAVE_BY_ASSIGNED_ID = "/getLeaveByAssignedToId";
    public static final String GET_LEAVE_SUPPORTING_DOCUMENTS = "/getLeaveSupportingDocumentById";
    public static final String POST_LEAVE_SUPPORTING_DOCUMENT ="/postLeaveSupportingDocumentById";
}
