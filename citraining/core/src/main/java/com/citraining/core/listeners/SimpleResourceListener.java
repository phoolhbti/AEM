package com.citraining.core.listeners;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citraining.core.utils.CommonUtil;
import com.drew.lang.annotations.NotNull;

/**
 * @author Phool Chandra A service to demonstrate how changes in the node tree can be listened for. It registers an event Listener service. The
 *         component is activated immediately after the bundle is started through the immediate flag. Please note, that apart from EventListener
 *         services, the immediate flag should not be set on a service. Event Listener work at JCR level with ObservationManager, means it will
 *         responds to events that occur at the JCR level An EventListener can be registered via the ObservationManager object. Event listeners are
 *         notified asynchronously, and see events after they occur and the transaction is committed. An event listener only sees events for which the
 *         session that registered it has sufficient access rights.
 */
@Component (immediate = true, service = EventListener.class)
public class SimpleResourceListener implements EventListener {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@NotNull
	private Session adminSession;

	@Reference
	private SlingRepository repository;

	@Reference
	private ResourceResolverFactory resolverFactory;

	@Activate
	public void activate(ComponentContext context) {
		logger.info("activating ExampleObservation");
		try{
			ResourceResolver resolver = CommonUtil.getResourceResolver(resolverFactory);
			adminSession = resolver.adaptTo(Session.class);			
				adminSession.getWorkspace().getObservationManager().addEventListener(	this, // handler
																						Event.PROPERTY_ADDED | Event.NODE_ADDED, // binary combination
																																	// of event types
																						"/apps/citraining", // path
																						true, // is Deep?
																						null, // uuids filter
																						null, // nodetypes filter
																						false);
		
		} catch (RepositoryException | LoginException e){
			logger.error("unable to register session", e);
		}
	}

	@Deactivate
	public void deactivate() {
		if (adminSession != null && adminSession.isLive()){
			adminSession.logout();
		}
	}

	@Override
	public void onEvent(EventIterator eventIterator) {
		try{
			while (eventIterator.hasNext()){
				logger.info("something has been added : {}", eventIterator.nextEvent().getPath());
			}
		} catch (RepositoryException e){
			logger.error("Error while treating events{}", e);
		}
	}
}