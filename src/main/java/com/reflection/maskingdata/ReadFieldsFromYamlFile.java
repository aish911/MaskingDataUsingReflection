package com.reflection.maskingdata;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

@Component
public class ReadFieldsFromYamlFile {
	
	private Set<String> fieldsToMask = new HashSet<>();

    public Set<String> getFieldsToMask()
    {
        return fieldsToMask;
    }
    public void setFieldsToMask(Set<String> fieldsToMask)
    {
        this.fieldsToMask = fieldsToMask;
    }

    public void setOfFieldsToMask(){

    	// Way to read masking fields dynamically from configurable Yaml file.
    	
        /*InputStream inputStream;
            try {
                inputStream = this.getClass()
                        .getClassLoader()
                        .getResourceAsStream("mask-fields.yml");
                Yaml yaml = new Yaml();
                Map<Object, List<String>> data = yaml.load(inputStream);
                for(Map.Entry<Object, List<String>> temp : data.entrySet()){
                    for(String listVal : temp.getValue()){
                        fieldsToMask.add(listVal);
                    }

                }
                setFieldsToMask(fieldsToMask);
                System.out.println("Fields to mask : " + getFieldsToMask());
            } catch (Exception e) {
                e.printStackTrace();
            }*/
    	
    	// hardcoded values for POC purpose    	
    	fieldsToMask.add("userId");
    	fieldsToMask.add("firstName");
    	fieldsToMask.add("lastName");
    	fieldsToMask.add("addressLine1");
    	fieldsToMask.add("city");
    	fieldsToMask.add("state");
    	fieldsToMask.add("pincode");
    	fieldsToMask.add("country");
    	fieldsToMask.add("cardNumber");
    	fieldsToMask.add("cvv");
    	fieldsToMask.add("expDate");
    	setFieldsToMask(fieldsToMask);
    }

}
