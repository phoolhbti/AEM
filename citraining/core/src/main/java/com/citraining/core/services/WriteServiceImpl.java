package com.citraining.core.services;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citraining.core.utils.CommonUtil;

@Component (service = WriteService.class)
public class WriteServiceImpl implements WriteService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Reference
	private ResourceResolverFactory resolverFactory;

	@Activate
	@Override
	public void doAWriteOperation(ComponentContext ctx) {
		ResourceResolver resourceResolver = null;
		try{
			resourceResolver = CommonUtil.getResourceResolver(resolverFactory);
			Resource resource = resourceResolver.getResource("/content/citraining/jcr:content");
			if (null != resource){
				ValueMap valueMap = resource.getValueMap();
				log.info(valueMap.get("jcr:primaryType", ""));
				ModifiableValueMap modifiableValueMap = resource.adaptTo(ModifiableValueMap.class);
				if (null != modifiableValueMap){
					modifiableValueMap.put("myKey", "myValue");
					resourceResolver.commit();
					log.info("Successfully saved");
				}
			}
		} catch (LoginException | PersistenceException e){
			log.error("LoginException{}", e);
		}
		finally{
			if (resourceResolver != null && resourceResolver.isLive()){
				resourceResolver.close();
			}

		}
	}
}