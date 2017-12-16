package com.zx.util;

import java.util.ArrayList;
import java.util.List;

import com.zx.domain.Cla;

public class Db {
	public static List<Cla> claList = new ArrayList<>();
	static{
		for(int i = 0;i < 10 ;i++){
			claList.add(new Cla(String.valueOf(i),"name" + i));
		}
	}
}
