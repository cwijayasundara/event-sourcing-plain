package com.cham.auditconsumer.repository;

import com.cham.eventsourcecomponent.pojo.AuditObj;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AuditCassandraRepository  extends ReactiveCassandraRepository<AuditObj, String> {

    @Override
    <S extends AuditObj> Flux<S> insert(Iterable<S> iterable);

}
