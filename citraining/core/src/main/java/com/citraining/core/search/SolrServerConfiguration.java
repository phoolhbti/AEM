package com.citraining.core.search;

public interface SolrServerConfiguration {

	public String getSolrProtocol();

	public String getSolrServerName();

	public String getSolrServerPort();

	public String getSolrCoreName();
	
	public String getContentPagePath();
	
}
