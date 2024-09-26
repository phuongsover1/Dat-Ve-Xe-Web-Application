package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Account;
import com.example.demo.entity.Nhan_Vien;
import com.example.demo.entity.User;
import com.example.demo.service.AccountService;
import com.example.demo.service.NhanVienService;
import com.example.demo.service.UserService;

@Slf4j
@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    NhanVienService nhanVienService;

    @Autowired
    AccountService accountService;

    @GetMapping("/showLoginPage")
    public String showLoginPage() {
        return "authentication/login";
    }

    @GetMapping("/showRegisterPage")
    public String showRegisterPage(Model model, HttpServletRequest request) {
        User newUser = new User();
        Account newAccount = new Account();
        model.addAttribute("newUser", newUser);
        model.addAttribute("newAccount", newAccount);

        // này làm đăng kí account nếu người dùng đã đăng kí vé rồi
        Account tempAccount = (Account) request.getSession().getAttribute("tempAccount");
        User tempUser = (User) request.getSession().getAttribute("tempUser");
        if (tempAccount != null && tempUser != null) {
            model.addAttribute("tempAccount", tempAccount);
            model.addAttribute("tempUser", tempUser);
        }

        return "authentication/register";
    }

    @GetMapping("/loginSuccessful")
    public String loginSuccessfulPage(HttpServletRequest request, Authentication authentication) {
        String currentPricipalName = authentication.getName();

        User tempUser = userService.getUserFromUsername(currentPricipalName);
        if (tempUser != null) {
            log.info("user: " + tempUser.getHoTen());
            request.getSession().setAttribute("user", tempUser);

            return "redirect:/";
        } else {
            Nhan_Vien tempNV = nhanVienService.getNhanVienFromUsername(currentPricipalName);
            request.getSession().setAttribute("nhanVien", tempNV);
            if (authentication.getAuthorities().stream().anyMatch(role -> role.toString().contains("ROLE_MANAGER"))) {
                return "redirect:/quanly";
            }
            return "redirect:/";
        }

    }

    @PostMapping("/saveNewUser")
    public String saveNewUser(HttpServletRequest request, @ModelAttribute("newUser") User user,
            @ModelAttribute("newAccount") Account account, Model model) {
        Account tempAccount = accountService.getAccountFromUsername(account.getUsername());
        User tempUser = userService.getUserFromUsername(account.getUsername());

        // nếu số điện thoại chưa từng đăng kí vé và chưa tạo tài khoản thì tạo tài
        // khoản mới + add thông tin user
        if (tempAccount != null) { // nếu account đã tồn tại thì trả về là số điện thoại đã tồn tại
            return "redirect:/showRegisterPage?tontaiTK";
        } else if (tempUser != null && tempAccount == null) { // số điện thoại từng đăng kí vé nhưng chưa tạo tài khoản
            request.getSession().setAttribute("tempAccount", account);
            request.getSession().setAttribute("tempUser", tempUser);
            return "redirect:/showRegisterPage?tontaiUser";
        } else {
            userService.saveUser(user, account);
            return "redirect:/user";
        }
    }

    @PostMapping("/saveNewAccount")
    public String saveNewAccount(HttpServletRequest request) {
        Account tempAccount = (Account) request.getSession().getAttribute("tempAccount");
        User tempUser = userService.getUserFromUsername(tempAccount.getUsername());
        userService.updateUserWithAccount(tempUser, tempAccount);
        request.getSession().removeAttribute("tempAccount");
        request.getSession().removeAttribute("tempUser");
        return "redirect:/user";
    }

}
