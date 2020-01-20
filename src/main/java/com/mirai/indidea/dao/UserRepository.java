package com.mirai.indidea.dao;

import com.mirai.indidea.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

//    通过id查找
//    public UserEntity findUserEntityById(int id);

    public List<UserEntity> findAll();
}
