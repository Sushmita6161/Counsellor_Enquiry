package in.sushmita.repo;

import in.sushmita.entity.Counsellor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounsellorRepo extends JpaRepository<Counsellor,Integer> {

    public  Counsellor findByEmail(String email);
    public Counsellor findByEmailAndPwd(String email,String pwd);
}
