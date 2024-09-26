package com.example.demo.controller;

import java.util.List;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Dia_Diem;
import com.example.demo.entity.Nhan_Vien;
import com.example.demo.entity.User;
import com.example.demo.entity.Ve_Xe;
import com.example.demo.service.ChuyenXeService;
import com.example.demo.service.DiaDiemService;
import com.example.demo.service.TuyenXeService;
import com.example.demo.service.UserService;
import com.example.demo.service.VeXeService;


@Slf4j
@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @Autowired
    DiaDiemService diaDiemService;

    @Autowired
    TuyenXeService tuyenXeService;

    @Autowired
    ChuyenXeService chuyenXeService;

    @Autowired
    VeXeService veXeService;

    public void addCurrentUserFromSessionToModel(HttpServletRequest request, Model model) {
        User tempUser = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", tempUser);
    }
    public void addCurrentNhanVienFromSessionToModel(HttpServletRequest request, Model model) {
        Nhan_Vien tempNV = (Nhan_Vien) request.getSession().getAttribute("nhanVien");
        model.addAttribute("nhanVien", tempNV);
    }

    // Test chi tiết vé
    @GetMapping("/chiTietVe")
    public String chiTietVePage(@RequestParam("veId") int idVe, HttpServletRequest request, Model model) {
        if (request.isUserInRole("ROLE_USER")) {
            addCurrentUserFromSessionToModel(request, model);
        } else if (request.isUserInRole("ROLE_EMPLOYEE")) {
            addCurrentNhanVienFromSessionToModel(request, model);
        }
        Ve_Xe veXe = veXeService.getVeXe(idVe);
        /// lấy param id ve xe
        model.addAttribute("veXe", veXe);
        return "chitietve";

    }


    @RequestMapping(value = {"/", "/user"}, method = RequestMethod.GET)
    public String getHomepage(HttpServletRequest request, Model model) {
        log.info("IN HOME /");
        // Nếu lần đầu người dùng vào trang thì lấy tất cả địa điểm trong database bỏ vào điểm đi và điểm đến
        List<Dia_Diem> tatCaDiaDiem = (List<Dia_Diem>) request.getSession().getAttribute("tatCaDiaDiem");
        // lần đầu vào thì lấy tất cả địa điểm bỏ vào session
        if (tatCaDiaDiem == null || tatCaDiaDiem.isEmpty()) {
            tatCaDiaDiem = diaDiemService.tatCaDiaDiem();
            request.getSession().setAttribute("tatCaDiaDiem", tatCaDiaDiem);
        }

        // hien thong tin nguoi dang nhap
        User tempUser = (User) request.getSession().getAttribute("user");
        if (tempUser != null) {
            log.info("user: " + tempUser.getHoTen());
            model.addAttribute("user", tempUser);
        } else {
            Nhan_Vien tempNV = (Nhan_Vien) request.getSession().getAttribute("nhanVien");
            model.addAttribute("nhanVien", tempNV);
        }

        model.addAttribute("tatCaDiaDiem", tatCaDiaDiem);
        return "index";
    }


}