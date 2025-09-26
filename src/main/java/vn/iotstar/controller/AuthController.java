package vn.iotstar.controller;

import vn.iotstar.entity.User;
import vn.iotstar.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
  private final UserRepository userRepo;
  public AuthController(UserRepository userRepo){ this.userRepo = userRepo; }

  @GetMapping("/login") public String loginPage(){ return "login"; }

  @PostMapping("/login")
  public String login(HttpServletRequest request, @RequestParam String email, @RequestParam String password){
    User u = userRepo.findByEmail(email).orElse(null);
    if(u!=null && u.getPassword().equals(password)){
      HttpSession session = request.getSession();
      session.setAttribute("user", u);
      return "redirect:/ui/products";
    }
    request.setAttribute("error", "Sai tài khoản hoặc mật khẩu");
    return "login";
  }

  @GetMapping("/register") public String registerPage(){ return "register"; }

  @PostMapping("/register")
  public String register(@RequestParam String fullname, @RequestParam String email,
                         @RequestParam String password, @RequestParam String phone){
    User u = new User();
    u.setFullname(fullname); u.setEmail(email); u.setPassword(password); u.setPhone(phone);
    userRepo.save(u);
    return "redirect:/login";
  }

  @GetMapping("/logout")
  public String logout(HttpServletRequest request){
    request.getSession().invalidate();
    return "redirect:/login";
  }
}
