package com.project2.kitchentable.data;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.project2.kitchentable.beans.User;

@Repository
public interface UserDao extends ReactiveCassandraRepository<User, String> {

}
