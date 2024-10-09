package tn.esprit.pidev.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.pidev.entities.Quiz;

import java.util.List;

public interface QuizDao extends MongoRepository<Quiz, String> {
    List<Quiz> findByCategory(String category);

}
