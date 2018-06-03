package com.duynguyen.personal.personalproject.web.api;

import com.duynguyen.personal.personalproject.domain.URL;
import com.duynguyen.personal.personalproject.repository.jpa.URLRepository;
import com.duynguyen.personal.personalproject.util.CodecUtil;
import com.duynguyen.personal.personalproject.util.HttpUtil;
import com.google.gson.Gson;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class UrlShortenerController {

    @Resource
    private URLRepository urlRepository;

    @Resource
    private Environment environment;

    @RequestMapping(value = "/createURL", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createURL(@RequestBody String data, HttpServletRequest request) {
        URL url = new Gson().fromJson(data, URL.class);
        urlRepository.save(url);
        String shortURL = HttpUtil.getBaseURL(environment.getActiveProfiles()[0]) + "redirect/" + CodecUtil.encode(url.getId());
        url.setShortURL(shortURL);
        urlRepository.save(url);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/redirect/{encodedId}", method = RequestMethod.GET)
    public String redirect(@PathVariable String encodedId) throws Base64DecodingException {
        long id = CodecUtil.decode(encodedId);
        String originalURL = urlRepository.findOne(id).getOriginalURL();
        return "redirect:" + originalURL;
    }

}
