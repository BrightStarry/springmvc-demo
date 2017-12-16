package com.zx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zx.domain.Stu;

public interface StuDao {
	//增加
	public void add(Stu stu);
	//修改
	public void edit(Stu stu);
	//删除
	public void delete(String stuId);
	//分页查询
	public List<Stu> queryPage(@Param("beginRecord")int beginRecord,@Param("endRecord")int endRecord);
	//查询某个学生
	public Stu query(String StuId);
	
}
