package com.template.sat.service;

import com.template.sat.domain.User;
import com.template.sat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserService {

    public final UserRepository userRepository;

    public User findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

}
