package functionalities.commands;

import exception.SniffException;
import functionalities.Animal;
import functionalities.Owner;
import functionalities.SniffTasks;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConsultationCommand extends Command {

    private final Animal animal;
    private final Owner owner;
    private final LocalDate date;
    private final LocalTime time;

    public ConsultationCommand(String animalType, String animalName,
                              String ownerName, String contactNumber, LocalDate date,
                              LocalTime time) throws SniffException {
        this.animal = new Animal(animalType, animalName);
        this.owner = new Owner(ownerName, contactNumber);
        this.date = date;
        this.time = time;
    }

    @Override
    public void executeCommand(SniffTasks tasks) throws SniffException {
        tasks.addConsultation(animal, owner, date, time);
    }
}
