package capgemini.printingQueue.cqrs.demo.query.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends Entity> extends JpaRepository<T, Long> {
	
	@Override
	default void delete(Entity entity) {
		entity.setDeleted(true);
	}

	@Override
	default void deleteAll() {
		findAll().stream().peek(e -> e.setDeleted(true));
	}

	@Override
	default void deleteById(Long id) {
		findById(id).stream().peek(e -> e.setDeleted(true));
	}	
}
