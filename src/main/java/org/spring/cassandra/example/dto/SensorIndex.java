package org.spring.cassandra.example.dto;

import com.datastax.driver.mapping.annotations.*;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.Map;

/**
 * Created by bfitouri on 28/10/16.
 */
@Table(name = "sensor_index")
public class SensorIndex {

    @PartitionKey
    @Column(name = "offer_family")
    private String offerFamily;

    @Frozen
    @PartitionKey(1)
    private Map<String, String> infos;

    @ClusteringColumn
    private DateTime ts;

    private String nature;

    @Column(name = "generation_ts")
    private Date generation_ts;

    @Column(name = "is_before_change")
    private  Boolean is_before_change;

    private SensorIndex(){
    }

    public SensorIndex(Builder builder) {
        this.offerFamily = builder.offerFamily;
        this.infos = builder.infos;
        this.ts = builder.ts;
        this.nature = builder.nature;
        this.generation_ts = builder.generation_ts;
        this.is_before_change = builder.is_before_change;
    }

    public String getOfferFamily() {
        return offerFamily;
    }

    public Map<String, String> getInfos() {
        return infos;
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

    public Boolean getIs_before_change() {
        return is_before_change;
    }

    public static Builder newBuilder(){
        return new Builder();
    }


    public static class Builder {

        private String offerFamily;
        private Map<String, String> infos;
        private DateTime ts;
        private String nature;
        private Date generation_ts;
        private Boolean is_before_change;


        public Builder offerFamily(String offerFamily) {
            this.offerFamily = offerFamily;
            return this;
        }

        public Builder infos(Map<String, String> infos) {
            this.infos = infos;
            return this;
        }

        public Builder ts(DateTime ts) {
            this.ts = ts;
            return this;
        }

        public Builder nature(String nature) {
            this.nature = nature;
            return this;
        }

        public Builder generation_ts(Date generation_ts) {
            this.generation_ts = generation_ts;
            return this;
        }

        public Builder before_change(Boolean is_before_change) {
            this.is_before_change = is_before_change;
            return this;
        }

        public SensorIndex build(){
            return new SensorIndex(this);
        }
    }
}
