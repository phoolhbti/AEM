package com.citraining.search.solr.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Citraining Solr Service Configuration", description = "Solr Service Configuration")
public @interface SolrConfiguration {
	@AttributeDefinition(name = "Protocol",defaultValue="http", description = "Either 'http' or 'https")
	String getSolrProtocol();
	
	@AttributeDefinition(name = "Solr Server Name",defaultValue="localhost", description = "Server name or IP address")
	String getSolrServerName();
	
	@AttributeDefinition(name = "Server port",defaultValue="8983", description = "Solr Server port")
	String getSolrServerPort();
	
	@AttributeDefinition(name = "Solr Core Name",defaultValue="collection1", description = "Core name in solr server")
	String getSolrCoreName();
	
	@AttributeDefinition(name = "Content page path",defaultValue="/content/geometrixx", description = "Content page path from where solr has to index the pages e.g /content/geometrixx")
	String getSolrCorePagepath();	
	

}
