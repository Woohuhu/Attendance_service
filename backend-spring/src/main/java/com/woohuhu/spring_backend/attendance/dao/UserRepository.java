package com.woohuhu.spring_backend.attendance.dao;

import com.woohuhu.spring_backend.attendance.Entity.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, String> {

}
