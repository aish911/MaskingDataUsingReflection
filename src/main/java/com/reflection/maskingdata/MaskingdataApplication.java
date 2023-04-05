package com.reflection.maskingdata;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.reflection.model.AccountDetail;

@SpringBootApplication
public class MaskingdataApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MaskingdataApplication.class, args);
		MaskSensitiveData maskData = new MaskSensitiveData();
		
		StringBuilder contentBuilder = new StringBuilder();
        try (Stream stream = Files.lines(
                Paths.get(
                        "C:\\Users\\aishwarya_deshmukh\\eclipse-workspace\\maskingdata\\src\\main\\resources\\AccountDetail.json"),
                StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Create GSON object
        //apply NullSearialization and Pretty formatting by GSON Builder
        Gson gson = getJsonBuilder().create();
        AccountDetail accounDetail = gson.fromJson(contentBuilder.toString(), AccountDetail.class);
        System.out.println(gson.toJson(maskData.maskData(accounDetail)));
	}
	
	public static GsonBuilder getJsonBuilder()
    {
        GsonBuilder builder = new GsonBuilder();
 
        // Setting for formatted output and serialize null value
        builder.setPrettyPrinting().serializeNulls();
 
        return builder;
    }

}
