package org.zerock.web;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

   private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

   @GetMapping("/dnd")
   public void dnd(){
      
   }
   
   @GetMapping("/input")
   public void inputGet() {
      logger.info("input get");
   }
   
  

   @GetMapping(value="/show",produces={"image/jpg","image/gif","image/png"})
   public @ResponseBody byte[] show(String name)throws Exception{
      
      InputStream in = new FileInputStream("C:\\zzz\\"+name);
//      OutputStream out = new ByteArrayOutputStream();
      
      return IOUtils.toByteArray(in);
      
   }
   
   @PostMapping("/uploadFile")
   @ResponseBody
   public String uploadFile(MultipartFile file)throws Exception{
      
      UUID uid = UUID.randomUUID();

      String fileName = file.getOriginalFilename();
      
      String uploadName = uid + "_" + fileName;

      FileOutputStream fos 
      = new FileOutputStream("C:\\zzz\\" + uploadName);
      
      //IOUtils을 쓸려고 Commons FileUpload 라이브러리 받은거다
      IOUtils.copy(file.getInputStream(), fos);
      fos.close();
      
      return fileName;
      
   }
   
   @PostMapping("/upload")
   public String upload(String id, String pw, 
         MultipartFile profile, Model model) throws Exception {

      logger.info("id :" + id);
      logger.info("pw :" + pw);
      logger.info("profile :" + profile);

      //d2771601-9016-4205-a035-0a8b2e9c533b이런식의 랜덤아이디를 생성해주는것
      UUID uid = UUID.randomUUID();

      String fileName = profile.getOriginalFilename();
      
      String uploadName = uid + "_" + fileName;

      FileOutputStream fos 
      = new FileOutputStream("C:\\zzz\\" + uploadName);
      
      //IOUtils을 쓸려고 Commons FileUpload 라이브러리 받은거다
      IOUtils.copy(profile.getInputStream(), fos);
      fos.close();
      
      model.addAttribute("uploadName",uploadName);
      
      return "success";
   }

   /**
    * Simply selects the home view to render by returning its name.
    */
   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String home(Locale locale, Model model) {
      logger.info("Welcome home! The client locale is {}.", locale);

      Date date = new Date();
      DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

      String formattedDate = dateFormat.format(date);

      model.addAttribute("serverTime", formattedDate);

      return "home";
   }

}