package org.spring.cassandra.example.dto;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;


/**
 * Created by bfitouri on 28/10/16.
 */

@Table(name = "site")
public class Site {

    @PartitionKey
    @Column(name = "site_id")
    private String siteId;

    @Column(name = "offer_family")
    private String offerFamily;

    private String bp;

    private String pce;

    private String pdl;

    private Site(){
    }

    public Site(Builder builder) {
        this.siteId = builder.siteId;
        this.offerFamily = builder.offerFamily;
        this.bp = builder.bp;
        this.pce = builder.pce;
        this.pdl = builder.pdl;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getPce() {
        return pce;
    }

    public String getBp() {
        return bp;
    }

    public String getPdl() {
        return pdl;
    }

    public String getOfferFamily() {
        return offerFamily;
    }

    public static Builder newBuilder(){
        return new Builder();
    }

    public static class Builder {

        private String siteId;

        private String bp;

        private String pce;

        private String pdl;

        private String offerFamily;

        public Builder siteId(String siteId) {
            this.siteId = siteId; return this;
        }

        public Builder bp(String bp) {
            this.bp = bp;
            return this;
        }

        public Builder offerFamily(String offerFamily) {
            this.offerFamily = offerFamily;
            return this;
        }

        public Builder pce(String pce) {
            this.pce = pce;
            return this;
        }

        public Builder pdl(String pdl) {
            this.pdl = pdl;
            return this;
        }

        public Site build(){
            return new Site(this);
        }
    }
}

