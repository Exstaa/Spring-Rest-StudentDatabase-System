package az.hafizrzazade.studentdatabasesystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.hafizrzazade.studentdatabasesystem.model.Student;
import az.hafizrzazade.studentdatabasesystem.repository.StudentRepository;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/students")
public class StudentController {

	// CRUD (Create,Remove,Update,Delete) operations.

	@Autowired
	private StudentRepository studentRepo;

	@PostMapping("/addStudent")
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		student.setStudentId(0);
		Student resultStudent = studentRepo.save(student);
		return new ResponseEntity<Student>(resultStudent, HttpStatus.CREATED);
	}

	@GetMapping({ "", "/" })
	public ResponseEntity<List<Student>> getAllStudents() {
		return new ResponseEntity<List<Student>>(studentRepo.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
		Optional<Student> resultStudent = studentRepo.findById(id);
		return new ResponseEntity<Student>(resultStudent.get(), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Student> deleteStudentById(@PathVariable Integer id) {
		Optional<Student> resultStudent = studentRepo.findById(id);
		if(!resultStudent.isEmpty()) {
		studentRepo.deleteById(id);
		}
		return new ResponseEntity<Student>(resultStudent.get(), HttpStatus.ACCEPTED);
	}

	@PutMapping("/edit")
	public ResponseEntity<HashMap<String, String>> editStudent(@RequestBody Student student) {
		HashMap<String, String> responseMap = new HashMap<>();
		responseMap.put("message", "student id " + student.getStudentId() + " has been edited");
		Integer id = student.getStudentId();
		if (id != null) {
			studentRepo.save(student);
		}

		return new ResponseEntity<>(responseMap, HttpStatus.ACCEPTED);
	}

}
