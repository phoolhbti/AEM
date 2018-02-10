package com.citraining.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citraining.core.config.PackageConfiguration;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.day.jcr.vault.fs.api.PathFilterSet;
import com.day.jcr.vault.fs.api.ProgressTrackerListener;
import com.day.jcr.vault.fs.config.DefaultWorkspaceFilter;
import com.day.jcr.vault.packaging.JcrPackage;
import com.day.jcr.vault.packaging.JcrPackageDefinition;
import com.day.jcr.vault.packaging.JcrPackageManager;
import com.day.jcr.vault.packaging.PackagingService;
import com.day.jcr.vault.util.DefaultProgressListener;

@Component (service = Servlet.class, property = { "sling.servlet.methods=get", "sling.servlet.paths=/bin/packagewithac", "metatype = true", "label = Package Manager with Associated Content", "description = Package Manager with Associated Content" })
@Designate (ocd = PackageConfiguration.class)
public class PackageWithAC extends SlingAllMethodsServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	private transient PackageConfiguration config;

	@Reference
	private transient SlingRepository repository;

	@Reference
	private transient QueryBuilder qbuilder;

	HashSet<String> nodePaths = new HashSet<>();

	@Override
	protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws ServletException, IOException {
		resp.getOutputStream().println("Your package mangaer is start....");
		try{
			/*
			 * Below piece of code will create a packge for the list of paths specified and we can get it from package manager.
			 */
			Session session = repository.loginAdministrative(null);
			resp.getOutputStream().println("Your package mangaer is got ....session");
			JcrPackageManager packageManager = (JcrPackageManager) PackagingService.getPackageManager(session);
			/*
			 * For 'create' method the parameter packageGroup is optional we can give group name under which the package should be created else it
			 * will take default, packageName is the name of the package and 1.0 is the version of the package
			 */
			resp.getOutputStream().println("Your package mangaer is got ....session pack..");
			JcrPackage pack = packageManager.create(config.getPackageGroupName(), config.getPackageName(), "1.0");
			JcrPackageDefinition definition = pack.getDefinition();
			resp.getOutputStream().println("Your package mangaer is got ....JcrPackageDefinition def..");
			DefaultWorkspaceFilter filter = new DefaultWorkspaceFilter();
			Node startingNode = session.getNode("/content/geometrixx/en_UK/products");
			nodePaths = associatedContent(startingNode, session);
			nodePaths.add(startingNode.getPath());
			/**
			 * nodePaths.add("/content/geometrixx/en_UK/products"); nodePaths.add("/content/geometrixx/en_UK/company"); nodePaths is the List
			 * containing the list of paths
			 */
			for (String nodePath : nodePaths){
				resp.getOutputStream().println("Node Path is" + nodePath);
				PathFilterSet pathFilterSet = new PathFilterSet();
				pathFilterSet.setRoot(nodePath);
				filter.add(pathFilterSet);
			}

			boolean autoSave = true;
			definition.setFilter(filter, autoSave);
			/**
			 * if autoSave is false then we have to explicitely save the session.
			 */

			ProgressTrackerListener listener = new DefaultProgressListener();
			packageManager.assemble(pack, listener);
			String resultMessage = "The package " + config.getPackageName() + " created sucessfully.";
			log.error("Exception{}", resultMessage);
		} catch (RepositoryException e){
			String errorMessage = e.getMessage();
			if (errorMessage.contains("NODE_ALREADY_EXISTS")){
				log.error("Exception{}", errorMessage);
			}
		}

		catch (Exception e){
			resp.getOutputStream().println("error is" + e);
			log.error("RepositoryException", e);
		}
	}

	@Activate
	protected void activate(PackageConfiguration config) {
		this.config = config;
	}

	private HashSet<String> associatedContent(Node node, Session session) {

		try{
			log.debug("with in start function...{}" + node.getPath());
			String[] arrofprop = new String[] { "fileReference" };
			for (int shopCopm = 0; shopCopm < arrofprop.length; shopCopm++){
				Map<String, String> map = new HashMap<>();
				map.put("path", node.getPath());
				map.put("type", "nt:unstructured");
				map.put("property", arrofprop[shopCopm]);
				Query query = qbuilder.createQuery(PredicateGroup.create(map), session);
				query.setStart(0);
				query.setHitsPerPage(10000);
				SearchResult result = query.getResult();
				Iterator<Node> childrensub = result.getNodes();
				log.debug("Before while...with in start function...");
				while (childrensub.hasNext()){
					log.debug("Before while...with in start function...");
					Node childrensubchild = childrensub.next();
					if (childrensubchild.hasProperty(arrofprop[shopCopm])){
						nodePaths.add(childrensubchild.getProperty(arrofprop[shopCopm]).getString());
					}
				}

			}
		} catch (RepositoryException e){
			log.debug("error is" + e);
		}
		return nodePaths;

	}
}
