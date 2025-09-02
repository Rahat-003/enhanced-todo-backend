package com.personal.auth_service.service;

import com.personal.auth_service.util.JwtUtil;
import com.personal.domain.User;
import com.personal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class AuthService {


//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtUtil jwtUtil;

//    @Autowired
//    public AuthService(UserRepository userRepository, JwtUtil jwtUtil) {
//        this.userRepository = userRepository;
//        this.jwtUtil = jwtUtil;
//        this.passwordEncoder = new BCryptPasswordEncoder();
//    }

//    public String register(String username, String password, Set<String> roles) {
//        if(userRepository.findByUsername(username).isPresent()) {
//            throw new RuntimeException("User already exists");
//        }
//        User user = User.builder()
//                .username(username)
//                .password(passwordEncoder.encode(password))
//                .roles(roles)
//                .build();
//        userRepository.save(user);
//        return jwtUtil.generateToken(username, String.join(",", roles));
//    }
//
//    public String login(String username, String password) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        if(!passwordEncoder.matches(password, user.getPassword())) {
//            throw new RuntimeException("Invalid password");
//        }
//        return jwtUtil.generateToken(username, String.join(",", user.getRoles()));
//    }
}
