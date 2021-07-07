package behavioral.command.commands.ceelphonecommander;

import behavioral.command.commands.Command;
import behavioral.command.devices.CellPhone;

public class CellPhoneMessageCommander implements Command {
    CellPhone phone;

    public CellPhoneMessageCommander(CellPhone phone) {
        this.phone = phone;
    }

    @Override
    public void execute() {
        phone.sendMessage();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }
}
