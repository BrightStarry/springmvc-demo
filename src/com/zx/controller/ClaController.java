package com.zx.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zx.domain.Cla;
import com.zx.domain.User;
import com.zx.util.Db;

@Controller
@RequestMapping("claa")
//@SessionAttributes("userName")
public class ClaController {
	
	
	@RequestMapping("down")
	public ResponseEntity<byte[]> down() throws Exception{
		String path="d:\\桌面\\Desktop\\a.txt";
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();
		//解决中文乱码问题,并设置下载是客户端显示的文件名为zx
		String fileName = new String("zx".getBytes("UTF-8"),"iso-8859-1");
		
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
				headers,HttpStatus.CREATED);
		
	}
	 
	
	
	
	@RequestMapping("b")
	public String b(HttpServletRequest request){
		System.out.println(request.getParameter("userName"));
		return "cla/a";
	}
	
	@RequestMapping("testJson")
	public  String  testJson(@RequestBody Cla cla) throws Exception{//@ResponseBody User
		//System.out.println(cla.getClaId());
		User user1= new User("1","1");
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(user1);
		return result;
	}
	
	
	
	@RequestMapping("toList")
	public String toList(Model model){
		//model.addAttribute("claList",Db.claList);
		Map<Integer,String> map = new HashMap<>();
//		map.put(1, "放放大");
//		map.put(2, "dd");
//		map.put(3, "ddff");
//		model.addAttribute("claList",map);
//		
//		User user = new User("dfsf","dfefwegrg");
//		model.addAttribute("user",user);
//		model.addAttribute("claList",map);
		
		User user1 = new User("1","1");
		User user2 = new User("2","2");
		User user3 = new User("3","3");
		List<User> list = new ArrayList<>();
		list.add(user1);
		list.add(user2);
		list.add(user3);
		model.addAttribute("claList",list);
		return "cla/cla";
	}
	
	@RequestMapping("toForm")
	public String toForm(String claId,Model model){
		if(claId != null){
			for(int i=0;i < Db.claList.size();i++){
				if(claId.equals(Db.claList.get(i).getClaId())){
					model.addAttribute("cla",Db.claList.get(i));
					break;
				}
			}
			return "cla/updateCla";
		}else{
			return "cla/addCla";
		}
	}
	
	@RequestMapping("addCla")
	public String addCla(Cla cla){
		Db.claList.add(cla);
		return "redirect:/claa/toList";
	}
	
	@RequestMapping("delCla")
	public String delCla(String claId){
		for(int i=0;i < Db.claList.size();i++){
			if(claId.equals(Db.claList.get(i).getClaId())){
				Db.claList.remove(i);
				break;
			}
		}
		return "redirect:/claa/toList";
	}
	@RequestMapping("updateCla")
	public String updateCla(Cla cla){
		for(int i=0;i < Db.claList.size();i++){
			if(cla.getClaId().equals(Db.claList.get(i).getClaId())){
				Db.claList.get(i).setClaName(cla.getClaName());
				break;
			}
		}
		return "redirect:/claa/toList";
	}
	
	
	@RequestMapping("interceptor")
	public ModelAndView interceptor(User user,ModelAndView mav){
		mav.setViewName("cla/a");
		System.out.println(user.getUserName());
		return mav;
	}
	
	@RequestMapping(value="upload")
	public String upload(@RequestParam("file")MultipartFile file) throws IOException{
		if(!file.isEmpty()){
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File("d:/桌面/Desktop/",System.currentTimeMillis() + file.getOriginalFilename()));
		}		
		return "cla/a";
	}
}
