package az.hafizrzazade.studentdatabasesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.hafizrzazade.studentdatabasesystem.model.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{

}
