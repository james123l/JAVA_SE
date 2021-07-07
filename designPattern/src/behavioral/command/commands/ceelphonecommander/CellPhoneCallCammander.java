package behavioral.command.commands.ceelphonecommander;

import behavioral.command.commands.Command;
import behavioral.command.devices.CellPhone;

public class CellPhoneCallCammander implements Command {
    CellPhone phone;

    public CellPhoneCallCammander(CellPhone phone) {
        this.phone = phone;
    }

    @Override
    public void execute() {
        phone.call();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }
}
