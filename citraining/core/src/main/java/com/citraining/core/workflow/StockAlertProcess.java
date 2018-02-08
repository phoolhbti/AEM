package com.citraining.core.workflow;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.jcr.ItemNotFoundException;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

/**
 * @author Phool The component is activated immediately after the bundle is started through the immediate flag.
 */
@Component (metatype = false)
@Service
@Property (name = "process.label", value = "Stock Threshold Checker")
public class StockAlertProcess implements WorkflowProcess {
	private static final String PROPERTY_LAST_TRADE = "lastTrade";

	private static final String TYPE_JCR_PATH = "JCR_PATH";

	private static final String TYPE_JCR_UUID = "JCR_UUID";

	private static final Logger LOGGER = LoggerFactory.getLogger(StockAlertProcess.class);

	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap args) throws WorkflowException {
		LOGGER.info("Starting point of execute method...");
		try{
			WorkflowData workflowData = workItem.getWorkflowData();
			Session session = workflowSession.adaptTo(Session.class);
			Node node = null;
			if (workflowData.getPayloadType().equals(TYPE_JCR_PATH)){
				String path = workflowData.getPayload().toString() + "/jcr:content";
				LOGGER.info("path{}", path);
				node = (Node) session.getItem(path);

			} else if (workflowData.getPayloadType().equals(TYPE_JCR_UUID)){
				node = session.getNodeByIdentifier(workflowData.getPayload().toString());

			}
			if (null != node){
				LOGGER.info("running with node{}", node.getPath());
				String symbol = node.getParent().getName();
				LOGGER.info("found symbol {}", symbol);
				if (node.hasProperty(PROPERTY_LAST_TRADE)){
					Double lastTrade = node.getProperty(PROPERTY_LAST_TRADE).getDouble();
					LOGGER.info("last trade was{}", lastTrade);
					Iterator<String> arguIterator = Arrays.asList(Pattern.compile("\n").split(args.get("PROCESS_ARGS", ""))).iterator();
					while (arguIterator.hasNext()){
						List<String> currentArgmentLine = Arrays.asList(Pattern.compile("=").split(arguIterator.next()));
						String currentSymbol = currentArgmentLine.get(0);
						Double currentLimit = new Double(currentArgmentLine.get(1));
						if (currentSymbol.equalsIgnoreCase(symbol) && currentLimit < lastTrade){
							LOGGER.warn("stock alert!!!!!{} is over {}", symbol, currentLimit);
						}
					}
				}
			}
		} catch (ItemNotFoundException e){
			LOGGER.error("ItemNotFoundException", e);
		} catch (RepositoryException e){
			LOGGER.error("RepositoryException", e);
		}
	}

}
