package com.venns.dao;

import com.venns.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TeacherMapper {

    //获取老师
    //List<Teacher> getTeacher();

    //获取指定老师下的所有学生及老师的信息
     Teacher getTeacher(@Param("tid")int id);
}
