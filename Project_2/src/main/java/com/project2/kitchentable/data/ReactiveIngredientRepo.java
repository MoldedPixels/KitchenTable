package com.project2.kitchentable.data;

<<<<<<< HEAD
import java.util.UUID;

=======
>>>>>>> 18d5fe1bda32e734144ccf32dea8db27a0f196b6
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.project2.kitchentable.beans.Ingredient;

<<<<<<< HEAD
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveIngredientRepo extends ReactiveCassandraRepository<Ingredient, String>{

}
=======
@Repository
public interface ReactiveIngredientRepo extends ReactiveCassandraRepository<Ingredient, String>{
	
}
>>>>>>> 18d5fe1bda32e734144ccf32dea8db27a0f196b6
