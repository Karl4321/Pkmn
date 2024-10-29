package ru.mirea.yakovlev.M.web.jdbc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.mirea.pkmn.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class DatabaseServiceImpl implements DatabaseService {

    private final Connection connection;

    private final Properties databaseProperties;

    public DatabaseServiceImpl() throws SQLException, IOException {

        // Загружаем файл database.properties

        databaseProperties = new Properties();
        databaseProperties.load(new FileInputStream("D:\\Pkmn-master\\src\\main\\resources\\database.properties"));

        // Подключаемся к базе данных

        connection = DriverManager.getConnection(
                databaseProperties.getProperty("database.url"),
                databaseProperties.getProperty("database.user"),
                databaseProperties.getProperty("database.password")
        );
        System.out.println("Connection is "+(connection.isValid(0) ? "up" : "down"));
    }

    @Override
    public Card getCardFromDatabase(String cardID) throws SQLException, JsonProcessingException {
        String query = "SELECT * FROM card WHERE id = ?::uuid";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(UUID.fromString(cardID)));
        ResultSet resultSet = preparedStatement.executeQuery();

        String id = null;
        String name = null;
        int hp = 0;
        Card evolvesFromCard = null;
        String evolvesFrom = null;
        String gameSet = null;
        String pokemonOwner = null;
        String stage = null;
        String retreatCost = null;
        String weaknessType = null;
        String resistanceType = null;
        String attackSkills = null;
        String pokemonType = null;
        char regulationMark = '\0';
        String cardNumber = null;

        List<AttackSkill> attackSkillsList = null;
        EnergyType weaknessTypeEnergy = null;
        EnergyType resistanceTypeEnergy = null;
        EnergyType pokemonTypeEnergy = null;

        resultSet.next();
        id = resultSet.getString("id");
        name = resultSet.getString("name");
        hp = resultSet.getInt("hp");

        evolvesFrom = resultSet.getString("evolves_from");

        gameSet = resultSet.getString("game_set");
        pokemonOwner = resultSet.getString("pokemon_owner");
        stage = resultSet.getString("stage");
        retreatCost = resultSet.getString("retreat_cost");
        weaknessType = resultSet.getString("weakness_type");
        resistanceType = resultSet.getString("resistance_type");
        attackSkills = resultSet.getString("attack_skills");
        pokemonType = resultSet.getString("pokemon_type");
        regulationMark = resultSet.getString("regulation_mark").charAt(0);
        cardNumber = resultSet.getString("card_number");

        if (resultSet.getString("evolves_from") != null) {
            evolvesFromCard = getCardFromDatabase(evolvesFrom);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        attackSkillsList = objectMapper.readValue(attackSkills, new TypeReference<List<AttackSkill>>() {
        });

        if (weaknessType != null & weaknessType!="null") {
            weaknessTypeEnergy = EnergyType.valueOf(weaknessType.toUpperCase());
        }
        if (resistanceType!=null & resistanceType!="null"){
            resistanceTypeEnergy=EnergyType.valueOf(resistanceType.toUpperCase());
        }
        if (pokemonType!=null & pokemonType!="null"){
            pokemonTypeEnergy = EnergyType.valueOf(pokemonType.toUpperCase());
        }

        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("HP: " + hp);
        System.out.println("Evolves From: " + evolvesFrom);
        System.out.println("Game Set: " + gameSet);
        System.out.println("Pokemon Owner: " + pokemonOwner);
        System.out.println("Stage: " + stage);
        System.out.println("Retreat Cost: " + retreatCost);
        System.out.println("Weakness Type: " + weaknessType);
        System.out.println("Resistance Type: " + resistanceType);
        System.out.println("Attack Skills: " + attackSkills);
        System.out.println("Pokemon Type: " + pokemonType);
        System.out.println("Regulation Mark: " + regulationMark);
        System.out.println("Card Number: " + cardNumber);

        resultSet.close();
        return new Card(PokemonStage.valueOf(stage.toUpperCase()), name, regulationMark, hp, pokemonTypeEnergy, evolvesFromCard,
                attackSkillsList, weaknessTypeEnergy, resistanceTypeEnergy, retreatCost, gameSet, getStudentFromDatabase(pokemonOwner), cardNumber);
    }

    @Override
    public Student getStudentFromDatabase(String idStudent) throws SQLException {

        String query = "SELECT * FROM student WHERE id = ?::uuid";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(UUID.fromString(idStudent)));
        ResultSet resultOfQuery = preparedStatement.executeQuery();

        String id = null;
        String firstName = null;
        String familyName = null;
        String patronicName = null;
        String group = null;

        while (resultOfQuery.next()) {
            id = resultOfQuery.getString("id");
            firstName = resultOfQuery.getString("firstName");
            familyName = resultOfQuery.getString("familyName");
            patronicName = resultOfQuery.getString("patronicName");
            group = resultOfQuery.getString("group");
            System.out.println("ID: " + id);
            System.out.println("First Name: " + firstName);
            System.out.println("Family Name: " + familyName);
            System.out.println("Patronic Name: " + patronicName);
            System.out.println("Group: " + group);
        }
        return new Student(firstName,patronicName,familyName,group);
    }


    @Override
    public void saveCardToDatabase(Card card) throws SQLException, JsonProcessingException {
        Statement statement = connection.createStatement();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonSkills = objectMapper.writeValueAsString(card.getSkills());

        ResultSet resultSet = statement.executeQuery("insert into card (id,name, hp, evolves_from, game_set,pokemon_owner, stage," +
                " retreat_cost, weakness_type, resistance_type, attack_skills,pokemon_type, regulation_mark, card_number) values ('34ac3070-5702-409b-ab9a-dcc0354293d5'"+",'"+card.getName()+"', "+
                card.getHp()+", "+card.getEvolvesFrom()+", '"+card.getGameSet()+"','9059f298-4812-4f53-89ff-ffe42e2c1163','"+card.getPokemonStage()+"', "+
                card.getRetreatCost()+", '"+card.getWeaknessType()+"', "+card.getResistanceType()+", '"+jsonSkills+"','"+card.getPokemonType()
                +"', '"+card.getRegulationMark()+"', "+card.getNumber()+");");
    }

    @Override
    public void createPokemonOwner(Student owner) throws SQLException {
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("insert into student (id,  \"familyName\", \"firstName\", \"patronicName\", \"group\") values('9059f298-4812-4f53-89ff-ffe42e2c1163'," +
                "'"+owner.getFamilyMame()+"','"+owner.getFirstName()+"','"+owner.getSurName()+"','"+owner.getGroup()+"')");
        // Реализовать добавление студента - владельца карты в БД
    }
}