package bom_operations;

import java.util.HashMap;

import com.teamcenter.clientx.AppXSession;
import com.teamcenter.schemas.soa._2006_03.exceptions.ServiceException;

import com.teamcenter.services.strong.core.DataManagementService;
import com.teamcenter.services.strong.query.SavedQueryService;
import com.teamcenter.services.strong.query._2006_03.SavedQuery;
import com.teamcenter.services.strong.query._2006_03.SavedQuery.GetSavedQueriesResponse;
import com.teamcenter.services.strong.query._2006_03.SavedQuery.SavedQueryObject;
import com.teamcenter.services.strong.query._2007_06.SavedQuery.ExecuteSavedQueriesResponse;
import com.teamcenter.services.strong.query._2007_06.SavedQuery.SavedQueryInput;
import com.teamcenter.services.strong.query._2007_06.SavedQuery.SavedQueryResults;
import com.teamcenter.services.strong.query._2007_09.SavedQuery.QueryResults;
import com.teamcenter.services.strong.query._2007_09.SavedQuery.SavedQueriesResponse;
import com.teamcenter.services.strong.query._2008_06.SavedQuery.QueryInput;

import com.teamcenter.soa.client.model.ModelObject;
import com.teamcenter.soa.client.model.strong.ImanQuery;

public class QueryItems {

	
	DataManagementService dms;
	SavedQueryService svc;
	
	
	
	public QueryItems() {
		
		dms = DataManagementService.getService(AppXSession.getConnection());
		svc = SavedQueryService.getService(AppXSession.getConnection());
		
	
	}
	
	
	public ModelObject[] execute_query(String queryname, String[] entry, String[] values, int entry_no) {
		
		ImanQuery myquery = null;
		ModelObject[] results = null;
		try {
			
			GetSavedQueriesResponse resp = svc.getSavedQueries();
			SavedQueryObject[] list = resp.queries;
			
			for(int i=0;i<list.length;i++) {
				
				String qname = list[i].name;
				if(qname.equals(queryname)) {
					
					myquery = list[i].query;
					break;
				}
				else {
					continue;
				}
				
			}
			
			
			if(myquery!=null) {
				
				SavedQueryInput input = new SavedQueryInput();
				input.query = myquery;
				input.maxNumToReturn = 25;
				input.limitList = new ModelObject[0];
				input.entries = entry;
				input.values = values;
				
				SavedQueryInput[] input_array = new SavedQueryInput[1];
				input_array[0] = new SavedQueryInput();
				input_array[1] = input;
				
				ExecuteSavedQueriesResponse r = svc.executeSavedQueries(input_array);
				SavedQueryResults r1 = r.arrayOfResults[0];
				
				 results = r1.objects;
				  
			}
			
		
		
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;
	}
	
	
}
