package com.atguigu.gmall.user.mapper;

import com.atguigu.gmall.bean.UmsMember;
import com.atguigu.gmall.user.baseDao.IBaseDao;

import java.util.List;

public interface UserMapper extends IBaseDao<UmsMember> {

    List<UmsMember> selectAllUser();

}
