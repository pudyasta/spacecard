package org.space.helper.database.repository;

import org.space.helper.database.ConnectionUtil;
import org.space.helper.database.entity.Cards;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardsRepositoryImpl implements CardsRepository{
    private Connection getConnection() throws SQLException {
        return ConnectionUtil.getDataSource().getConnection();
    }

    @Override
    public Cards getCommentById(Integer id) {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM cards WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractCardFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Cards> findAll() {
        List<Cards> cardsList = new ArrayList<>();

        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM cards";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Cards card = extractCardFromResultSet(resultSet);
                cardsList.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cardsList;
    }

    @Override
    public List<Cards> findByPlanet(String planet) {
        List<Cards> filteredCards = new ArrayList<>();

        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM cards WHERE planet = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, planet);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Cards card = extractCardFromResultSet(resultSet);
                filteredCards.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filteredCards;
    }

    private Cards extractCardFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int attack = resultSet.getInt("attack");
        int defense = resultSet.getInt("defense");
        int health = resultSet.getInt("health");
        String abilities = resultSet.getString("abilities");
        int cost = resultSet.getInt("cost");

        return new Cards(id, name, attack, defense, health, abilities, cost);
    }
}
