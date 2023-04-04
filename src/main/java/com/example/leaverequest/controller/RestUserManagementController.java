package com.example.leaverequest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.leaverequest.configuration.JWTTokenProvider;
import com.example.leaverequest.configuration.MyUserDetails;
import com.example.leaverequest.dto.LoginDTO;
import com.example.leaverequest.dto.RegisterDTO;
import com.example.leaverequest.models.Division;
import com.example.leaverequest.models.Employee;
import com.example.leaverequest.models.Role;
import com.example.leaverequest.models.User;
import com.example.leaverequest.services.AuthService;
import com.example.leaverequest.services.DivisionService;
import com.example.leaverequest.services.EmployeeService;
import com.example.leaverequest.services.RoleService;
import com.example.leaverequest.services.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/usermanagement")
public class RestUserManagementController {

    private EmployeeService employeeService;
    private RoleService roleService;
    private UserService userService;
    private PasswordEncoder encoder;
    private AuthenticationManager authenticationManager;
    private AuthService authService;
    private MyUserDetails userDetails;

    @Autowired
    public RestUserManagementController(
            AuthenticationManager authenticationManager,
            UserService userService,
            RoleService roleService,
            EmployeeService employeeService,
            DivisionService divisionService,
            PasswordEncoder encoder,
            AuthService authService,
            MyUserDetails userDetails) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleService = roleService;
        this.employeeService = employeeService;
        this.encoder = encoder;
        this.authService = authService;
        this.userDetails = userDetails;
        ;
    }

    // @GetMapping(value = {"register"})
    // public String create(Model model){
    // model.addAttribute("role_id", roleService.getidbylevel());
    // model.addAttribute("register", new RegisterDTO());
    // model.addAttribute("division_list", divisionService.getAll());
    // return "employee/register";
    // }

    @PostMapping("register")
    public ResponseEntity<?> save(@RequestBody RegisterDTO registerDTO) {
        boolean result, result2;
        Employee employee = new Employee();
        employee.setEmail(registerDTO.getEmail());
        employee.setNik(registerDTO.getNik());
        employee.setFullname(registerDTO.getFullname());
        employee.setJoin_date(registerDTO.getJoin_date());
        employee.setPhone_number(registerDTO.getPhone_number());
        employee.setAddress(registerDTO.getAddress());
        employee.setRemaining_leave(12); // leave

        Division division = new Division();
        division.setId(registerDTO.getDivision_id());
        employee.setDivision(division);

        result = employeeService.save(employee);

        User user = new User();
        Role role = new Role();

        user.setId(employeeService.getIdByEmail(registerDTO.getEmail()));
        role.setId(roleService.getLevelById());
        user.setRole(role);

        // user.setId(employeeService.getIdEmployeeFromEmail(register.getEmail()));
        user.setPassword(encoder.encode(registerDTO.getPassword()));
        // employee.setUser(user);

        result2 = userService.save(user);

        if (result && result2) {
            return ResponseEntity.ok().body("200 Register Success");
        } else {
            return ResponseEntity.badRequest().body("400 / 500 Register Failed (Error Request / Server)");
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> submitLogin(@RequestBody LoginDTO loginDTO) {

        org.springframework.security.core.Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //for jwt
        String token = authService.loginToken(loginDTO.getEmail(), loginDTO.getPassword());

        if (authentication.isAuthenticated() == true) {
            return ResponseEntity
                    .ok()
                    .body(Map.of("message", "200 Login Success",
                    "email", loginDTO.getEmail(),
                    "token", token,
                    "role", userDetails.getAuthorities()
                ));
        } else {
            return ResponseEntity.badRequest().body("400 /500 Login Failed (Error Request / Server)");
        }
    }

    @PostMapping("change_password")
    public ResponseEntity<?> forgetPasswordSubmitPassword(@RequestBody User u) {
        userService.updatePasswordEmployee(u.getId(), encoder.encode(u.getPassword()));
        return ResponseEntity.ok().body("200 Password Changed");
    }

    // @GetMapping("forgetpassword")
    // public String forgetPassword(Model model){
    // model.addAttribute("employee", new Employee());
    // return "employee/forgetpassword";
    // }

    // @PostMapping("forgetpassword/submitEmail")
    // public String forgetPasswordSubmitEmail(Employee e){
    // Integer x = employeeService.getIdEmployeeFromEmail(e.getEmail());
    // if(x!=null){
    // return "redirect:/employee/forgetpassword/employeeId/"+x;
    // }else{
    // return "employee/forgetpassword";
    // }
    // }

    // @GetMapping("forgetpassword/employeeId/{Id}")
    // public String forgetPasswordChange(@PathVariable (required=true) Integer Id,
    // Model model){
    // model.addAttribute("id", Id);
    // model.addAttribute("user", new User());
    // return "employee/forgetpassword2";
    // }

    // @PostMapping("forgetpassword/submitPassword")
    // public String forgetPasswordSubmitPassword(User u) {
    // userService.updatePasswordEmployee(u.getId(),
    // passwordEncoder.encode(u.getPassword()));
    // return "redirect:/employee/login";
    // }
}
