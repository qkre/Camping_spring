package com.tinygem.camping_spring.service;

import com.tinygem.camping_spring.domain.user.User;
import com.tinygem.camping_spring.repository.UserRepository;
import com.tinygem.camping_spring.web.dto.AddUserRequestDto;
import com.tinygem.camping_spring.web.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public int save(AddUserRequestDto requestDto){

        if(userRepository.findByEmail(requestDto.getEmail()).isPresent()){
            return -1;
        }

        return userRepository.save(
                User.builder()
                        .accessLevel(requestDto.getAccessLevel())
                        .email(requestDto.getEmail())
                        .pwd(requestDto.getPwd())
                        .nick(requestDto.getNick())
                        .provider(requestDto.getProvider())
                        .snsID(requestDto.getSnsID())
                        .build()
        ).getUserID();
    }

    public boolean login(LoginRequestDto requestDto){
        Optional<User> user = userRepository.findByEmail(requestDto.getEmail());

        boolean result = user.get().getPwd().equals(requestDto.getPwd());

        return result;
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(int id){
        return userRepository.findById(id);
    }

    public void clearAll(){
        userRepository.deleteAll();
    }
}
