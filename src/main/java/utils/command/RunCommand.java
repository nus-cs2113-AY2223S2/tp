package utils.command;

import java.util.Scanner;
import model.Card;
import model.CardList;
import model.CardUUID;
import model.Deck;
import model.DeckList;
import model.TagList;
import utils.UserInterface;
import utils.exceptions.DeckNotFoundException;
import utils.exceptions.EmptyDeckException;
import utils.exceptions.InkaException;
import utils.storage.IDataStorage;

public class RunCommand extends Command {
    private String deckName;

    public RunCommand(String deckName) {
        this.deckName = deckName;
    }

    @Override
    public void execute(CardList cardList, TagList tagList, DeckList deckList, UserInterface ui, IDataStorage storage)
            throws InkaException {
        Scanner in = new Scanner(System.in);
        Deck deck = deckList.findDeckFromName(deckName);
        if (deck == null) {
            throw new DeckNotFoundException();
        }
        boolean isExit = false;
        while (!isExit) {
            if (deck.getCardsSet().isEmpty()) {
                throw new EmptyDeckException();
            }
            for (CardUUID cardUUID : deck.getCardsSet()) {
                Card card = cardList.findCardFromUUID(cardUUID);
                ui.printQuestion(card.getQuestion());
                String input = in.nextLine();
                if (input.isEmpty()) {
                    ui.printAnswer(card.getAnswer());
                } else if (input.equals("exit")) {
                    ui.printExitingRunMode();
                    break;
                }
            }
            isExit = true;
        }
    }
}
