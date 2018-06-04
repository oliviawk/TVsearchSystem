package org.pmsc.entity;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table(name = "channel")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer fatherId;

    private Integer dataTypeId;

    private Integer dataTimeId;

    private Integer c_order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public Integer getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(Integer dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public Integer getDataTimeId() {
        return dataTimeId;
    }

    public void setDataTimeId(Integer dataTimeId) {
        this.dataTimeId = dataTimeId;
    }

    public Integer getC_order() {
        return c_order;
    }

    public void setC_order(Integer c_order) {
        this.c_order = c_order;
    }
}
