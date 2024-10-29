package ru.mirea.pkmn;
import com.fasterxml.jackson.databind.JsonNode;
import ru.mirea.yakovlev.M.web.http.PkmnHttpClient;

import java.io.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CardImport {
    private PokemonStage pokemonStage;
    private String name;

    private char regulationMark;
    private int hp;

    private EnergyType pokemonType;
    private Card evolvesFrom;
    private List<AttackSkill> skills;

    private EnergyType weaknessType;
    private EnergyType resistanceType;

    private String retreatCost;
    private String gameSet;
    private String number;

    private Student pokemonOwner;
    private String fileName;
    String[] reportData;

    public Card Import(String filename) throws IOException{
        FileInputStream fileInput = new FileInputStream(filename);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInput);
        byte[] data = bufferedInputStream.readAllBytes();
        reportData = new String(data).split("\n");

        if (reportData[6]=="-"){
            reportData[6]=null;
        }
        if (reportData[7]=="-"){
            reportData[7]=null;
        }

        pokemonStage = pokemonStage.valueOf(reportData[0].toUpperCase());
        name = reportData[1];
        regulationMark=reportData[10].charAt(0);
        hp = Integer.parseInt(reportData[2]);
        pokemonType = EnergyType.valueOf(reportData[3].toUpperCase());
        evolvesFrom = ImportEvolvesFrom();
        skills=ImportAttackSkills();
        try {
            weaknessType=EnergyType.valueOf(reportData[6].toUpperCase());
        }
        catch (IllegalArgumentException e) {
            weaknessType=null;
        }
        try {
            resistanceType = EnergyType.valueOf(reportData[7].toUpperCase());
        }
        catch (IllegalArgumentException e) {
            resistanceType=null;
        }
        retreatCost = reportData[8];
        gameSet = reportData[9];
        number=reportData[12];
        pokemonOwner=ImportStudent();

        Card card = new Card(pokemonStage,name,regulationMark,hp,pokemonType,evolvesFrom,skills,weaknessType,resistanceType
                ,retreatCost,gameSet,pokemonOwner,number);

        GetTextsAttacks(card);

        fileInput.close();
        bufferedInputStream.close();
        return card;
    }

    public List<String> GetTextsAttacks(Card card) throws IOException {
        PkmnHttpClient pkmnHttpClient = new PkmnHttpClient();

        JsonNode cardJson = pkmnHttpClient.getPokemonCard(card.getName(),card.getNumber(),card.getGameSet(),card.getHp());
//        System.out.println(cardJson.toPrettyString());
//
//        System.out.println(cardJson.findValues("attacks")
//                .stream()
//                .map(JsonNode::toPrettyString)
//                .collect(Collectors.toSet()));

        List<String> texts = new ArrayList<>();
        cardJson.findValues("attacks")
                .forEach(attackNode->attackNode.forEach(attack -> {
                    String text = attack.get("text").asText();
                    texts.add(text);
                }));
        card.Description(card,texts);
        return texts;
    }

    public Card LoadCard(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Card) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Student ImportStudent(){
        String[] data = reportData[11].split("/");
        return new Student(data[0],data[1],data[2],data[3]);
    }

    private List<AttackSkill> ImportAttackSkills(){
        String fullStr=reportData[5];
        List<AttackSkill> attackSkills = new ArrayList<AttackSkill>();
        String[] abilities = fullStr.split(",");

        for (int i=0;i<abilities.length;i++){
            String[] ability = abilities[i].split("/");
            attackSkills.add(new AttackSkill(ability[1],ability[0],Integer.parseInt(ability[2])));
        }

        return attackSkills;
    }

    private Card ImportEvolvesFrom() throws IOException {
        if (reportData[4].equals("-")){
            return null;
        }
        else{
            CardImport cardImport = new CardImport();
            cardImport.Import(reportData[4]);
        }
        return null;
    }

    private PokemonStage ImportPokemonStage(){
        String symbol = reportData[0];
        if (symbol.equals("BASIC")){
            return PokemonStage.BASIC;
        }
        else if (symbol.equals("STAGE1")) {
            return PokemonStage.STAGE1;
        }
        else if (symbol.equals("STAGE2")) {
            return PokemonStage.STAGE2;
        }
        else if (symbol.equals("VSTAR")) {
            return PokemonStage.VSTAR;
        }
        else if (symbol.equals("VMAX")) {
            return PokemonStage.VMAX;
        }
        return null;
    }
}
