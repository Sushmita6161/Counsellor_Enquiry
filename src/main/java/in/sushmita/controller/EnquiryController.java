package in.sushmita.controller;


import in.sushmita.binding.SearchCriteria;
import in.sushmita.entity.StudentEnq;
import in.sushmita.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EnquiryController {

    @Autowired
    private EnquiryService enqService;

    @GetMapping("/enquiry")
    public String enqPage(Model model)
    {
        model.addAttribute("enq",new StudentEnq());
        return "addEnqView";
    }

    @PostMapping("/enquiry")
    public String addEnquiry(@ModelAttribute("enq") StudentEnq enq, HttpServletRequest req, Model model)
    {
        HttpSession session = req.getSession(false);
        Integer cid =(Integer) session.getAttribute("CID");

        if(cid == null)
        {
            return "redirect:/logout";
        }
        enq.setCid(cid);
        boolean addEnq = enqService.addEnq(enq);
        if(addEnq)
        {
            model.addAttribute("succMsg","Enquiry Added");
        }
        else
        {
            //error msg
            model.addAttribute("errMsg","Enquiry Failed To Add");
        }
        return "addEnqView";
    }

    @GetMapping("/enquiries")
    public String viewEnquiries(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(false);
        SearchCriteria sc = new SearchCriteria(); // ✅ Create a new SearchCriteria
        model.addAttribute("sc", sc);

        if (session == null) {
            System.out.println("Session is NULL ❌");
        } else {
            Integer cid = (Integer) session.getAttribute("CID");
            System.out.println("Fetched CID from session: " + cid); // ✅ Debug CID

            if (cid == null) {
                System.out.println("CID is NULL ❌");
            } else {
                List<StudentEnq> enquiriesList = enqService.getEnquiries(cid, new SearchCriteria());  // ✅ Pass a valid object
                model.addAttribute("enquiries", enquiriesList);
                return "displayEnqView";
            }
        }

        return "redirect:/login"; // Redirect user to login if CID is missing
    }


    @PostMapping("/filter-enquiries")
    public String filterEnquiries(@ModelAttribute("sc") SearchCriteria sc,HttpServletRequest req, Model model)
    {
        HttpSession session = req.getSession(false);
        Integer cid = (Integer) session.getAttribute("CID");

        if(cid == null)
        {
            return "redirect:/";
        }

        List<StudentEnq> enquiriesList =enqService.getEnquiries(cid,sc);

        model.addAttribute("enquiries",enquiriesList);


        return "filterEnqView";
    }
}
