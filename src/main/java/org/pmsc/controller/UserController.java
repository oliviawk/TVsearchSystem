package org.pmsc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.pmsc.entity.User;
import org.pmsc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("getAll")
    public String getAll(Model model){
        List<User> users = userRepository.findAll();
        User u = userRepository.login("095308","123456");
        model.addAttribute("users",users);
        model.addAttribute("user",u);
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
}
