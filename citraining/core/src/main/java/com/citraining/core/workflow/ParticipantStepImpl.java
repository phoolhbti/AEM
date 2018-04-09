package com.citraining.core.workflow;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.HistoryItem;
import com.adobe.granite.workflow.exec.ParticipantStepChooser;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.Workflow;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component (immediate = true, service = ParticipantStepChooser.class, property = { "chooser.label=Citraining Workflow Dynamic Participant Chooser" })
public class ParticipantStepImpl implements ParticipantStepChooser {
	private static final Logger logger = LoggerFactory.getLogger(ParticipantStepImpl.class);
@Override
	public String getParticipant(WorkItem workItem, WorkflowSession wfSession, MetaDataMap metaDataMap) throws WorkflowException {
		logger.info("################ Inside the SampleProcessStepChooserImpl GetParticipant ##########################");	
		  String initiator = workItem.getWorkflow().getInitiator();
		  logger.info("Assigning Dynamic Participant Step work item to {}",initiator);
		Workflow wf = workItem.getWorkflow();
		List<HistoryItem> wfHistory = wfSession.getHistory(wf);
		if (!wfHistory.isEmpty()){
			return "administrators";
		} else{
			return "admin";
		}
	}
}