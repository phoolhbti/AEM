package com.citraining.search.solr.services;

import java.io.IOException;
import java.util.List;

import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import com.citraining.search.solr.beans.AssetsBean;
import com.day.cq.search.result.SearchResult;

public interface SolrSearchAssetsService {
	List<AssetsBean> crawlAssets(String resourcePath, String resourceType);

	List<AssetsBean> createAssetsMetadataArray(SearchResult results) throws RepositoryException;

	AssetsBean createAssetsMetadataObject(Resource pageContent);

	boolean indexAssetsToSolr(AssetsBean indexAssetData, HttpSolrClient server) throws  SolrServerException, IOException;

	boolean indexAssetsToSolr(List<AssetsBean> indexAssetData, HttpSolrClient server) throws  SolrServerException, IOException;

}
