package com.citraining.core.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Component(immediate = true)
public class ReadServiceImpl implements ReadService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Reference
	private ResourceResolverFactory resolverFactory;

	// If you are planning to use repository session
	// @Reference
	// private SlingRespository repository;
	// private Session session = null;

	@Activate
	public void doAReadOperation(ComponentContext ctx) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(ResourceResolverFactory.SUBSERVICE, "readService");
		ResourceResolver resolver = null;
		try {
			// Deprecated Method using admin resolver
			// resolver =
			// resolverFactory.getAdministrativeResourceResolver(null);
			resolver = resolverFactory.getServiceResourceResolver(param);
			// If you are planning to use repository session instead of
			// repository.loginAdministrative
			// session = repository.getService("readService",null);
			log.info(resolver.getUserID());
			Resource res = resolver
					.getResource("/content/citraining/jcr:content");
			ValueMap readMap = res.getValueMap();
			log.info(readMap.get("jcr:primaryType", ""));
			ModifiableValueMap modMap = res.adaptTo(ModifiableValueMap.class);
			if (modMap != null) {
				modMap.put("myKey", "myValue");
				resolver.commit();
				log.info("Successfully saved");
			}
		} catch (LoginException e) {
			log.error("LoginException", e);
		} catch (PersistenceException e) {
			log.error("LoginException", e);
		} finally {
			if (resolver != null && resolver.isLive()) {
				resolver.close();
			}
			// Close session if you are using session
		}
	}
}