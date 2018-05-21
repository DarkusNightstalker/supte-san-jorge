package com.mtl.ssj.imp.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Function;

/**
 * Abstract class for any import on the SSJ project, be for excel or dbf files
 * @version 1.0
 * @author Jhoan Zevallos R
 * @param <T> the class of map
 */
public abstract class AbstractImport<T> {

    /**
     * the impot stream for our
     */
    protected ByteArrayInputStream byteArrayInputStream;
    private final static Map<Class,Map<Class,Function>> IMPORT_CONVERTERS;
    static{
        Map<Class,Map<Class,Function>> converters = new HashMap<>();
        Map<Class,Function> bigDecimalConverter = new HashMap<>();
        bigDecimalConverter.put(Integer.class,bigDecimal->{
            return ((BigDecimal)bigDecimal).intValue();
        });
        bigDecimalConverter.put(Short.class,bigDecimal->{
            return ((BigDecimal)bigDecimal).shortValue();
        });
        converters.put(BigDecimal.class,bigDecimalConverter);
        IMPORT_CONVERTERS = Collections.unmodifiableMap(converters);
    }

    /**
     * @param fileData
     * @throws IllegalArgumentException
     */
    protected AbstractImport(byte[] fileData)  throws IllegalArgumentException{
        byteArrayInputStream = new ByteArrayInputStream(fileData);
//        if(!isValidFile()){
//            throw new IllegalArgumentException("Invalid file was selected for imported");
//        }
    }

    public void changeInput(byte[] fileData) throws IOException {
        close();
        if(byteArrayInputStream != null) byteArrayInputStream.close();
        byteArrayInputStream = new ByteArrayInputStream(fileData);

        if(!isValidFile()){
            throw new IllegalArgumentException("Invalid File");
        }
    }

    private boolean isValidFile(){
        String fileType = getConfiguration().get("fileType").asText();
        String mimetype = "dbf";
        return fileType.equals(mimetype);
    }

    protected abstract JsonNode getConfiguration();
    public abstract void open() throws IOException;
    public abstract void close() throws IOException;

    /**
     * Validate the object record , if the record is not valid, the algorithm catch a exception with the field error message
     * @param record - The record that will be examinated
     * @throws Exception - Any exception for the not valid fields
     */
    protected abstract void validateRecord(T record) throws Exception;

    /**
     * Count all the records on this current import
     * @return The total records count
     * @throws IOException - Catch when the internal iterator not be initialize
     */
    public abstract Integer countData() throws IOException;

    /**
     * @param log
     * @return
     * @throws IOException - Catch when the internal iterator not be initialized
     * @throws ParseException - Catch when the field type of the import not be the same of the class type
     * @throws ReflectiveOperationException - Catch when the configuration property file not yet appropriately configured
     */
    public abstract List<T> readData(ImportLog log) throws IOException,ParseException,ReflectiveOperationException;


    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    protected  Object convertValue(Class classFound,String typeTarget,Object source) throws NullPointerException,ClassNotFoundException{
        if(classFound.getName().equals(typeTarget)) return source;
        return IMPORT_CONVERTERS
                .get(classFound)
                .get(Class.forName(typeTarget))
                .apply(source);
    }


}
