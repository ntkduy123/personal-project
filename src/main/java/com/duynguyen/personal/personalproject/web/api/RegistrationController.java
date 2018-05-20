package com.duynguyen.personal.personalproject.web.api;

import com.duynguyen.personal.personalproject.domain.MyUser;
import com.duynguyen.personal.personalproject.domain.VerificationToken;
import com.duynguyen.personal.personalproject.event.OnRegistrationCompleteEvent;
import com.duynguyen.personal.personalproject.service.MyUserService;
import com.duynguyen.personal.personalproject.service.VerificationTokenService;
import com.duynguyen.personal.personalproject.util.ValidationUtil;
import com.google.gson.Gson;
import net.minidev.json.JSONObject;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private MyUserService myUserService;

    @Resource
    private VerificationTokenService tokenService;

    @Resource
    private ApplicationEventPublisher eventPublisher;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity registerUserAccount(@RequestBody String data, HttpServletRequest request) {
        MyUser myUser = new Gson().fromJson(data, MyUser.class);

        if (myUser != null) {
            JSONObject messages = new JSONObject();

            if (myUserService.isUserExist(myUser.getEmail())) {
                messages.put("emailExist", true);
                return new ResponseEntity<>(messages, HttpStatus.OK);
            }

            if (!ValidationUtil.isMatch(myUser.getPassword(), myUser.getMatchingPassword())) {
                messages.put("unmatchedPassword", true);
                return new ResponseEntity<>(messages, HttpStatus.OK);
            }

            myUser.setPassword(bCryptPasswordEncoder.encode(myUser.getPassword()));
            myUserService.save(myUser);
            String appUrl = String.format("%s://%s:%d",request.getScheme(),  request.getServerName(), request.getServerPort());
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                    (myUser, appUrl));

            messages.put("success", "OK");
            return new ResponseEntity<>(messages, HttpStatus.OK);
        }


        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity confirmRegistration(@RequestParam("token") String token) {
        VerificationToken verificationToken = tokenService.findByToken(token);
        System.out.println(verificationToken);
        if (verificationToken != null) {
            Calendar calendar = Calendar.getInstance();
            long expireTime = verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime();
            MyUser user = verificationToken.getMyUser();
            if (expireTime > 0) {
                user.setEnabled(true);
                myUserService.save(user);
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
