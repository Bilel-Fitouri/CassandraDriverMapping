package org.spring.cassandra.example.repo;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import com.datastax.driver.mapping.annotations.QueryParameters;
import org.spring.cassandra.example.dto.Index;
import org.spring.cassandra.example.dto.Site;

import java.util.Date;

/**
 * Created by bfitouri on 28/10/16.
 */
@Accessor
public interface IndexAccessor {

    @Query("SELECT * FROM gas_index")
    Result<Index> getAll();

    @Query("SELECT * FROM gas_index where offer_family = ? and bp = ? and pce = ? and ts >= ? and ts < ?")
    @QueryParameters(consistency = "QUORUM")
    Result<Index> getAllByLimits(String offer, String bp, String pce, Date begin, Date end);
}
