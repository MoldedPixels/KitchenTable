package com.project2.kitchentable.data;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import com.project2.kitchentable.beans.User;

import reactor.core.publisher.Flux;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveUserRepo extends ReactiveCassandraRepository<User, String> {
	
	Flux<User> findAllById(Integer id);
}
