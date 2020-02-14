package vinicius.kalah.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vinicius.kalah.model.Game;

public interface GameRepository extends MongoRepository<Game, String> {
}
