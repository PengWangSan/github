package com.ecoo.dtx.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.ecoo.dtx.DtxAdminApplication;
import com.ecoo.dtx.dto.DtxDto;

@Controller
@EnableAutoConfiguration
public class IndexController {
	
	
	@RequestMapping("/index")
    String index() {
        return "index";
    }

  
	 @RequestMapping("/dtx/list")
	 String list() {
    	
		 return "/dtx/list";
     }
    
    
    @RequestMapping("/getDtxList")
    @ResponseBody
    List<DtxDto> getlist() {
    	
    	List<DtxDto> dtxList=new ArrayList<DtxDto>();
    	
    	DtxDto dtxDto=new DtxDto();
    	dtxDto.setTranId("1");
    	dtxDto.setClassName("com.ecoo.spi");
    	
    	dtxList.add(dtxDto);
    	
    	DtxDto dtxDto2=new DtxDto();
    	dtxDto2.setTranId("2");
    	dtxDto2.setClassName("com.ecoo.spi");
    	
    	dtxList.add(dtxDto2);
    	
        return dtxList;
    }

    public static void main(String[] args) throws Exception {
		SpringApplication.run(IndexController.class, args);
	}
}