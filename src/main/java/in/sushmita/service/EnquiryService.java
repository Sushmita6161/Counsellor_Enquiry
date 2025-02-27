package in.sushmita.service;

import in.sushmita.binding.SearchCriteria;
import in.sushmita.entity.StudentEnq;

import java.util.List;

public interface EnquiryService {

    public boolean addEnq(StudentEnq se);

    public List<StudentEnq> getEnquiries(Integer cid, SearchCriteria s);
}
