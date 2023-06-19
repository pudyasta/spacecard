package org.space.helper.database.repository;

import org.space.helper.database.entity.Cards;

import java.util.List;

public interface CardsRepository {
    Cards getCommentById(Integer id);

    List<Cards> findAll();

    List<Cards> findByPlanet(String planet);
}
