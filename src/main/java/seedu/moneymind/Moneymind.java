package seedu.moneymind;

import seedu.moneymind.storage.Storage;

public class Moneymind {
    private Ui ui;
    private Storage storage;

    public Moneymind() {
        this.ui = new Ui();
        // TODO: verify storage is working
        this.storage = new Storage();
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        storage.load();
        while (!isExit) {
            try {
                Command command = ui.processNextCommand();
                if (command.isExit()) {
                    ui.goodbye();
                    isExit = true;
                } else {
                    command.execute(ui); // should also accept storage object as parameter
                }
            } catch (Exception e) {
                ui.error(e);
            }
        }
        storage.save();
    }

    public static void main(String[] args) {
        new Moneymind().run();
    }
}
