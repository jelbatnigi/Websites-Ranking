package com.jamal.siterank.test.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jamal.siterank.domain.SiteExeclusion;
import com.jamal.siterank.service.ExclusionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExeclusionServiceTest {
	
	@Autowired
	ExclusionService exclusionService;
	
	@Test
	public void getExeclusionListTest()
	{
		SiteExeclusion execlusion1 = new SiteExeclusion("facebook.com","2016-12-01",null);
		SiteExeclusion execlusion2 = new SiteExeclusion("google.com","2016-03-12","2016-03-14");
		SiteExeclusion execlusion3 = new SiteExeclusion("apple.com","2017-05-20","2017-12-31");
		
		SiteExeclusion[] execlusionList = {execlusion1,execlusion2,execlusion3};
		
		String rankDt = "2017-12-01";
		List<String> sites = exclusionService.filterExeclusionList(execlusionList,rankDt);
		//System.out.println(sites);
		String[] execRS = {"facebook.com", "apple.com"};
		assertArrayEquals(execRS, sites.toArray());
		
		rankDt = "2015-12-01";
		sites = exclusionService.filterExeclusionList(execlusionList,rankDt);
		System.out.println(sites);
		String[] execRS2 = {};
		assertArrayEquals(execRS2, sites.toArray());
		
		rankDt = "2018-12-01";
		sites = exclusionService.filterExeclusionList(execlusionList,rankDt);
		System.out.println(sites);
		String[] execRS3 = {"facebook.com"};
		assertArrayEquals(execRS3, sites.toArray());
		//System.out.println(sites);*
	}

}
