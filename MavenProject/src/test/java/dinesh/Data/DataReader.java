package dinesh.Data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	
	//public List<HashMap<String, String>> getJsonDataToMap() throws IOException
	{
		//reading json to sting
     //  String jsoncontent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//dinesh//Data//PurchaseOrder.json").StandardCharsets.UTF_8);
	//string to hashmap jackson databind
				
				ObjectMapper mapper=new ObjectMapper();
				
			//	List<HashMap<String, String>> data=mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String, String>>>(){});
			//	return data;
				}
	
	
	}

