package org.spring.cassandra.example.repo;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import com.datastax.driver.mapping.annotations.QueryParameters;
import org.spring.cassandra.example.dto.Index;
import org.spring.cassandra.example.dto.SensorIndex;

import java.util.Date;
import java.util.Map;

/**
 * Created by bfitouri on 28/10/16.
 */
@Accessor
public interface SensorIndexAccessor {

    @Query("SELECT * FROM sensor_index")
    Result<SensorIndex> getAll();

    @Query("SELECT * FROM sensor_index where offer_family = ? and infos = ?")
    @QueryParameters(consistency = "QUORUM")
    Result<SensorIndex> getSensorIndexesById(String offer, Map<String, String> infos);
}
