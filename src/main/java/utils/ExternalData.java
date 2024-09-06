package utils;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import io.cucumber.core.internal.com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;

	public class ExternalData {

	    public static List<Map<String, String>> readJSON(String filePath) {
	        ObjectMapper objectMapper = new ObjectMapper();
	        List<Map<String, String>> data = null;
	        try {
	            data = objectMapper.readValue(new File(filePath), new TypeReference<List<Map<String, String>>>() {});
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
	}


