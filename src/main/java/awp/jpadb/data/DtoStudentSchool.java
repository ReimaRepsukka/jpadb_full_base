package awp.jpadb.data;

/**
 * This is custom dto for gathering the query information
 */

public interface DtoStudentSchool {
    String getStudentName();  //studentName --> has to match the query result column name
    String getSchoolName(); //schoolName --> has to match the query result column name
}
