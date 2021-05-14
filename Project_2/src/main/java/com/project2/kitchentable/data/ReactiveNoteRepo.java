package com.project2.kitchentable.data;

import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import com.project2.kitchentable.beans.Notes;
import reactor.core.publisher.Flux;

@Repository
public interface ReactiveNoteRepo extends ReactiveCassandraRepository<Notes, String>{

	@AllowFiltering
	Flux<Notes> findByRecipeId(UUID userID);
}
