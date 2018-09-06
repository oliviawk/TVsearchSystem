package org.pmsc.controller;

import com.alibaba.fastjson.JSONObject;
import org.pmsc.entity.User;
import org.pmsc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("getAll")
    public String getAll(Model model, HttpSession session){
        List<User> users = (List)userRepository.findAll();
        User u = userRepository.login("095308","123456");
        model.addAttribute("users",users);
        session.setAttribute("user",u);
        return "userManage";
    }

    @RequestMapping("add")
    @ResponseBody
    public String add(@RequestBody User user){

        userRepository.save(user);
        JSONObject json = new JSONObject();
        json.put("success",true);
        json.put("info","用户添加成功");
        return json.toJSONString();
    }

    @RequestMapping("login")
    public String login(String userID,String pwd, HttpSession session){
        User u = userRepository.login(userID,pwd);
        session.setAttribute("user",u);
        return "userManage";
    }

    @RequestMapping("changePwd")
    @ResponseBody
    public String changePwd(String pwd,HttpSession session){

        User user = (User)session.getAttribute("user");
        user.setPwd(pwd);
        userRepository.save(user);
        session.setAttribute("user",user);
        JSONObject json = new JSONObject();
        json.put("success",true);
        json.put("info","密码修改成功");
        return json.toJSONString();
    }

    @RequestMapping("resetPwd")
    @ResponseBody
    public Integer resetPwd(String userID){
        return userRepository.reset(userID);
    }

    @RequestMapping("update")
    @ResponseBody
    public Integer update(String userID){
        return userRepository.reset(userID);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Integer delete(String userID){
        return userRepository.delete(userID);
    }
}
