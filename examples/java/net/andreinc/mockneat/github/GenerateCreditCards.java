package net.andreinc.mockneat.github;


import net.andreinc.mockneat.MockNeat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;
import static net.andreinc.mockneat.types.enums.CreditCardType.*;

/**
 * Created by andreinicolinciobanu on 03/03/2017.
 */
public class GenerateCreditCards {

    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();
        final Path path = Paths.get("cc.txt");

        // Write in a file 1000 credit cards AMEX and Mastercard:
        m.creditCards()
                .types(MASTERCARD, AMERICAN_EXPRESS)
                .list(1000)
                .consume(list -> {
                    try { Files.write(path, list, CREATE, WRITE); }
                    catch (IOException e) { e.printStackTrace(); }
                });

        // Generates a single valid credit card number (AMEX)
        String amex = m.creditCards()
                       .type(AMERICAN_EXPRESS)
                       .val();
        System.out.println("AMEX: "  + amex);

        // Generates a single valid credit card number (MASTERCARD)
        String mastercard = m.creditCards()
                             .type(MASTERCARD)
                             .val();
        System.out.println("MASTERCARD: " + mastercard);

        // Generates a single Mastercard or AMEX credit card number
        String amexOrMastercard = m.creditCards()
                                   .types(AMERICAN_EXPRESS, MASTERCARD)
                                   .val();
        System.out.println("AMEX || MASTERCARD: " + amexOrMastercard);

        // Generates a a list of credit card numbers
        List<String> mastercards = m.creditCards()
                                    .type(MASTERCARD)
                                    .list(LinkedList.class, 10)
                                    .val();
        System.out.println("10 MasterCards CARDS: ");
        System.out.println(mastercard);

        // Generates an infinite stream of strings, each String
        // is a valic credit card number
        Stream<String> creditCards = m.creditCards()
                                      .stream()
                                      .val();

        System.out.println(m.creditCards().val());
        System.out.println(m.creditCards().type(VISA_16).val());

        String visaOrMastercard = m.creditCards()
                                   .types(VISA_16, MASTERCARD)
                                   .val();

        String ccName = m.creditCards().names().val();


    }
}
