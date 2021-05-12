package com.project2.kitchentable.data;

import java.util.UUID;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import com.project2.kitchentable.beans.Kitchen;

@Repository
public interface ReactiveKitchenRepo extends ReactiveCassandraRepository<Kitchen, String>{

}