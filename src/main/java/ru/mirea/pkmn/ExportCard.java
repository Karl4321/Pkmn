package ru.mirea.pkmn;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ExportCard {

    public void SaveCard(Card card) {
        String fileName = card.getName() + ".crd";
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(card);
            System.out.println("Card successfully saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
