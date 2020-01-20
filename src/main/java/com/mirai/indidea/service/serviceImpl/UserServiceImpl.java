package com.mirai.indidea.service.serviceImpl;

import com.mirai.indidea.domain.UserEntity;

public interface UserServiceImpl {

    public UserEntity find(int id);

    public boolean save(UserEntity userEntity);
    /*
     *  @param code => 1为更新，2为软删除
     */
    public boolean update(UserEntity userEntity, int code);

}
