package com.citraining.search.solr.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.jcr.api.SlingRepository;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citraining.search.solr.beans.AssetsBean;
import com.citraining.search.solr.models.AssetModel;
import com.citraining.search.solr.services.SolrSearchAssetsService;
import com.citraining.search.solr.utils.CommonUtil;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

@Component (service = SolrSearchAssetsService.class)
public class SolrSearchAssetsServiceImpl implements SolrSearchAssetsService {

	private static final Logger LOG = LoggerFactory.getLogger(SolrSearchAssetsServiceImpl.class);

	@Reference
	private QueryBuilder queryBuilder;

	@Reference
	private SlingRepository repository;

	@Reference
	private ResourceResolverFactory resolverFactory;

	/**
	 * This method takes path and type of resource to perform search in JCR
	 * 
	 * @param resourcePath
	 * @param resourceType
	 * @return JSONArray with resources metadata
	 */
	@Override
	public List<AssetsBean> crawlAssets(String resourcePath, String resourceType) {

		Map<String, String> params = new HashMap<>();
		params.put("path", resourcePath);
		params.put("type", resourceType);
		params.put("p.offset", "0");
		params.put("p.limit", "10000");

		Session session = null;
		ResourceResolver resourceResolver = null;
		try{
			resourceResolver = CommonUtil.getResourceResolver(resolverFactory);
			session = resourceResolver.adaptTo(Session.class);
			Query query = queryBuilder.createQuery(PredicateGroup.create(params), session);
			SearchResult searchResults = query.getResult();
			LOG.info("Found '{}' matches for query", searchResults.getTotalMatches());
			if (resourceType.equalsIgnoreCase("dam:Asset")){
				return createAssetsMetadataArray(searchResults);
			}

		} catch (RepositoryException | LoginException e){
			LOG.error("Exception due to", e);
		}
		finally{
			if (session != null && session.isLive()){
				session.logout();
			}
		}
		return null;

	}

	/**
	 * This method takes search result of content pages and creates a JSON array object with properties
	 * 
	 * @param results
	 * @return
	 * @throws RepositoryException
	 */
	@Override
	public List<AssetsBean> createAssetsMetadataArray(SearchResult results) throws RepositoryException {
		List<AssetsBean> assetList = new ArrayList<>();
		for (Hit hit : results.getHits()){
			Resource pageContent = hit.getResource();
			ValueMap properties = pageContent.adaptTo(ValueMap.class);
			String isPageIndexable = properties.get("notsolrindexable", String.class);
			if (null != isPageIndexable && isPageIndexable.equals("true")) continue;
			AssetsBean aBean = createAssetsMetadataObject(pageContent);
			assetList.add(aBean);
		}

		return assetList;

	}

	/**
	 * This method creates JSONObject which has all the page metadata which is used to index in Solr server
	 * 
	 * @param It takes resource of type cq:PageContent to extract the page metadata
	 * @return Json object with page's metadata
	 */
	@Override
	public AssetsBean createAssetsMetadataObject(Resource assetContent) {

		AssetsBean assetBean = new AssetsBean();
		AssetModel assetModel = assetContent.adaptTo(AssetModel.class);
		assetBean.setCreatedBy(assetModel.getCreatedBy());
		assetBean.setCreatedDate(assetModel.getCreatedDate());
		assetBean.setDescription(assetModel.getDescription());
		assetBean.setLastModified(assetModel.getLastModified());
		assetBean.setTitle(assetModel.getTitle());
		return assetBean;
	}

	/**
	 * This method connects to the Solr server and indexes page content using Solrj api. This is used by bulk update handler (servlet)
	 * 
	 * @param Takes Json array and iterates over each object and index to solr
	 * @return boolean true if it indexes successfully to solr server, else false.
	 */
	@Override
	public boolean indexAssetsToSolr(List<AssetsBean> listIndexData, HttpSolrClient server) throws SolrServerException, IOException {
		if (null != listIndexData){

			for (int i = 0; i < listIndexData.size(); i++){
				AssetsBean assetBean = listIndexData.get(i);
				SolrInputDocument doc = createAssetsSolrDoc(assetBean);
				server.add(doc);
			}
			server.commit();
			return true;
		}

		return false;
	}

	/**
	 * This method connects to the Solr server and indexes page content using Solrj api. This is used by transport handler
	 * 
	 * @param Takes Json object and index to solr
	 * @return boolean true if it indexes successfully to solr server, else false.
	 */
	@Override
	public boolean indexAssetsToSolr(AssetsBean indexPageData, HttpSolrClient server) throws SolrServerException, IOException {
		if (null != indexPageData){
			SolrInputDocument doc = createAssetsSolrDoc(indexPageData);
			server.add(doc);
			server.commit();
			return true;
		}

		return false;
	}

	private SolrInputDocument createAssetsSolrDoc(AssetsBean assets) {
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("title", assets.getTitle());
		doc.addField("description", assets.getDescription());
		doc.addField("lastModified", assets.getLastModified());
		doc.addField("ceratedBy", assets.getCreatedBy());
		doc.addField("ceratedDate", assets.getCreatedDate());
		return doc;

	}

}
