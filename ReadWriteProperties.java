package bom_operations;

import com.teamcenter.soa.client.model.ModelObject;
import com.teamcenter.soa.exceptions.NotLoadedException;

public class ReadWriteProperties {

	
	public static String read_string_props(ModelObject obj, String props) {
		
		String value ="";
		LoadProperties lp = new LoadProperties();
		lp.loadProperties(obj, props);
		
		try {
			value = obj.getPropertyObject(props).getStringValue();
		} catch (NotLoadedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
		
	}
	
	public static ModelObject get_related_component(ModelObject obj, String props) {
		
		ModelObject object = null;
		LoadProperties lp = new LoadProperties();
		lp.loadProperties(obj, props);
		
		try {
			object = obj.getPropertyObject(props).getModelObjectValue();
		} catch (NotLoadedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
	
}
