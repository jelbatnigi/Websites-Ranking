package com.jamal.siterank.domain;

public class SiteExeclusion {
	private String host;
	private String excludedSince;
	private String excludedTill;

	public SiteExeclusion() {
		super();
	}

	public SiteExeclusion(String host, String excludedSince, String excludedTill) {
		super();
		this.host = host;
		this.excludedSince = excludedSince;
		this.excludedTill = excludedTill;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getExcludedSince() {
		return excludedSince;
	}

	public void setExcludedSince(String excludedSince) {
		this.excludedSince = excludedSince;
	}

	public String getExcludedTill() {
		return excludedTill;
	}

	public void setExcludedTill(String excludedTill) {
		this.excludedTill = excludedTill;
	}

	@Override
	public String toString() {
		return "SiteExeclusion [host=" + host + ", excludedSince="
				+ excludedSince + ", excludedTill=" + excludedTill + "]";
	}

}
