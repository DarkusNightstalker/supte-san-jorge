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

@Data
@Table(name = "gender", catalog = "public")
@XmlRootElement
@NoArgsConstructor
@EqualsAndHashCode(of ={"id"})
public class Gender implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "state", nullable = false)
    private Boolean state;
}
