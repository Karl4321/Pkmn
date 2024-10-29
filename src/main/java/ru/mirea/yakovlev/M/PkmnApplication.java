package ru.mirea.yakovlev.M;

import com.fasterxml.jackson.databind.JsonNode;
import ru.mirea.pkmn.Card;
import ru.mirea.pkmn.CardImport;
import ru.mirea.pkmn.ExportCard;
import ru.mirea.yakovlev.M.web.http.PkmnHttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PkmnApplication {

    public static void main(String[] args) throws IOException {
//        PkmnHttpClient pkmnHttpClient = new PkmnHttpClient();
//        JsonNode card = pkmnHttpClient.getPokemonCard("Phantump", "16","Sword & Shield",60);
//        System.out.println(card.toPrettyString());
//
//        System.out.println(card.findValues("attacks")
//                .stream()
//                .map(JsonNode::toPrettyString)
//                .collect(Collectors.toSet()));
//
//        List<String> texts = new ArrayList<>();
//        card.findValues("attacks")
//                .forEach(attackNode->attackNode.forEach(attack -> {
//                    String text = attack.get("text").asText();
//                    texts.add(text);
//                }));

        String filename = "D:\\Pkmn-master\\src\\main\\resources\\my_card.txt";
        CardImport cardImport = new CardImport();
        Card cardIm = cardImport.Import(filename);

        ExportCard exportCard = new ExportCard();
        exportCard.SaveCard(cardIm);

        Card cardEx = cardImport.LoadCard("D:\\Pkmn-master\\Phantump.crd");
        System.out.println(cardEx.toString());
    }
}
