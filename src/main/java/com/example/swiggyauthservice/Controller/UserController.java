package com.example.swiggyauthservice.Controller;

import com.example.swiggyauthservice.DTO.ResponseDTO;
import com.example.swiggyauthservice.DTO.SignInDTO;
import com.example.swiggyauthservice.DTO.SignUpDTO;
import com.example.swiggyauthservice.Service.authservice;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final authservice authservice;

    public UserController(authservice authservice) {
        this.authservice = authservice;
    }

    @PostMapping("/Signup")
    public ResponseEntity<ResponseDTO> signup(@Valid @RequestBody SignUpDTO signUpDTO) {
        ResponseDTO responseDTO = authservice.SignUp(signUpDTO);
        return ResponseEntity.ok(responseDTO);

    }
    @PostMapping("/Signin")
    public ResponseEntity<?> signin(@Valid @RequestBody SignInDTO signInDTO) {
        return ResponseEntity.ok(authservice.SignIn(signInDTO));
    }
}
