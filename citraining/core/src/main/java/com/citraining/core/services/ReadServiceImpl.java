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

@Component (service = ReadService.class)
public class ReadServiceImpl implements ReadService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Reference
	private ResourceResolverFactory resolverFactory;

	@Activate
	@Override
	public void doAReadOperation(ComponentContext ctx) {

		ResourceResolver resolver = null;
		try{
			resolver = CommonUtil.getResourceResolver(resolverFactory);
			log.info("User ID{}",resolver.getUserID());
			Resource resource = resolver.getResource("/content/citraining/jcr:content");
			if (null != resource){
				ValueMap readMap = resource.getValueMap();
				log.info(readMap.get("jcr:primaryType", ""));
				ModifiableValueMap modMap = resource.adaptTo(ModifiableValueMap.class);
				if (modMap != null){
					modMap.put("myKey", "myValue");
					resolver.commit();
					log.info("Successfully saved");
				}
			}
		} catch (LoginException | PersistenceException e){
			log.error("LoginException{}", e);
		}
		finally{
			if (resolver != null && resolver.isLive()){
				resolver.close();
			}

		}
	}
}