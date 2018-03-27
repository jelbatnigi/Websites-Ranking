package com.jamal.siterank.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jamal.siterank.domain.SiteExeclusion;

/**
 * @author j_elbatn
 *
 */
@Service
public class ExclusionService {

	/** Call the service to get execluded list
	 * @param date
	 * @return
	 */
	public List<String> getExeclusionList(String date)
	{
		//Get Execluded list
		RestTemplate restTemplate = new RestTemplate();
		SiteExeclusion[] response = restTemplate.getForObject("http://private-1de182-mamtrialrankingadjustments4.apiary-mock.com/exclusions", SiteExeclusion[].class);

		return filterExeclusionList(response, date);
	}


	/** Filters the execluded list to see if execlusion is still active
	 * 
	 * @param response
	 * @param date
	 * @return
	 * returns only list of site names.
	 */
	public List<String> filterExeclusionList(SiteExeclusion[] response, String date)
	{
		List<SiteExeclusion> execlusionList = new ArrayList<SiteExeclusion>(Arrays.asList(response));
		List<String> execludedSites = execlusionList.stream().filter(t -> {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date rankDt = formatter.parse(date);
				Date execludeSince = formatter.parse(t.getExcludedSince());
				
				//Not execluded if rank date is less that execludedSince
				if(rankDt.compareTo(execludeSince) < 0)
					return false;
				//System.out.println(execludeSince);
				Date excludedTill = null;
				
				if(t.getExcludedTill() != null)
				{
					excludedTill = formatter.parse(t.getExcludedTill());
					//execluded if date is in between
					if(rankDt.compareTo(execludeSince) >= 0 && rankDt.compareTo(excludedTill) <= 0)
						return true;
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}).map(m -> m.getHost()).collect(Collectors.toList());
		return execludedSites;

	}

}
