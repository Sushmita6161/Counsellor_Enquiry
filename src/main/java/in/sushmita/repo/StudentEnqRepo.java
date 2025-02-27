package in.sushmita.repo;

import in.sushmita.entity.StudentEnq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentEnqRepo extends JpaRepository<StudentEnq,Integer> {

    public List<StudentEnq> findByCid(Integer cid);
}
