package model.TicketPriceDecorator;

import model.MetroCard;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class TicketPriceFactory {

    public TicketPrice createTicketPrice(boolean is64Plus, boolean isStudent, MetroCard metroCard){
        TicketPrice ticketPrice = new BasicTicketPrice();
        //boolean isChristmas = false;
        List<String> actieveKortingenLijst = new ArrayList<>();

        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("src/bestanden/settings.properties")) {
            prop.load(input);
            String actieveKortingen = prop.getProperty("actievekortingen");
            String[] values = actieveKortingen.split(",");
            actieveKortingenLijst = Arrays.asList(values);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (is64Plus && actieveKortingenLijst.contains("AGE64PLUSDISCOUNT")) {
            ticketPrice = new Age64PlusDiscount(ticketPrice);
        }
        if (isStudent && actieveKortingenLijst.contains("STUDENTDISCOUNT")) {
            ticketPrice = new StudentDiscount(ticketPrice);
        }
        if(metroCard.getRittenVerbruikt() > 50 & actieveKortingenLijst.contains("FREQUENTTRAVELLERDISCOUNT")){
            ticketPrice = new FrequentTravellerDiscount(ticketPrice);
        }
        if (actieveKortingenLijst.contains("CHRISTMASLEAVEDISCOUNT")) {
            ticketPrice = new ChristmasLeaveDiscount(ticketPrice);
        }
        return ticketPrice;
    }
}
