package com.atguigu.gmall.user.controller;

import com.atguigu.gmall.bean.UmsMember;
import com.atguigu.gmall.bean.UmsMemberReceiveAddress;
import com.atguigu.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping("/index2222")
    @ResponseBody
    public String index(){
        return "2222222";
    }

    @RequestMapping("/index")
    @ResponseBody
    public String index1(){
        return "2222111222";
    }

    @RequestMapping("/getAllUser")
    @ResponseBody
    public List<UmsMember> getAllUser(){
        List<UmsMember> umsMembers=userService.getAllUser();
        return umsMembers;
    }

    @RequestMapping("/getReceiveAddressByNemberId")
    @ResponseBody
    public List<UmsMemberReceiveAddress> getReceiveAddressByNemberId(String memberId){
        List<UmsMemberReceiveAddress> umsMemberReceiveAddress=userService.getReceiveAddressByNemberId(memberId);
        return umsMemberReceiveAddress;
    }

}
