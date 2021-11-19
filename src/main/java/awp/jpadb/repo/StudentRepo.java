package awp.jpadb.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import awp.jpadb.data.DtoStudentSchool;
import awp.jpadb.data.Student;

/**
 * This repository for default student database requesta but also
 * containtin custom native requests.
 */

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>{
    
    final String query1 = "SELECT * FROM student WHERE name LIKE %?1%";

    final String query2 = 
        "SELECT student.name as studentName FROM student JOIN school ON student.school_id=school.id";

    final String query3 = "INSERT INTO student (name, school_id) VALUES (?1, ?2)";

    @Query(value = query1, nativeQuery = true)
    List<Student> getStudentWithText(String text);

    @Query(value = query2, nativeQuery = true)
    List<DtoStudentSchool> getStudentSchools();

    //Modifying commands should have two special tags (otherwise errors)

    @Transactional
    @Modifying
    @Query(value = query3, nativeQuery = true)
    public void insertStudent(String name, Long schoolId);


}
