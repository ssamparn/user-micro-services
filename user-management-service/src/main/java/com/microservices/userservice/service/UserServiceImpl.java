package com.microservices.userservice.service;

import com.microservices.userservice.entity.UserEntity;
import com.microservices.userservice.repository.UsersRepository;
import com.microservices.userservice.web.model.request.UserSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserEntity createUser(UserSignUpRequest newUser) {

        UserEntity entity = new UserEntity();
        entity.setUserId(UUID.randomUUID().toString());
        entity.setFirstName(newUser.getFirstName());
        entity.setLastName(newUser.getLastName());
        entity.setEmail(newUser.getEmail());
        entity.setEncryptedPassword(passwordEncoder.encode(newUser.getPassword()));

        UserEntity savedUserEntity = usersRepository.save(entity);

        return savedUserEntity;
    }

    @Override
    public UserEntity getUserDetailsByEmail(String email) {

        UserEntity userEntityByEmail = usersRepository.findByEmail(email);

        if (userEntityByEmail == null) {
            throw new UsernameNotFoundException(email);
        }

        return userEntityByEmail;
    }

    @Override
    public UserEntity getUserByUserId(String userId) {

        UserEntity entity = usersRepository.findByUserId(userId);

        if (entity == null) {
            throw new EntityNotFoundException("User not found");
        }

        return entity;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = usersRepository.findByEmail(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
    }
}
