package com.shj.eids.controller.admin;

import com.shj.eids.domain.Admin;
import com.shj.eids.domain.EpidemicEvent;
import com.shj.eids.service.AdminService;
import com.shj.eids.service.EpidemicEventService;
import com.shj.eids.service.EpidemicInfoService;
import com.shj.eids.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: AdminCotroller
 * @Description:
 * @Author: ShangJin
 * @Create: 2020-03-22 14:04
 **/
@Controller
public class AdminCotroller {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private EpidemicInfoService infoService;
    @Autowired
    private EpidemicEventService epidemicEventService;

    @RequestMapping("/admin/patientinfo/{event}")
    public String toPatientInfoPage(@PathVariable("event") String eventName, Model model){
        EpidemicEvent event = epidemicEventService.getEpidemicEventByName(eventName);
        if(event == null){
            return "redirect:admin/adminindex";
        }
        model.addAttribute("event", event);
        model.addAttribute("provinces", EpidemicInfoService.provinces);
        return "admin/patientinfo";
    }

    @RequestMapping("/admin/adminManagement")
    public String toAdminManagementPage(HttpSession session){
        try {
            Admin admin = (Admin) session.getAttribute("loginAccount");
            if(admin.getLevel() <= 1){
                return "forward:/admin";
            }else{
                return "admin/adminManagement";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "forward:/admin";
        }
    }
}