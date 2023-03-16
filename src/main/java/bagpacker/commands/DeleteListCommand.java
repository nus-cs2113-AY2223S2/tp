package bagpacker.commands;

import bagpacker.packingfunc.PackingList;

public class DeleteListCommand extends Command {

    @Override
    public void execute(PackingList packingList) {
        packingList = new PackingList();
    }

}
