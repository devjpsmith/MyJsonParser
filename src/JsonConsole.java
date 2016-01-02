import java.io.StringReader;
import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.JsonWriter;

/**
 * A simple application to demonstrate creating and parsing JSON data
 * @author James
 *
 */
public class JsonConsole {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// create some initial values
		MyObjectModel[] dataArray = new MyObjectModel[3];
		for(int i = 0; i < 3; i++){
			MyObjectModel data = new MyObjectModel();
			data.ID = i;
			data.Value = "Round " + i;
			data.Deleted = false;
			dataArray[i] = data;
		}

		// create the JSON data		
		String s = createString(dataArray);

		// parse it back to a string 
		parseString(s);
	}
	
	private static String createString(MyObjectModel[] dataArray){
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		
		for(MyObjectModel model : dataArray){
			JsonObject obj = Json.createObjectBuilder()
					.add("ID", model.ID)
					.add("Value", model.Value)
					.add("Deleted", model.Deleted)
					.build();
			jsonArrayBuilder.add(obj);
		}
		
		StringWriter sw = new StringWriter();
		JsonWriter jsonWriter = Json.createWriter(sw);
		jsonWriter.writeArray(jsonArrayBuilder.build());
		jsonWriter.close();
		
		System.out.println(sw);
		return sw.toString();
	}
	
	private static void parseString(String data){
		StringReader sr = new StringReader(data);
		JsonReader jsonReader = Json.createReader(sr);
		JsonStructure jStruct = jsonReader.read();
		JsonArray dataArray = (JsonArray) jStruct;
		
		for(JsonValue val : dataArray){
			JsonObject obj = (JsonObject)val;
			System.out.println("===================");
			System.out.println("ID\t:" + obj.getInt("ID"));
			System.out.println("Value\t:" + obj.getString("Value"));
			System.out.println("Deleted\t:" + obj.getBoolean("Deleted"));
			System.out.println("===================");
		}
	}

}
