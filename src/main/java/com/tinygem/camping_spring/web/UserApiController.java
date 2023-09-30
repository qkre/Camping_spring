package com.tinygem.camping_spring.web;

import com.tinygem.camping_spring.domain.user.User;
import com.tinygem.camping_spring.service.UserService;
import com.tinygem.camping_spring.web.dto.AddUserRequestDto;
import com.tinygem.camping_spring.web.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> signUp(@RequestBody AddUserRequestDto requestDto){
        int result = userService.save(requestDto);

        return ResponseEntity.ok(String.valueOf(result));
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<User>> login(@RequestBody LoginRequestDto requestDto){
        if(userService.login(requestDto)){
            return ResponseEntity.ok(userService.findByEmail(requestDto.getEmail()));
        } return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable int id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/clear")
    public ResponseEntity<String> clearAll(){
        userService.clearAll();
        return ResponseEntity.ok("유저 DB 초기화 완료");
    }
}
