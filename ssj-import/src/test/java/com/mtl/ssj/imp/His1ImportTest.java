package com.mtl.ssj.imp;

import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class His1ImportTest {
    @Test
    public void open() throws Exception {
    }

    @Test
    public void close() throws Exception {
    }

    @Test
    public void validateRecord() throws Exception {
    }

    @Test
    public void countData() throws Exception {
    }

    @Test
    public void readData() throws Exception {
        His1Import imp = new His1Import(Files.readAllBytes(new File("D:\\Proyectos\\Supte San Jorge\\Diciembre 2017\\Data\\his1.dbf").toPath()));
        imp.open();
        System.out.println(imp.countData());
        imp.readData(null).forEach(System.out::println);
    }

}