package com.jamal.siterank.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.jamal.siterank.domain.SiteRank;

public class SiteRanksDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Map<String, Long> getTopFiveRanksSorted(String rankDate, int rownum) {
		String sql = "select website, visits " + "from Ranks "
				+ "where rank_dt = ? " + "order by visits desc " + "LIMIT ?";

		return (Map<String, Long>) jdbcTemplate.query(sql,
				new ResultSetExtractor<Map<String, Long>>() {

					@Override
					public Map<String, Long> extractData(ResultSet rs)
							throws SQLException, DataAccessException {

						Map<String, Long> map = new LinkedHashMap<String, Long>();
						while (rs.next()) {
							String website = rs.getString("website");
							Long visits = rs.getLong("visits");
							map.put(website, visits);
						}

						return map;
					}

				}, rankDate, rownum + 5);

	}

	public List<SiteRank> getSitesRanksByDate(String rankDate) {
		String sql = "select website, visits, rank_dt " + "from Ranks "
				+ "where rank_dt = ? ";

		return jdbcTemplate.query(sql, new RowMapper<SiteRank>() {

			@Override
			public SiteRank mapRow(ResultSet rs, int rowNum)
					throws SQLException {

				SiteRank siteRank = new SiteRank();
				siteRank.setWebsite(rs.getString("website"));
				siteRank.setVisits(rs.getLong("visits"));
				siteRank.setRankDt(rs.getDate("rank_dt"));

				return siteRank;

			}

		}, rankDate);
	}

}
