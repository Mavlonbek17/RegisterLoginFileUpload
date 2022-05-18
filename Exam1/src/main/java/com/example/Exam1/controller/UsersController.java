package com.example.Exam1.controller;


import com.example.Exam1.model.UserModel;
import com.example.Exam1.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/register")
    public String getRegisterPage(Model model){

        model.addAttribute("registerRequest",new UserModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest",new UserModel());
        return "login_page";
    }







    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel){
        System.out.println("register request: "+userModel);
        UserModel registeredUser=usersService.registerUser(userModel.getLogin(),userModel.getPassword(),userModel.getEmail());
        if(registeredUser == null){
            return "error_page";
        }else{
            return "redirect:/login";
        }
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel,Model model){
        System.out.println("Login request: "+userModel);
        UserModel authenticated=usersService.authenticate(userModel.getLogin(),userModel.getPassword());
        if(authenticated == null){
            return "error_page";
        }else{
            model.addAttribute("userLogin",authenticated.getLogin());

            return "personal_page";
        }
    }


    @PostMapping("/submit")
    public String submit(@RequestParam("myfile") MultipartFile file) throws IOException {

        String mylocation = System.getProperty("user.dir") + "/src/main/resources/static/";

        String filename = file.getOriginalFilename();

        File mySavedFile = new File( mylocation + filename);

        InputStream inputStream = file.getInputStream();

        OutputStream outputStream = new FileOutputStream(mySavedFile);

        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = inputStream.read(bytes)) != -1){
            outputStream.write(bytes , 0 , read);
        }

        String mylink = "http://localhost:9090/" + filename;

        UserModel userModel = new UserModel();
        userModel.setFileName(mylink);

        usersService.save(userModel);

        return "redirect:/";
    }






}
