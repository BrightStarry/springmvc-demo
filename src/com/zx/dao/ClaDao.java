package com.zx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zx.domain.Cla;

public interface ClaDao {
	//增加
	public void add(Cla cla);
	//修改
	public void edit(Cla cla);
	//删除
	public void delete(String claId);
	//分页查询
	public List<Cla> queryPage(@Param("beginRecord")int beginRecord,@Param("endRecord")int endRecord);
	//查询某个班级
	public Cla query(String claId);
	
}
