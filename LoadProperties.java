package bom_operations;

import com.teamcenter.clientx.AppXSession;
//import com.teamcenter.schemas.soa._2006_03.base.ModelObject;
import com.teamcenter.soa.client.model.ModelObject;
import com.teamcenter.services.strong.core.DataManagementService;

public class LoadProperties {

	DataManagementService dms;

	
	public LoadProperties() {
		
		dms = DataManagementService.getService(AppXSession.getConnection());
		
		
	}
	
	public void loadProperties(ModelObject obj, String props) {
		
		ModelObject[] obj_array = new ModelObject[] {obj};
		String[] props_array = new String[] {props};
		
		dms.getProperties( obj_array, props_array);
		
	}
	
	
}
