package com.jamal.siterank.test.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.jamal.siterank.dao.SiteRanksDao;
import com.jamal.siterank.service.ExclusionService;
import com.jamal.siterank.service.RankService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RankServiceTest {
	
	@Autowired
	RankService rankService;
	@MockBean
	SiteRanksDao siteRanksDao;
	@MockBean
	ExclusionService exclusionService;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		
		List<String> execludedList = new ArrayList();
		execludedList.add("google.com");
		execludedList.add("apple.com");
		
		Mockito.when(exclusionService.getExeclusionList("2016-03-13")).thenReturn(execludedList);
		
		Map<String, Long> sites = new LinkedHashMap<String, Long>();
		sites.put("google.com", 1000000L);
		sites.put("Yahoo.com", 1000000L);
		sites.put("gmail.com", 500000L);
		sites.put("facebook.com", 40000L);
		sites.put("apple.com", 250000L);
		sites.put("career.com", 10000L);
		sites.put("cars.com", 1000L);
		
		Mockito.when(siteRanksDao.getTopFiveRanksSorted("2016-03-13", execludedList.size()+5)).thenReturn(sites);
	}
	
	@Test
	public void getExeclusionListTest()
	{
		Set<String> set = new HashSet(){{add("Yahoo.com");
		add("gmail.com");
		add("facebook.com");
		add("career.com");
		add("cars.com");}};
		Map<String, Long> map = rankService.gettopFiveRanksSorted("2016-03-13");
		
		assertNotEquals(set, map.keySet());
		
	}

}
