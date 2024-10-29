package ru.mirea.pkmn;

import java.io.Serializable;
import java.util.List;

public class Card implements Serializable {
    private PokemonStage pokemonStage;
    private String name;
    private String number;

    private char regulationMark;
    private int hp;
    public static final long serialVersionUID = 1L;

    private EnergyType pokemonType;
    private Card evolvesFrom;
    private List<AttackSkill> skills;

    private EnergyType weaknessType;
    private EnergyType resistanceType;

    private String retreatCost;
    private String gameSet;

    private Student pokemonOwner;

    public Card(ru.mirea.pkmn.PokemonStage pokemonStage, String name, char regulationMark,
                int hp, EnergyType pokemonType,
                Card evolvesFrom, List<AttackSkill> skills, EnergyType weaknessType, EnergyType resistanceType,
                String retreatCost, String gameSet, Student pokemonOwner,String number) {
        this.pokemonStage = pokemonStage;
        this.name = name;
        this.regulationMark = regulationMark;
        this.hp = hp;
        this.pokemonType = pokemonType;
        this.evolvesFrom = evolvesFrom;
        this.skills = skills;
        this.weaknessType = weaknessType;
        this.resistanceType = resistanceType;
        this.retreatCost = retreatCost;
        this.gameSet = gameSet;
        this.pokemonOwner = pokemonOwner;
        this.number=number;
    }

    public Card() {
    }

    public Card Description(Card card1, List<String> texts){
        List<AttackSkill> skills = card1.getSkills();
        int i=0;
        for (AttackSkill skill : skills){
            skill.setDescription(texts.get(i));
            i+=1;
        }
        return card1;
    }

    @Override
    public String toString() {
        return "Card{" +
                "PokemonStage=" + pokemonStage +
                ", name='" + name + '\'' +
                ", regulationMark=" + regulationMark +
                ", hp=" + hp +
                ", pokemonType=" + pokemonType +
                ", evolvesFrom=" + evolvesFrom +
                ", skills=" + skills +
                ", weaknessType=" + weaknessType +
                ", resistanceType=" + resistanceType +
                ", retreatCost='" + retreatCost + '\'' +
                ", gameSet='" + gameSet + '\'' +
                ", pokemonOwner=" + pokemonOwner +'\'' +
                ", number=" + number+
                '}';
    }

    public ru.mirea.pkmn.PokemonStage getPokemonStage() {
        return this.pokemonStage;
    }

    public String getName() {
        return name;
    }
    public String getNumber() {
        return number;
    }

    public char getRegulationMark() {
        return regulationMark;
    }

    public int getHp() {
        return hp;
    }

    public EnergyType getPokemonType() {
        return pokemonType;
    }

    public Card getEvolvesFrom() {
        return evolvesFrom;
    }

    public List<AttackSkill> getSkills() {
        return skills;
    }

    public EnergyType getWeaknessType() {
        return weaknessType;
    }

    public EnergyType getResistanceType() {
        return resistanceType;
    }

    public String getRetreatCost() {
        return retreatCost;
    }

    public String getGameSet() {
        return gameSet;
    }

    public Student getPokemonOwner() {
        return pokemonOwner;
    }

    public void setPokemonStage(ru.mirea.pkmn.PokemonStage pokemonStage) {
        this.pokemonStage = pokemonStage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegulationMark(char regulationMark) {
        this.regulationMark = regulationMark;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setPokemonType(EnergyType pokemonType) {
        this.pokemonType = pokemonType;
    }

    public void setEvolvesFrom(Card evolvesFrom) {
        this.evolvesFrom = evolvesFrom;
    }

    public void setSkills(List<AttackSkill> skills) {
        this.skills = skills;
    }

    public void setWeaknessType(EnergyType weaknessType) {
        this.weaknessType = weaknessType;
    }

    public void setResistanceType(EnergyType resistanceType) {
        this.resistanceType = resistanceType;
    }

    public void setRetreatCost(String retreatCost) {
        this.retreatCost = retreatCost;
    }

    public void setGameSet(String gameSet) {
        this.gameSet = gameSet;
    }

    public void setPokemonOwner(Student pokemonOwner) {
        this.pokemonOwner = pokemonOwner;
    }
}
