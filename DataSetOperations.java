package bom_operations;

import com.teamcenter.clientx.AppXSession;
//import com.teamcenter.schemas.core._2006_03.filemanagement.DatasetFileInfo;
import com.teamcenter.services.strong.core.DataManagementService;
//import com.teamcenter.services.loose.core._2006_03.DataManagement.CreateDatasetsResponse;
import com.teamcenter.services.loose.core._2006_03.FileManagement.GetDatasetWriteTicketsInputData;
import com.teamcenter.services.strong.core._2008_06.DataManagement.DatasetProperties2;
import com.teamcenter.soa.client.FileManagementUtility;
import com.teamcenter.soa.client.model.strong.Dataset;
import com.teamcenter.services.loose.core.FileManagementService;
import com.teamcenter.services.loose.core._2006_03.FileManagement.DatasetFileInfo;

public class DataSetOperations {

	
	DataManagementService dms;
	FileManagementUtility fs;
	
	public DataSetOperations(DataManagementService s) {
		this.dms = s;
		fs = new FileManagementUtility(AppXSession.getConnection());
	}
	
	public Dataset create_Dataset(String name, String desc) {
		
		Dataset d1= null;
		DatasetProperties2 dsp  = new DatasetProperties2();
		dsp.name = name;
		dsp.description = desc;
		dsp.type = "pdf";
		DatasetProperties2[] array = new DatasetProperties2[] {dsp};
		
		
		com.teamcenter.services.strong.core._2006_03.DataManagement.CreateDatasetsResponse resp = this.dms.createDatasets2(array);
		d1 = (Dataset) resp.serviceData.getCreatedObject(0);
		
		return d1;
		
	}
	
	public void upload_file_in_dataset(Dataset d1,String filepath) {
		
		DatasetFileInfo info = new DatasetFileInfo();
		info.fileName = filepath;
		info.namedReferencedName = "PDF_Reference";
		
		
		DatasetFileInfo[] array = new DatasetFileInfo[] {info};
		
		GetDatasetWriteTicketsInputData ticket = new GetDatasetWriteTicketsInputData();
		ticket.dataset = d1;
		ticket.datasetFileInfos = array;
		
		GetDatasetWriteTicketsInputData[] tkt = new GetDatasetWriteTicketsInputData[] {};
		
		fs.putFiles(tkt);
	}
	
	
	
	
	
}
