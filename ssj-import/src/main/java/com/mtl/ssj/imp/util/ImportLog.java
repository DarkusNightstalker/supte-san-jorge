package com.mtl.ssj.imp.util;

import lombok.Data;

@Data
public class ImportLog implements  java.io.Serializable{

    private Integer currentReadRows;
    private String message;

    public ImportLog() {
        currentReadRows = 0;
    }
}
