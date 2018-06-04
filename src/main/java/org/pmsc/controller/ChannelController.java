package org.pmsc.controller;

import com.alibaba.fastjson.JSONObject;
import org.pmsc.entity.Channel;
import org.pmsc.entity.User;
import org.pmsc.repository.ChannelRepository;
import org.pmsc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelRepository channelRepository;

    @RequestMapping("channelSet")
    public String channelSet(Model model){
        return "channelSet";
    }

    @RequestMapping("add")
    public void add(Channel entity){
        channelRepository.save(entity);
    }

    @RequestMapping("channelManage")
    public String channelM(Model model){
        List<Channel> list = channelRepository.findByFatherId(0);
        model.addAttribute("channels",list);
        return "channelMgr";
    }
}
