package org.spring.cassandra.example;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.CodecRegistry;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.google.common.collect.Maps;
import org.joda.time.DateTime;
import org.spring.cassandra.example.codec.DateTimeCodec;
import org.spring.cassandra.example.dto.Index;
import org.spring.cassandra.example.dto.SensorIndex;
import org.spring.cassandra.example.dto.Site;
import org.spring.cassandra.example.repo.IndexAccessor;
import org.spring.cassandra.example.repo.SensorIndexAccessor;
import org.spring.cassandra.example.repo.SiteAccessor;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.datastax.driver.mapping.Mapper.Option.consistencyLevel;

/**
 * Created by bfitouri on 30/10/16.
 */
public class Application {

    static Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
    Session session = cluster.connect("test");
    MappingManager manager = new MappingManager(session);

    public static void main(String[] args) {

        Application app2 = new Application();

        DateTimeCodec dateTimeCodec = new DateTimeCodec();
        CodecRegistry myCodecRegistry = cluster.getConfiguration().getCodecRegistry();
        myCodecRegistry.register(dateTimeCodec);

        System.out.println("-----------Query 1-------------");
        Site site1 = app2.getSiteById("site1");
        System.out.println(site1.getOfferFamily());

        System.out.println("-----------Query 2-------------");
        Site site2 = Site.newBuilder().siteId("site3").bp("bp3").offerFamily("offer3").pdl("pdl4").build();
        app2.saveSite(site2, ConsistencyLevel.QUORUM);

        System.out.println("-----------Query 3-------------");
        List<Site> allSites = app2.getAllSites();
        allSites.stream().forEach(site -> System.out.println(site.getPdl()));

        System.out.println("-----------Query 4-------------");
        List<Index> indexes = app2.getIndexesBetweenTwoDates();
        indexes.stream().forEach(index -> System.out.println(index.getTs()));

        System.out.println("-----------Query 5-------------");
        List<SensorIndex> sensorIndexesById = app2.getSensorIndexesById();
        sensorIndexesById.stream().forEach(index -> System.out.println(index.getInfos()));

        System.out.println("-----------Query 6-------------");
        Map<String, String> infos = Maps.newHashMap();
        infos.put("name", "legrand");
        infos.put("prenom", "fabien");
        SensorIndex sensorIndex = SensorIndex.newBuilder().before_change(true).generation_ts(new Date()).infos(infos).nature("nature").offerFamily("offer4").ts(DateTime.now()).build();
        app2.saveSensorIndex(sensorIndex, ConsistencyLevel.QUORUM);

        System.out.println("-----------Query 7-------------");
        List<Index> allIndexes = app2.getAllIndexes();
        allIndexes.stream().forEach(index -> System.out.println(index.getPdl()));

    }

    private void saveSite(Site site, ConsistencyLevel consistencyLevel){
        Mapper<Site> mapper = manager.mapper(Site.class);
        mapper.save(site, consistencyLevel(consistencyLevel));
    }

    private void saveSensorIndex(SensorIndex index, ConsistencyLevel consistencyLevel){
        Mapper<SensorIndex> mapper = manager.mapper(SensorIndex.class);
        mapper.save(index, consistencyLevel(consistencyLevel));
    }

    private Site getSiteById(String siteId){
        Mapper<Site> mapper = manager.mapper(Site.class);
        return mapper.get(siteId);
    }

    private List<Site> getAllSites(){
        SiteAccessor siteAccessor = manager.createAccessor(SiteAccessor.class);
        Result<Site> allSites = siteAccessor.getAll();
        return allSites.all();
    }


    private List<Index> getAllIndexes(){
        IndexAccessor indexAccessor = manager.createAccessor(IndexAccessor.class);
        Result<Index> allIndexes = indexAccessor.getAll();
        return allIndexes.all();
    }
    private List<Index> getIndexesBetweenTwoDates(){
        IndexAccessor indexAccessor = manager.createAccessor(IndexAccessor.class);
        Date beginDate = DateTime.now().minusYears(7).toDate();
        Date endDate = DateTime.now().toDate();
        Result<Index> allIndexByLimits = indexAccessor.getAllByLimits("offer1", "bp1", "pce1", beginDate, endDate);
        return allIndexByLimits.all();
    }

    private List<SensorIndex> getSensorIndexesById(){
        SensorIndexAccessor sensorAccessor = manager.createAccessor(SensorIndexAccessor.class);
        Map<String, String> infos = Maps.newHashMap();
        infos.put("name", "fitouri");
        infos.put("prenom", "bilel");
        Result<SensorIndex> sensorIndexesById = sensorAccessor.getSensorIndexesById("offer1", infos);
        return sensorIndexesById.all();
    }
}
