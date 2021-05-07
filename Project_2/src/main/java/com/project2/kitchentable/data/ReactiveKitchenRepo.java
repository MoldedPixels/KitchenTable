package com.project2.kitchentable.data;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import com.project2.kitchentable.beans.Kitchen;


import reactor.core.publisher.Flux;

@Repository
public interface ReactiveKitchenRepo extends ReactiveCassandraRepository<Kitchen, String>{
	
	Flux<Kitchen> findAllById(Integer id);
}
