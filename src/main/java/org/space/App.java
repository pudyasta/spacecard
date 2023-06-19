package org.space;

import org.space.helper.database.entity.Cards;
import org.space.helper.database.repository.CardsRepository;
import org.space.helper.database.repository.CardsRepositoryImpl;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        CardsRepository cardsRepository = new CardsRepositoryImpl();

        // Example usage of repository methods
        Cards card = cardsRepository.getCommentById(1);
        if (card != null) {
            System.out.println("Card Found: " + card.getName());
        } else {
            System.out.println("Card Not Found");
        }

        List<Cards> allCards = cardsRepository.findAll();
        System.out.println("All Cards:");
        for (Cards c : allCards) {
            System.out.println(c.getName());
        }

        List<Cards> venusCards = cardsRepository.findByPlanet("venus");
        System.out.println("Venus Cards:");
        for (Cards c : venusCards) {
            System.out.println(c.getName());
        }
    }
}
