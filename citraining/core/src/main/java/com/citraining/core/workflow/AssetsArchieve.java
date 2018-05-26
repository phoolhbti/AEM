package com.citraining.core.workflow;

import java.io.InputStream;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.adobe.granite.workflow.model.WorkflowNode;
import com.citraining.core.utils.CommonUtil;
import com.day.cq.dam.api.AssetManager;

@Component (service = WorkflowProcess.class, property = { "process.label=Citraining AssetsArchieve Workflow Process" })
public class AssetsArchieve implements WorkflowProcess {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Reference
	private ResourceResolverFactory resolverFactory;

	@Override
	public void execute(WorkItem item, WorkflowSession wfsession, MetaDataMap args) throws WorkflowException {

		try{
			// Get the Assets from the file system for a test
			WorkflowNode myNode = item.getNode();
			String myTitle = myNode.getTitle(); // returns the title of the workflow step

			log.info("**** The title is {}", myTitle);

			WorkflowData workflowData = item.getWorkflowData(); // gain access to the payload data
			String path = workflowData.getPayload().toString();// Get the path of the asset

			// Get only the name of the asset - including the ext
			int index = path.lastIndexOf('/');
			String fileName = path.substring(index + 1);

			// Write the Asset to the Trash folder in the DAM
			String myFile = writeToDam(path, fileName, wfsession);// write to the Trash location

			log.info("**** This was was written to the Trash folder{} ", myFile);
		}

		catch (Exception e){
			log.error("Exception{}", e);
		}
	}

	// Place the Asset into the AEM DAM using AssetManager API
	private String writeToDam(String path, String fileName, WorkflowSession wfsession) {
		try{
			// Inject a ResourceResolver - make sure to whitelist the bundle
			Session session = wfsession.adaptTo(Session.class);
			ResourceResolver resourceResolver = CommonUtil.getResourceResolver(resolverFactory);
			// ResourceResolver resourceResolver = resolverFactory.getResourceResolver()
			// Remove the first / char - JCR API does not like that
			String newPath = path.replaceFirst("/", "");

			// USE JCR API TO get the Asset Data so we can move it to another JCR location
			Node root = session.getRootNode();

			// Append the path where the Asset data is stored
			String dataPath = newPath + "/jcr:content/renditions/original/jcr:content";

			// Get the InputStream of the Asset
			Node dataPathNode = root.getNode(dataPath);
			InputStream content = dataPathNode.getProperty("jcr:data").getStream();

			// Use AssetManager to place the file into the AEM DAM
			AssetManager assetMgr = resourceResolver.adaptTo(AssetManager.class);
			String newFile = "/content/dam/trash/" + fileName;
			if (null != assetMgr){
				assetMgr.createAsset(newFile, content, "image/jpeg", true);
			}
			return fileName;
		} catch (Exception e){
			log.error("**** Error: {}", e);
		}
		return "";
	}
}