package com.jamal.siterank.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamal.siterank.domain.SiteRank;
import com.jamal.siterank.service.RankService;

@RestController
public class RankController {

	@Autowired
	RankService rankService;

	/**Use to get the list sorted by the db
	 * @param date
	 * @return
	 */
	@RequestMapping("/ranksite-1/{date}")
	public Map<String, Long> getTopRanksSorted(@PathVariable String date) {
		Map<String, Long> map = rankService.gettopFiveRanksSorted(date);
		return map;
	}

	/**Use to get the list sorted in memory
	 * @param date
	 * @return
	 */
	@RequestMapping("/ranksite-2/{date}")
	public Map<String, Long> getTopRanksList(@PathVariable String date) {
		Map<String, Long> map = rankService.getAllRanksByDate(date);
		return map;
	}
}
