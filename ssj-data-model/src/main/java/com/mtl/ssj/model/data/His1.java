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
@Table(name = "his1", catalog = "public")
@XmlRootElement
@NoArgsConstructor
@EqualsAndHashCode(of ={"id"})
public class His1 implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "cod2000", nullable = false)
    private String cod2000;
    @Column(name = "year", nullable = false)
    private Integer year;
    @Column(name = "month", nullable = false)
    private Integer month;
    @Column(name = "nom_lote", nullable = false)
    private String nomLote;
    @Column(name = "page_number", nullable = false)
    private Integer pageNumber;
    @Column(name = "codif", nullable = false)
    private String codif;
    @Column(name = "cod_servsa", nullable = false)
    private String codServsa;
    @Column(name = "plaza", nullable = false)
    private String plaza;
    @Column(name = "esta_pag", nullable = false)
    private String estaPag;
    @Column(name = "total_record", nullable = false)
    private Integer totalRecord;
    @Column(name = "send_flag")
    private String sendFlag;
    @Column(name = "mt", nullable = false)
    private String mt;
    @Column(name = "st", nullable = false)
    private String st;

}
