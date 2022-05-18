package com.example.Exam1.service;

import com.example.Exam1.model.UserModel;
import com.example.Exam1.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserModel registerUser(String login, String password, String email){
        if (login == null || password == null) {
            return null;
        } else {
            if(usersRepository.findFirstByLogin(login).isPresent()){
                System.out.println("Duplicate LogIn");
                return null;

            }
            UserModel userModel = new UserModel();
            userModel.setLogin(login);
            userModel.setEmail(email);
            userModel.setPassword(password);
            return usersRepository.save(userModel);

        }

    }



    public UserModel authenticate(String login,String password){
        return usersRepository.findByLoginAndPassword(login,password).orElse(null);
    }

    public void save(UserModel userModel) {

        usersRepository.save(userModel);
    }




}


