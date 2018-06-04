package org.pmsc.repository;

import org.pmsc.entity.Channel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChannelRepository extends CrudRepository<Channel,Long> {

    @Query("select c from Channel c where c.fatherId=:fatherId order by c.c_order asc")
    List<Channel> findByFatherId(@Param("fatherId")Integer fatherId);
}
