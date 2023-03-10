package com.example.kirikiri.controller;

import com.example.kirikiri.domain.UserVO;
import com.example.kirikiri.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.FileInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;





import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Properties;




@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserVO userVO){
        return "/signup";
    }
    @PostMapping("/signup")
    public RedirectView signupComplete(UserVO userVO){
        userService.signup(userVO);
        return new RedirectView("/login");
    }


    @GetMapping("/login")
    public String login(UserVO userVO){
        return "/login";
    }
    @PostMapping("/login")
    public RedirectView login(UserVO userVO, Model model, HttpServletRequest request){
        boolean userCheck = true;
        HttpSession session = request.getSession();
        model.addAttribute("userCheck", userCheck);
        String userId = userVO.getUserId();
        session.setAttribute("userId", userId);
        return new RedirectView("/");
    }
    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();

        return new RedirectView("/");
    }

    @GetMapping("/myPage/info")
    public void getInfoById(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String userId = null;
        UserVO userVO = new UserVO();
        if(session != null) {
            userId = (String)session.getAttribute("userId");
        }
        if(userId != null) {
            userVO = userService.getUserVOById(userId);
        }
        model.addAttribute("userVO", userVO);
    }

    @PostMapping("/myPage/info")
    public RedirectView updateInfo(UserVO userVO, RedirectAttributes redirectAttributes){
        userService.updateInfo(userVO);

        return new RedirectView("/myPage/info");
    }
    @GetMapping("/myPage/delete")
    public void delete(){}


    @PostMapping("/myPage/delete")
    public RedirectView delete(HttpServletRequest request){
        HttpSession session = request.getSession();
        String userId = null;
        if(session != null) {
            userId = (String)session.getAttribute("userId");
        }
        userService.deleteInfo(userId);
        session.invalidate();

        return new RedirectView("/");
    }

    @GetMapping("/findAccount")
    public void findGet (Model model){

    }

    //?????? ??????
    @PostMapping("/checkEmail")
    public void findById (Model model, String userEmail){
        UserVO userVO = userService.findById(userEmail);
        model.addAttribute("user", userService.findById(userEmail));

        String recipient = userEmail;
        String code = userVO.getUserId();

        // 1. ???????????? ?????? ????????? ???????????? ??????
        final String user = "kimjinu2540@gmail.com";
        final String password = "hhuadnmuqfwjmnjt";

        // 2. Property??? SMTP ?????? ?????? ??????
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 587);

        // 3. SMTP ??????????????? ????????? ????????? ???????????? Session ???????????? ???????????? ??????
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                }
        );

        // 4. Message ???????????? ????????? ???????????? ???????????? ??????, ????????? ???????????? ????????????.
        // 5. Transport ???????????? ???????????? ????????? ???????????? ????????????.

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(user));

            // ????????? ?????? ??????
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Subject
            message.setSubject("kirikiri ??????ID ??????");

            // Text
            message.setText("????????? ID??? [ "+code+" ] ?????????");

            Transport.send(message);    // send message


        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    //???????????? ??????
    @GetMapping("/passwordReset")
    public void password (UserVO userVO, HttpServletRequest request){
        HttpSession session = request.getSession();

        if(session != null) {
            userVO.setUserId((String)session.getAttribute("userId"));
        }
    }
    @PostMapping("/passwordResetCompletion")
    public String changePwCompletion (Model model, UserVO userVO, HttpServletRequest request) {
        userService.updatePw(userVO);
        return "/passwordResetCompletion";
    }
}
