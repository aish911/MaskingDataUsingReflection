package com.reflection.maskingdata;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MaskSensitiveData {
	
	public Object maskData(Object object) {
        try {
        	ReadFieldsFromYamlFile readFieldsFromYamlFile = new ReadFieldsFromYamlFile();
        	readFieldsFromYamlFile.setOfFieldsToMask();
            Field[] fields = object.getClass().getDeclaredFields();
            System.out.println("Field Array :: " + Arrays.asList(fields));
            Object value = null;
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                value = fields[i].get(object);
                System.out.println("Value :: " + value + ", name :: " + fields[i].getName());
                if (value != null) {
                    Class<?> type = fields[i].getType();
                    if (java.util.List.class.isAssignableFrom(type)) {
                        Method getS = value.getClass().getMethod("size", null);
                        int size = (int) getS.invoke(value);
                        for (int k = 0; k < size; k++) {
                            Method getM = value.getClass().getMethod("get", int.class);
                            Object actualObj = getM.invoke(value, k);
                            if(actualObj instanceof String){
                                maskListOfStrings(object, fields[i]);
                                break;
                            }
                            maskData(actualObj);
                        }
                    } else if (/*type.getCanonicalName().startsWith(CommonConstants.RISK_PACKAGE)
                            || type.getCanonicalName().startsWith(CommonConstants.COMPL_PACKAGE)
                            || type.getCanonicalName().startsWith(CommonConstants.RISK_PACKAGE)*/
                    		type.getCanonicalName().startsWith("com.reflection") ) {
                        maskData(value);

                    } else if (type.equals(String.class) || Date.class.isAssignableFrom(type)
                            || type.equals(BigDecimal.class) || type.equals(BigInteger.class)
                            || type.equals(Boolean.class)) {
                        if (readFieldsFromYamlFile.getFieldsToMask().contains(fields[i].getName())) {
                            if (Date.class.isAssignableFrom(type)) {
                                fields[i].set(object, convertStringDateToGregorian("9999-01-01"));
                            } else if(type.equals(BigDecimal.class)){
                                fields[i].set(object,null);
                            }else if(type.equals(BigInteger.class)){
                                fields[i].set(object,null);
                            }else if(type.equals(Boolean.class)){
                                fields[i].set(object, Boolean.parseBoolean("*****"));
                            }else {
                                fields[i].set(object, "*****");
                            }
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
        return object;
    }

    public void maskListOfStrings(Object obj ,Field field){
        try {
            List<String> listToModify = (List<String>) field.get(obj);
            for(int i=0; i<listToModify.size(); i++){
                listToModify.set(i,"*****");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Date convertStringDateToGregorian(String inputStringDate) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (inputStringDate.isEmpty() || null == inputStringDate) {
            date = null;
        } else {
            try {
                date = sdf.parse(inputStringDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return date;

    }
}
