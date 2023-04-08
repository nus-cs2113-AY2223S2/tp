package functionalities.commands;

import exception.SniffException;
import functionalities.Animal;
import functionalities.Owner;
import functionalities.SniffTasks;

import java.time.LocalDate;
import java.time.LocalTime;

public class EditConsultationCommand extends Command {
       public String uid;

        private final Animal animal;
        private final Owner owner;
        private final LocalDate date;
        private final LocalTime time;


        public EditConsultationCommand(String uid,String animalType, String animalName,
                                       String ownerName, String contactNumber, LocalDate date,
                                       LocalTime time) throws SniffException {
            this.uid = uid;
            this.animal = new Animal(animalType, animalName);
            this.owner = new Owner(ownerName, contactNumber);
            this.date = date;
            this.time = time;
        }

        @Override
        public void executeCommand(SniffTasks tasks) throws SniffException {
            //tasks.removeAppointment(uid);
            tasks.editConsultation(uid,animal, owner, date, time);
        }
    }
