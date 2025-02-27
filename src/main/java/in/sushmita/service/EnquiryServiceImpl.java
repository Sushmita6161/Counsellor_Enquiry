package in.sushmita.service;

import in.sushmita.binding.SearchCriteria;
import in.sushmita.entity.StudentEnq;
import in.sushmita.repo.StudentEnqRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnquiryServiceImpl implements EnquiryService{

    @Autowired
    private StudentEnqRepo srepo;

    @Override
    public boolean addEnq(StudentEnq se) {

        StudentEnq savedEnq=srepo.save(se);

        return savedEnq.getEnqId() != null;
    }

    @Override
    public List<StudentEnq> getEnquiries(Integer cid, SearchCriteria sc) {
        StudentEnq enq = new StudentEnq();

        //setting cid
        enq.setCid(cid);

        //if mode is selected add to query
        if(sc.getClassMode() !=null && !sc.getClassMode().equals(""))
        {
            enq.setClassMode(sc.getClassMode());
        }

        if(sc.getCourseName() !=null && !sc.getCourseName().equals(""))
        {
            enq.setCourseName(sc.getCourseName());
        }

        if(sc.getEnqStatus() !=null && !sc.getEnqStatus().equals(""))
        {
            enq.setEnqStatus(sc.getEnqStatus());
        }

        Example<StudentEnq> of =Example.of(enq);

        List<StudentEnq> enquiries = srepo.findAll(of);
        return enquiries;
    }
}
