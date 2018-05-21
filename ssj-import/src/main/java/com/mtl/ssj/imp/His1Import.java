package com.mtl.ssj.imp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtl.ssj.imp.util.AbstractImport;
import com.mtl.ssj.imp.util.ImportLog;
import net.iryndin.jdbf.core.DbfRecord;
import net.iryndin.jdbf.reader.DbfReader;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import com.mtl.ssj.model.data.His1;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 */
public final class His1Import extends AbstractImport<His1> {
    private final static JsonNode CONFIGURATION;
    static{
        JsonNode his1ConfigTemp =null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            his1ConfigTemp = mapper.readTree(His1Import.class.getClassLoader().getResourceAsStream("com/mtl/ssj/imp/configuration/config-his1.json"));
        }catch(IOException e){
            e.printStackTrace();
        }
        CONFIGURATION = his1ConfigTemp;
    }

    private DbfReader reader;

    public His1Import(byte[] fileData) throws IllegalArgumentException {
        super(fileData);
    }

    @Override
    protected  JsonNode getConfiguration() {
        return CONFIGURATION;
    }

    @Override
    public void open() throws IOException {
        reader = new DbfReader(super.byteArrayInputStream);
    }

    @Override
    public void close() throws IOException {
        if (reader != null) reader.close();
        if (super.byteArrayInputStream != null) super.byteArrayInputStream.close();
    }


    @Override
    protected void validateRecord(His1 record) throws Exception {

        if(StringUtils.isBlank(record.getCod2000())){
            throw new Exception("The Field Cod2000 is blank");
        }
        if(!StringUtils.isNumeric(record.getCod2000())){
            throw new Exception("The Field Cod2000 is not numeric");
        }
        if(StringUtils.length(record.getCod2000()) > 9){
            throw new Exception("The Field Cod2000 is too big");
        } else if(StringUtils.length(record.getCod2000()) < 9){
            record.setCod2000( StringUtils.leftPad(record.getCod2000(),9,"0") );
        }

        if(record.getYear() <= 0){
            throw new Exception("The Field Year must be greater than 0");
        }
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if(record.getYear() > currentYear){
            throw new Exception("The Field Year must be left than current year "+currentYear);
        }


        if(record.getMonth() < 1 || record.getMonth() > 12 ){
            throw new Exception("The Field Month must be between 1 and 12");
        }
    }

    @Override
    public Integer countData() throws IOException{
        return reader.getMetadata().getRecordsQty();
    }

    @Override
    public List<His1> readData(ImportLog log) throws IOException,ParseException,ReflectiveOperationException{
        DbfRecord record;
        List<His1> result = new ArrayList<>();
        int i;
        JsonNode fields = CONFIGURATION.get("fields");
        while ((record = reader.read())!= null){
            Map<String,Object> recordMap = record.toMap();
            His1 item = new His1();
            Iterator<String> fieldsNames = fields.fieldNames();
            while(fieldsNames.hasNext()){
                String fieldName = fieldsNames.next();
                Object object = recordMap.get(fieldName);
                JsonNode currentField = fields.get(fieldName);
                if(object != null) {
                    try {
                        object = convertValue(object.getClass(),currentField.get("type").get("target").asText(), object);
                    }catch (NullPointerException  | ClassNotFoundException ce){
                        throw new ParseException("Cannot converted '"+object.getClass().getName()+"' to '"+currentField.get("type").get("target").asText()+"' ",1);
                    }
                    item
                            .getClass()
                            .getMethod("set" + StringUtils.capitalize(currentField.get("classField").asText()), object.getClass())
                            .invoke(item, object);
                }
            }
            try{
                validateRecord(item);
                result.add(item);
            }catch(Exception e){

            }
        }
        return result;
    }

}
