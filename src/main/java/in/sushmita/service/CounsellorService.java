package in.sushmita.service;

import in.sushmita.binding.DashboardResponse;
import in.sushmita.entity.Counsellor;

public interface CounsellorService {

    public String saveCounsellor(Counsellor c);

    public Counsellor loginCheck(String email, String pwd);

    public boolean recoverPwd(String email);

    public DashboardResponse getDashboardInfo(Integer cid);
}
