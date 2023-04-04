package com.example.leaverequest.configuration;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.leaverequest.dto.LoginDTO;
import com.example.leaverequest.models.Employee;
import com.example.leaverequest.models.Role;
import com.example.leaverequest.models.User;
import com.example.leaverequest.repositories.EmployeeRepository;
import com.example.leaverequest.services.UserService;


@Service
public class MyUserDetails implements UserDetails, UserDetailsService {
    // private UserManagementRepository userManagementRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    private String username;
    private String password;
    private GrantedAuthority authority;

    public MyUserDetails(){

    }
    
    public MyUserDetails(Employee employee){
        // this.employeeRepository = employeeRepository;
        // this.username = "adi@gmail.com";
        // this.password = "adi";
        // this.authority = new SimpleGrantedAuthority("Spv");

        // this.username = user.getEmployee().getEmail();
        // this.password = user.getPassword();
        // this.authority = new SimpleGrantedAuthority(user.getRole().getName());

        this.username = employee.getEmail();
        this.password = employee.getUser().getPassword();
        this.authority = new SimpleGrantedAuthority(employee.getUser().getRole().getName());
        
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthority = new HashSet<>();
        grantedAuthority.add(authority);
        return grantedAuthority;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.leaverequest.models.Employee data = employeeRepository.loginEmployee(username);
        // com.example.leaverequest.models.User data = employeeRepository.loginUser(username);
        //return new User(data.getEmail(), data.getUser().getPassword(), getAuthorities());
        // return new MyUserDetails(data);
        this.authority = new SimpleGrantedAuthority(data.getUser().getRole().getName());
        this.username=data.getEmail();
        this.password=data.getUser().getPassword();
        // return new User(data.getEmail(), data.getUser().getPassword(), getAuthorities());
        return new org.springframework.security.core.userdetails.User(data.getEmail(), data.getUser().getPassword(), getAuthorities());
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    
}
