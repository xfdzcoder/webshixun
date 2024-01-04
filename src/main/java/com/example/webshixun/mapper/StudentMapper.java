package com.example.webshixun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webshixun.entity.Student;

import org.apache.ibatis.annotations.Mapper;

/**
 * (Student)表数据库访问层
 *
 * @author makejava
 * @since 2024-01-04 18:28:34
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}

