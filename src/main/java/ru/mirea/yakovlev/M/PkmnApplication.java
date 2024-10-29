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

        String filename = "D:\\Pkmn-master\\src\\main\\resources\\my_card.txt";
        CardImport cardImport = new CardImport();
        Card cardIm = cardImport.Import(filename);

        ExportCard exportCard = new ExportCard();
        exportCard.SaveCard(cardIm);

        Card cardEx = cardImport.LoadCard("D:\\Pkmn-master\\Phantump.crd");
        System.out.println(cardEx.toString());
    }
}
