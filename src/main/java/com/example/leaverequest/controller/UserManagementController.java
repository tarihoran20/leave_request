package com.example.leaverequest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.leaverequest.dto.LoginDTO;
import com.example.leaverequest.dto.RegisterDTO;
import com.example.leaverequest.models.Employee;
import com.example.leaverequest.models.Role;
import com.example.leaverequest.models.User;
import com.example.leaverequest.services.DivisionService;
import com.example.leaverequest.services.EmployeeService;
import com.example.leaverequest.services.RoleService;
import com.example.leaverequest.services.UserService;

@Controller
@RequestMapping("usermanagement")
public class UserManagementController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private DivisionService divisionService;

    @Autowired
    public UserManagementController(
        AuthenticationManager authenticationManager,
        UserService userService,
        RoleService roleService,
        EmployeeService employeeService,
        PasswordEncoder encoder){
            this.authenticationManager = authenticationManager;
            this.userService = userService;
            this.roleService = roleService;
            this.employeeService = employeeService;
            this.encoder = encoder;

    }

    // Register
    @GetMapping(value = {"register"})
    public String register(Model model) {

        model.addAttribute("register", new RegisterDTO());
        model.addAttribute("divisions", divisionService.getAll());

        return "usermanagement/register";
    }
    
    @PostMapping("save")
    public String save(RegisterDTO registerDTO){
        Employee employee = new Employee();
        employee.setNik(registerDTO.getNik());
        employee.setFullname(registerDTO.getFullname());
        employee.setEmail(registerDTO.getEmail());
        employee.setPhone_number(registerDTO.getPhone_number());
        employee.setAddress(registerDTO.getAddress());
        employee.setJoin_date(registerDTO.getJoin_date());
        employee.setRemaining_leave(registerDTO.getRemaining_leave());
        employee.setDivision(registerDTO.getDivision());
        Boolean result = employeeService.save(employee);

        User user = new User();
        // user.setId(employee.getId());
        user.setId(employeeService.getIdByEmail(employee.getEmail()));

        Role role = new Role();
        role.setId(roleService.getLevelById());
        user.setRole(role);
        user.setPassword(encoder.encode(registerDTO.getPassword()));

        Boolean result2 = userService.save(user);
      
        if(result && result2){
            return "redirect:/employee";
        } else {
            return "usermanagement/register";
        }
    }

    @GetMapping(value = {"login"})
    public String login(Model model) {
        // LoginDTO loginDTO = new LoginDTO();
        model.addAttribute("login", new LoginDTO());

        return "usermanagement/login";
    }

    @PostMapping(value = {"loginconfirm"})
    public String loginconfirm(LoginDTO dataLogin, Model model){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dataLogin.getEmail(), dataLogin.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        
            model.addAttribute("users", userService.getById(employeeService.getIdByEmail(dataLogin.getEmail())));
        
        // return "redirect:/usermanagement/dashboard";
        return "dashboard/dashboard";
    }

    @GetMapping(value = {"forgotpassword"})
    public String forgotpassword(Model model){
        model.addAttribute("employee", new Employee());
        return "usermanagement/forgotpassword";
    }

    @PostMapping("forgotpassword/submitEmail")
    public String forgetPasswordSubmitEmail(Employee e){
        Integer x = employeeService.getIdEmployeeByEmail(e.getEmail());
        if(x!=null){
            return "redirect:/usermanagement/forgotpassword/employeeId/"+x;
        }else{
            return "usermanagement/forgotpassword";
        }
    }

    @GetMapping("forgotpassword/employeeId/{Id}")
    public String forgetPasswordChange(@PathVariable (required=true) Integer Id, Model model){
        model.addAttribute("id", Id);
        model.addAttribute("user", new User());
        return "usermanagement/forgotpassword2";
    }


    @GetMapping(value = {"changePassword/{id}"})
    public String create(@PathVariable(required = false) Integer id, Model model){
        model.addAttribute("user", userService.getById(id));
        return "usermanagement/changePassword";
    }

    @PostMapping("forgetpassword/submitPassword")
    public String forgetPasswordSubmitPassword(User u) {
        userService.updatePasswordEmployee(u.getId(), encoder.encode(u.getPassword()));
        return "redirect:/usermanagement/login";
    }



    @GetMapping(value = {"dashboard"})
    public String dashboard(LoginDTO dataLogin, Model model){
        // model.addAttribute("users", userService.getAll());
        
        model.addAttribute("users", userService.getById(employeeService.getIdByEmail(dataLogin.getEmail())));
        return "dashboard/dashboard";
    }
    
}
