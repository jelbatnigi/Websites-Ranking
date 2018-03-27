package com.jamal.siterank.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamal.siterank.dao.SiteRanksDao;
import com.jamal.siterank.domain.SiteRank;

/**
 * Two approaches (methods) are being provided here. getRanksSorted - Sort and
 * return top 5 directly from the DB using SQL getAllRanksByDate - Assuming we
 * cannot rely on sorting in the DB. We get the full list and sort in memory and
 * return top 5
 * 
 * @author j_elbatn
 *
 */
@Service
public class RankService {

	@Autowired
	SiteRanksDao siteRanksDao;
	@Autowired
	ExclusionService exclusionService;

	public Map<String, Long> gettopFiveRanksSorted(String rankDate) {
		//First get excluded sites
		List<String> execludedSites = exclusionService
				.getExeclusionList(rankDate);

		Map<String, Long> map = siteRanksDao.getTopFiveRanksSorted(rankDate,
				execludedSites.size());
		
		//remove the excluded sites
		execludedSites.forEach(site -> map.remove(site));

		return map;
	}

	public Map<String, Long> getAllRanksByDate(String rankDate) {
		//First get excluded sites
		List<String> execludedSites = exclusionService
				.getExeclusionList(rankDate);
		List<SiteRank> list = new LinkedList<SiteRank>();
		list = siteRanksDao.getSitesRanksByDate(rankDate);
		//Sort list by the number of visits
		list.sort((o1, o2) -> (int) (o2.getVisits() - o1.getVisits()));
		//move to map
		Map<String, Long> map = list
				.stream()
				.limit(5 + execludedSites.size())
				.collect(
						Collectors.toMap(SiteRank::getWebsite,
								SiteRank::getVisits, (v1, v2) -> v1,
								LinkedHashMap::new));
		//remove the excluded sites
		execludedSites.forEach(site -> map.remove(site));

		return map;

	}
}
