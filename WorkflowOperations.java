package bom_operations;

import com.teamcenter.soa.client.model.ModelObject;
import com.teamcenter.soa.client.model.strong.ItemRevision;

import java.util.Arrays;

import com.teamcenter.clientx.AppXSession;

import com.teamcenter.services.strong.workflow.WorkflowService;
import com.teamcenter.services.strong.workflow._2008_06.Workflow.ContextData;


public class WorkflowOperations {

	
	ItemRevision itemrev;
	WorkflowService svc;
	
	public WorkflowOperations(ItemRevision itr) {
		
		svc = WorkflowService.getService(AppXSession.getConnection());
		this.itemrev = itr;
	}
	
	public void raise_wfo(String templatename) {
		
		
		String itemrev_uid = this.itemrev.getUid();
		String rev_master_form_uid = ReadWriteProperties.get_related_component((ModelObject)itemrev, "IMAN_master_form_rev").getUid();
		String nx_dataset_uid = ReadWriteProperties.get_related_component((ModelObject) itemrev, "IMAN_specification").getUid();
		
		String uid_array[] = new String[3];
		uid_array[0] = itemrev_uid;
		uid_array[1] = rev_master_form_uid;
		uid_array[2] = nx_dataset_uid;
		
		int atachmenttypes[] = new int[] {3};
		Arrays.fill(atachmenttypes, 1);
		
		ContextData ctx = new ContextData();
		ctx.processTemplate = templatename;
		ctx.attachmentTypes = atachmenttypes;
		ctx.attachments = uid_array;
		ctx.attachmentCount = 3;
		
		
		svc.createInstance(true, null, "name", "subject", "desc", ctx);
	}
	
}
