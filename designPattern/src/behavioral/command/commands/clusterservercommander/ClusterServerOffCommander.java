package behavioral.command.commands.clusterservercommander;

import behavioral.command.commands.Command;
import behavioral.command.devices.ClusterServer;

public class ClusterServerOffCommander implements Command {
    ClusterServer server;

    public ClusterServerOffCommander(ClusterServer server) {
        this.server = server;
    }

    @Override
    public void execute() {
        server.off();
    }

    @Override
    public void undo() {
        server.on();
    }
}
