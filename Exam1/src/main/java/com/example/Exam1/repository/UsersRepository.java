package com.example.Exam1.repository;

import com.example.Exam1.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserModel,Integer> {


    Optional<UserModel> findByLoginAndPassword(String login, String password);
    Optional<UserModel>findFirstByLogin(String login);


}

