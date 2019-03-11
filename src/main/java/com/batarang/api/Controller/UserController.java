package com.batarang.api.Controller;

import com.batarang.api.Model.CustomPrincipal;
import com.batarang.api.Model.Role;
import com.batarang.api.Model.User;
import com.batarang.api.Security.JWTGenerator;
import com.batarang.api.Service.Concrete.RoleService;
import com.batarang.api.Service.Concrete.UserDetailsServiceImp;
import com.batarang.api.Service.Concrete.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private JWTGenerator jwtGenerator;

    public UserController(JWTGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody User user){
        Role role = new Role();
        role.setRoleName("ROLE_USER");
        roleService.Add(role);
        user.setRole(role);
        userService.Add(user);
        return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody User user){
        User userDetails = userService.findByUserName(user.getUserName());

        if (passwordEncoder.matches(user.getPassword(),userDetails.getPassword()))
            if (passwordEncoder.matches(user.getPassword(),userDetails.getPassword())){
            String jwt= jwtGenerator.generate(userDetails);
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }

        return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);

    }

}
