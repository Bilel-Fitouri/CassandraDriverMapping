package org.spring.cassandra.example;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import org.joda.time.DateTime;
import org.spring.cassandra.example.codec.DateTimeCodec;
import org.spring.cassandra.example.dto.Index;
import org.spring.cassandra.example.dto.Site;
import org.spring.cassandra.example.repo.IndexAccessor;
import org.spring.cassandra.example.repo.SiteAccessor;

import java.util.Date;


/**
 * Created by bfitouri on 28/10/16.
 */
public class Application {

    public static void main(String[] args) {

        Cluster cluster = null;
        try {

            cluster = Cluster.builder().addContactPoint("127.0.0.1").build();

            DateTimeCodec dateTimeCodec = new DateTimeCodec();
            CodecRegistry myCodecRegistry = cluster.getConfiguration().getCodecRegistry();
            myCodecRegistry.register(dateTimeCodec);

            Session session = cluster.connect("production_foundation");

            System.out.println("--------------------------------------------");
            ResultSet rs = session.execute("select * from site");
            Row row = rs.one();
            System.out.println(row.getString("site_id"));
            System.out.println("--------------------------------------------");

            MappingManager manager = new MappingManager(session);
            Mapper<Site> mapper = manager.mapper(Site.class);
            Site site = mapper.get("site_demo");
            System.out.println(site.getOfferFamily());
            System.out.println("--------------------------------------------");

            SiteAccessor siteAccessor = manager.createAccessor(SiteAccessor.class);
            Result<Site> allSites = siteAccessor.getAll();
            allSites.all().stream().forEach(site1 -> System.out.println(site1.getPdl()));
            System.out.println("--------------------------------------------");

            Session session2 = cluster.connect("bc_gas");
            MappingManager manager2 = new MappingManager(session2);
            IndexAccessor indexAccessor = manager2.createAccessor(IndexAccessor.class);
            Result<Index> allIndex = indexAccessor.getAll();
            allIndex.all().stream().forEach(index -> System.out.println(index.getTs()));
            System.out.println("--------------------------------------------");

            Date beginDate = DateTime.now().minusMonths(5).toDate();
            Date endDate = DateTime.now().toDate();
            Result<Index> allIndexByLimits = indexAccessor.getAllByLimits("FO008", "BPGAS_GMC01", "PCEGAS_GMC01", beginDate, endDate);
            allIndexByLimits.all().stream().forEach(index -> System.out.println(index.getTs()));
            System.out.println("--------------------------------------------");

        } finally {
            if (cluster != null) cluster.close();
        }

    }
}
