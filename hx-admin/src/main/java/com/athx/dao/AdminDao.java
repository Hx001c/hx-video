package com.athx.dao;

import com.athx.entity.Admin;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* @Entity .domain.Admin
*/
@Mapper
@Repository
public interface AdminDao extends BaseMapper<Admin> {
    Admin selectByUserName(@Param("username") String username);
}

