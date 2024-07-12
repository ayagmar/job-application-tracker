package com.ayagmar.jobapplicationtracker.service;

import com.ayagmar.jobapplicationtracker.model.User;
import com.ayagmar.jobapplicationtracker.model.record.UserRecord;
import com.ayagmar.jobapplicationtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserRecord findUserRecordById(Long userId){
        return userRepository.findById(userId)
                .map(User::toRecord)
                .orElse(null);
    }

    public UserRecord insertUserRecord(UserRecord userRecord){
        return userRepository.save(userRecord.toUser()).toRecord();
    }
}
