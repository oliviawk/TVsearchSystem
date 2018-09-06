package org.pmsc.controller;

import com.alibaba.fastjson.JSON;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @ResponseBody
    public Integer add(String name,Integer fid){
        Channel entity = new Channel();
        entity.setName(name);
        entity.setFatherId(fid);
        int c_order = channelRepository.findOrderId(fid);
        entity.setC_order(c_order+1);
        entity = channelRepository.save(entity);
        return entity.getId();
    }

    @RequestMapping("edit")
    @ResponseBody
    public Integer update(String name,Integer id){
        return channelRepository.updateName(id,name);
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(Integer id){
        channelRepository.delete(id);
        return "ok";
    }

    @RequestMapping("channelManage")
    public String channelM(Model model){
        List<Channel> list = channelRepository.findByFatherId(0);
        model.addAttribute("channels",list);
        return "channelMgr";
    }

    @RequestMapping("getByFid")
    @ResponseBody
    public List<Channel> getByFid(Integer fid){
        return channelRepository.findByFatherId(fid);
    }

    @RequestMapping("changeFid")
    @ResponseBody
    public Integer changeFid(Integer id,Integer fid){
        if(channelRepository.exists(Long.getLong(fid.toString()))){
            int res = channelRepository.updateFatherId(id,fid);
            return res;
        }
        return null;
    }
}
