package jebi.hendardi.universityjebi.repository;

import jebi.hendardi.universityjebi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
