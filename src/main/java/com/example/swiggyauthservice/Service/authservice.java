package com.example.swiggyauthservice.Service;

import com.example.swiggyauthservice.DTO.ResponseDTO;
import com.example.swiggyauthservice.DTO.SignInDTO;
import com.example.swiggyauthservice.DTO.SignUpDTO;
import com.example.swiggyauthservice.Entity.Role;
import com.example.swiggyauthservice.Entity.User;
import com.example.swiggyauthservice.Exception.PasswordException;
import com.example.swiggyauthservice.Exception.Phonenumberexception;
import com.example.swiggyauthservice.Repositroy.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class authservice {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseDTO SignUp(SignUpDTO signUpDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        if(userRepository.findByPhonenumber(signUpDTO.getPhonenumber()) != null) {
            throw new Phonenumberexception(signUpDTO.getPhonenumber()+" already exists");
        }
        User user = new User();
        user.setName(signUpDTO.getName());
        user.setEmail(signUpDTO.getEmail());
        user.setPhonenumber(signUpDTO.getPhonenumber());
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        user.setRole(Role.CUSTOMER);
        userRepository.save(user);
        responseDTO.setName(signUpDTO.getName());
        responseDTO.setPhonenumber(signUpDTO.getPhonenumber());
        responseDTO.setRole(user.getRole().name());
        return responseDTO;
    }
    public String SignIn(SignInDTO signInDTO) {
        User user = userRepository.findByPhonenumber(signInDTO.getPhoneNumber());
        if(user == null){
            throw new Phonenumberexception(signInDTO.getPhoneNumber()+" not found");
        }
        if(!passwordEncoder.matches(signInDTO.getPassword(), user.getPassword())) {
            throw new PasswordException("Invalid password");
        }
        return "SignIn successful";
    }
}
