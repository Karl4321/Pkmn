package ru.mirea.yakovlev.M;

import com.fasterxml.jackson.databind.JsonNode;
import ru.mirea.pkmn.Card;
import ru.mirea.pkmn.CardImport;
import ru.mirea.pkmn.ExportCard;
import ru.mirea.yakovlev.M.web.http.PkmnHttpClient;
import ru.mirea.yakovlev.M.web.jdbc.DatabaseServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PkmnApplication {

    public static void main(String[] args) throws IOException, SQLException {

        String filename = "D:\\Pkmn-master\\src\\main\\resources\\my_card.txt";
        CardImport cardImport = new CardImport();
        Card cardIm = cardImport.Import(filename);
//
//        ExportCard exportCard = new ExportCard();
//        exportCard.SaveCard(cardIm);
//
//        Card cardEx = cardImport.LoadCard("D:\\Pkmn-master\\Phantump.crd");
//        System.out.println(cardEx.toString());
        DatabaseServiceImpl databaseService = new DatabaseServiceImpl();
        databaseService.getCardFromDatabase("34ac3070-5702-409b-ab9a-dcc0354293d5");
    }
}
