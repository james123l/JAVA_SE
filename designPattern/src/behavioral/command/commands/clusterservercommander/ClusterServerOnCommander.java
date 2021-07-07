package behavioral.command.commands.clusterservercommander;

import behavioral.command.commands.Command;
import behavioral.command.devices.ClusterServer;

public class ClusterServerOnCommander implements Command {
    ClusterServer server;

    public ClusterServerOnCommander(ClusterServer server) {
        this.server = server;
    }
    @Override
    public void execute() {
        server.on();
    }

    @Override
    public void undo() {
        server.off();
    }
}
