package awp.jpadb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import awp.jpadb.data.DtoStudentSchool;
import awp.jpadb.data.School;
import awp.jpadb.data.Student;
import awp.jpadb.repo.SchoolRepo;
import awp.jpadb.repo.StudentRepo;

@SpringBootApplication
@RestController
public class JpadbApplication {

	@Autowired
	StudentRepo studentRepo;

	@Autowired
	SchoolRepo schoolRepo;
	
	public static void main(String[] args) {
		
		SpringApplication.run(JpadbApplication.class, args);
	}

	@PostConstruct
	public void init(){

		//Example of updating existing school
		//(Creating new school without id would insert a new row in db)
		School s = schoolRepo.findById(1L).get();
		s.city = "Rovaniemi";
		schoolRepo.save(s);

		//Inserting a new student by using custom repo method
		//studentRepo.insertStudent("Mattila Mukkula", 2L);
	}

	@GetMapping("schools")
	public List<School> getSchools(){
		return schoolRepo.findAll();
	}

	@GetMapping("students")
	public List<Student> getStudents(){
		return studentRepo.findAll();
	}

	/**
	 * This one finds the custom information by using objects and basic queries from repos.
	 * Map may be used to gather information to form a custom JSON response.
	 * (You can also return the map in ResponseEntity) 
	 */
	@GetMapping("student")
	public Map<String,String> getStudentInfo(@PathVariable Long id){

		Student student = studentRepo.findById(id).get();
		School school = schoolRepo.findById( student.schoolId ).get();

		Map<String,String> info = new HashMap<>();
		info.put("student_name", student.name);
		info.put("school_namme", school.name);

		return info;
	}


	/**
	 * Using custom repo method for getting students by text in name
	 */
	@GetMapping("student/{text}")
	public List<Student> getByText(@PathVariable String text){
		return studentRepo.getStudentWithText(text);
	}

	/**
	 * Using custom DTO object for gathering column information of custom native query.
	 * @return
	 */
	@GetMapping("studentschools")
	public ResponseEntity<List<DtoStudentSchool>> getStudentSchools(){

		List<DtoStudentSchool> resp = studentRepo.getStudentSchools();
		
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}


	// @GetMapping("custom")
	// public List<Map<String,String>> getCustomInfo(){

	// 	//Get just first school and student name as first example

	// 	//Get all studets with default repo method
	// 	List<Student> students = studentRepo.findAll();

	// 	//Create result list. Each map will contain one JSON object (key-value pairs)
	// 	List<Map<String,String>> infos = new ArrayList<>();

	// 	for (Student student : students) {

	// 		//Creating map for the student
	// 		Map<String, String> studentInfo = new HashMap<>();

	// 		//Put e.g. "student":"Reima" json value
	// 		studentInfo.put("student", student.name);

	// 		//Get the school by student's school_id
	// 		School sch = schoolRepo.findById(student.schoolId).get();

	// 		//Put e.g. "city":"Oulu" json value.
	// 		studentInfo.put("city", sch.city );

	// 		//Add the student map to list
	// 		infos.add(studentInfo);
	// 	}

	// 	//Return the list of values (array of json objects)
	// 	return infos;
	// }

}
