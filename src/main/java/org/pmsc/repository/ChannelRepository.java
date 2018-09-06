package org.pmsc.repository;

import org.pmsc.entity.Channel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChannelRepository extends CrudRepository<Channel,Long> {

    @Query("select c from Channel c where c.fatherId=:fatherId order by c.c_order asc")
    List<Channel> findByFatherId(@Param("fatherId")Integer fatherId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="UPDATE channel JOIN (SELECT IFNULL(MAX(c_order)+1,1) AS cnt FROM channel WHERE father_id=?2) t ON id=?1 SET father_id=?2,c_order=t.cnt ",nativeQuery = true)
    int updateFatherId(Integer id,Integer fatherId);

    @Query(value="select IFNULL(max(c.c_order),0) from channel c where c.father_id=?1",nativeQuery = true)
    int findOrderId(Integer fatherId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="UPDATE channel set name=?2 where id=?1",nativeQuery = true)
    int updateName(Integer id,String name);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="delete from channel where id=?1 or father_id=?1",nativeQuery = true)
    int delete(Integer id);
}
