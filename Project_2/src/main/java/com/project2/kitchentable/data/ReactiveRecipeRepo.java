package com.project2.kitchentable.data;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import com.project2.kitchentable.beans.Recipe;

@Repository
public interface ReactiveRecipeRepo extends ReactiveCassandraRepository<Recipe, String>{
}
