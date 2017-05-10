package com.citraining.core.services;

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

import com.citraining.core.utils.CommonUtil;

@Service
@Component(immediate = true)
public class WriteServiceImpl implements WriteService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Reference
	private ResourceResolverFactory resolverFactory;

	@Activate
	public void doAWriteOperation(ComponentContext ctx) {
		ResourceResolver resourceResolver = null;
		try {
			resourceResolver = CommonUtil.getResourceResolver(resolverFactory);
			Resource resource = resourceResolver
					.getResource("/content/citraining/jcr:content");
			ValueMap valueMap = resource.getValueMap();
			log.info(valueMap.get("jcr:primaryType", ""));
			ModifiableValueMap modifiableValueMap = resource
					.adaptTo(ModifiableValueMap.class);
			if (null != modifiableValueMap) {
				modifiableValueMap.put("myKey", "myValue");
				resourceResolver.commit();
				log.info("Successfully saved");
			}
		} catch (LoginException e) {
			log.error("LoginException", e);
		} catch (PersistenceException e) {
			log.error("LoginException", e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resourceResolver != null && resourceResolver.isLive()) {
				resourceResolver.close();
			}

		}
	}
}