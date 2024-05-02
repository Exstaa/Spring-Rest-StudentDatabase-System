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

import az.hafizrzazade.studentdatabasesystem.model.Course;
import az.hafizrzazade.studentdatabasesystem.repository.CourseRepository;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/courses")
public class CourseController {
	// CRUD (Create,Remove,Update,Delete) operations.

	@Autowired
	private CourseRepository courseRepo;

	@PostMapping("/addCourse")
	public ResponseEntity<Course> addcourse(@RequestBody Course course) {
		course.setCourseId(0);
		Course resultCourse = courseRepo.save(course);
		return new ResponseEntity<Course>(resultCourse, HttpStatus.CREATED);
	}

	@GetMapping({ "", "/" })
	public ResponseEntity<List<Course>> getAllCourses() {
		return new ResponseEntity<List<Course>>(courseRepo.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
		Optional<Course> resultCourse = courseRepo.findById(id);
		return new ResponseEntity<Course>(resultCourse.get(), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Course> deleteCourseById(@PathVariable Integer id) {
		Optional<Course> resultCourse = courseRepo.findById(id);
		if(!resultCourse.isEmpty()) {
		courseRepo.deleteById(id);
		}
		return new ResponseEntity<Course>(resultCourse.get(), HttpStatus.ACCEPTED);
	}

	@PutMapping("/edit")
	public ResponseEntity<HashMap<String, String>> editCourse(@RequestBody Course course) {
		HashMap<String, String> responseMap = new HashMap<>();
		responseMap.put("message", "course id " + course.getCourseId() + " has been edited");
		Integer id = course.getCourseId();
		if (id != null) {
			courseRepo.save(course);
		}

		return new ResponseEntity<>(responseMap, HttpStatus.ACCEPTED);
	}
}
