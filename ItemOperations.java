package bom_operations;

import com.teamcenter.clientx.AppXSession;
import com.teamcenter.schemas.soa._2006_03.base.ModelObject;
import com.teamcenter.services.strong.core.DataManagementService;
import com.teamcenter.services.strong.core._2006_03.DataManagement.CreateItemsResponse;
import com.teamcenter.services.strong.core._2006_03.DataManagement.ItemProperties;
import com.teamcenter.services.strong.core._2007_01.DataManagement.GetItemFromIdInfo;

public class ItemOperations {

	DataManagementService dms;
	
	public ItemOperations() {
		
		 dms = DataManagementService.getService(AppXSession.getConnection());
	}
	
	
	public ModelObject find_item_with_id(String itemid) {
		
		ModelObject item = null;
		
		GetItemFromIdInfo info = new GetItemFromIdInfo();
		info.itemId = itemid;
		
		GetItemFromIdInfo[] infoarray = new GetItemFromIdInfo[1];
		infoarray[0] = new GetItemFromIdInfo();
		infoarray[0] = info;
		
		dms.getItemFromId(infoarray, 0, null);
		
		
		return item;
		
	}
	
	public ModelObject create_item(String itemid, String name, String desc, String type) {
		
		ModelObject item = null;
		
		ItemProperties props = new ItemProperties();
		props.clientId = itemid;
		props.itemId = itemid;
		props.name = name;
		props.description = desc;
		props.type = "Item";
		
		ItemProperties[] item_props_array  = new  ItemProperties[1];
		item_props_array[0] = new ItemProperties();
		item_props_array[0] = props;
		
		
		CreateItemsResponse resp = dms.createItems(item_props_array, null, null);
		item = (ModelObject) resp.serviceData.getCreatedObject(0);
		
		return item;
	}
	
	
	
}
