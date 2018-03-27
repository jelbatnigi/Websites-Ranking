package com.jamal.siterank.domain;

import java.util.Date;

public class SiteRank {

	private String website;
	private Date rankDt;
	private long visits;

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Date getRankDt() {
		return rankDt;
	}

	public void setRankDt(Date rankDt) {
		this.rankDt = rankDt;
	}

	public long getVisits() {
		return visits;
	}

	public void setVisits(long visits) {
		this.visits = visits;
	}

	@Override
	public String toString() {
		return "SiteRank [website=" + website + ", rankDt=" + rankDt
				+ ", visits=" + visits + "]";
	}

}
