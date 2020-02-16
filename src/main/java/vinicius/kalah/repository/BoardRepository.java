package vinicius.kalah.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vinicius.kalah.model.Board;

public interface BoardRepository extends MongoRepository<Board, String> {
}
