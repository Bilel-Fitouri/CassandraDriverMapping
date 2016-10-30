package org.spring.cassandra.example.repo;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import org.spring.cassandra.example.dto.Site;

/**
 * Created by bfitouri on 28/10/16.
 */
@Accessor
public interface SiteAccessor {

    @Query("SELECT * FROM site")
    Result<Site> getAll();
}
