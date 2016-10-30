package org.spring.cassandra.example.dto;

import com.datastax.driver.mapping.annotations.*;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by bfitouri on 28/10/16.
 */
@Table(name = "gas_index")
public class Index {

    @PartitionKey
    private String offer_family;

    @PartitionKey(1)
    private String bp;

    @PartitionKey(2)
    private String pce;

    private String pdl;

    @ClusteringColumn
    private DateTime ts;

    @ClusteringColumn(1)
    private String nature;

    @ClusteringColumn(2)
    @Column(name = "generation_ts")
    private Date generation_ts;

    @ClusteringColumn(3)
    @Column(name = "is_before_change")
    private  Boolean is_before_change;



    public String getOffer_family() {
        return offer_family;
    }

    public void setOffer_family(String offer_family) {
        this.offer_family = offer_family;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getPce() {
        return pce;
    }

    public void setPce(String pce) {
        this.pce = pce;
    }

    public String getPdl() {
        return pdl;
    }

    public DateTime getTs() {
        return ts;
    }

    public String getNature() {
        return nature;
    }

    public Date getGeneration_ts() {
        return generation_ts;
    }

    public void setGeneration_ts(Date generation_ts) {
        this.generation_ts = generation_ts;
    }

    public Boolean getIs_before_change() {
        return is_before_change;
    }

    public void setIs_before_change(Boolean is_before_change) {
        this.is_before_change = is_before_change;
    }

    public void setPdl(String pdl) {
        this.pdl = pdl;
    }

    public void setTs(DateTime ts) {
        this.ts = ts;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }
}
