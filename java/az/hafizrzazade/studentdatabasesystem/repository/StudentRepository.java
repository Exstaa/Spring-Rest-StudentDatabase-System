package az.hafizrzazade.studentdatabasesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.hafizrzazade.studentdatabasesystem.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
