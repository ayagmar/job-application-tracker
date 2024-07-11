package com.ayagmar.jobapplicationtracker.service;

import com.ayagmar.jobapplicationtracker.entity.User;
import com.ayagmar.jobapplicationtracker.entity.UserDto;
import com.ayagmar.jobapplicationtracker.entity.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    @PostConstruct
    public void init(){
        UserDto userDto = new UserDto(1L,"hamid","lala","hhaha");
        System.out.println(insertUserRecord(userDto));
        System.out.println(findUserRecordById(userDto.toUser().getId()));
    }

    public UserDto findUserRecordById(Long userId){
        return userRepository.findById(userId)
                .map(User::toRecord)
                .orElse(null);
    }

    public UserDto insertUserRecord(UserDto userDto){
        return userRepository.save(userDto.toUser()).toRecord();
    }
}
