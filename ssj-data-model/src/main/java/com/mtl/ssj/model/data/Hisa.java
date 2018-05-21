package com.mtl.ssj.model.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@Data
@Table(name = "hisa", catalog = "public")
@XmlRootElement
@NoArgsConstructor
@EqualsAndHashCode(of ={"id"})
public class Hisa implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "cod2000", nullable = false)
    private String cod2000;
    @Column(name = "year", nullable = false)
    private Integer year;
    @Column(name = "month", nullable = false)
    private Integer month;
    @Column(name = "day", nullable = false)
    private Integer day;
    @Column(name = "nom_lote", nullable = false)
    private String nomLote;
    @Column(name = "page_number", nullable = false)
    private Integer pageNumber;
    @Column(name = "record_number", nullable = false)
    private Integer recordNumber;
    @Column(name = "ficha_fam", nullable = false)
    private String fichaFam;
    @Column(name = "region_code", nullable = false)
    private String regionCode;
    @Column(name = "subregion_code", nullable = false)
    private String subregionCode;
    @Column(name = "district_code", nullable = false)
    private String districtCode;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "age_type", nullable = false)
    private String ageType;
    @Column(name = "gender", nullable = false)
    private String gender;
    @Column(name = "establishment", nullable = false)
    private String establishment;
    @Column(name = "service", nullable = false)
	private String service;
    @Column(name = "diagnose1")
    private String diagnose1;
    @Column(name = "lab_conf1")
    private String labConf1;
    @Column(name = "code1")
    private String code1;
    @Column(name = "diagnose2")
    private String diagnose2;
    @Column(name = "lab_conf2")
    private String labConf2;
    @Column(name = "code2")
    private String code2;
    @Column(name = "diagnose3")
    private String diagnose3;
    @Column(name = "lab_conf3")
    private String labConf3;
    @Column(name = "code3")
    private String code3;
    @Column(name = "diagnose4")
    private String diagnose4;
    @Column(name = "lab_conf4")
    private String labConf4;
    @Column(name = "code4")
    private String code4;
    @Column(name = "diagnose5")
    private String diagnose5;
    @Column(name = "lab_conf5")
    private String labConf5;
    @Column(name = "code5")
    private String code5;
    @Column(name = "diagnose6")
    private String diagnose6;
    @Column(name = "lab_conf6")
    private String labConf6;
    @Column(name = "code6")
    private String code6;
    @Column(name = "esta_reg",nullable = false)
    private String estaReg;
    @Column(name = "mt",nullable = false)
    private String mt;
    @Column(name = "dni",nullable = false)
    private String dni;
    @Column(name = "fi",nullable = false)
    private String fi;
    @Column(name = "et",nullable = false)
    private String et;
    @Column(name = "st",nullable = false)
    private String st;               
}
