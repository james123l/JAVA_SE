package behavioral.command;

import behavioral.command.commands.Controller;
import behavioral.command.commands.ceelphonecommander.CellPhoneCallCammander;
import behavioral.command.commands.ceelphonecommander.CellPhoneMessageCommander;
import behavioral.command.commands.clusterservercommander.ClusterServerOffCommander;
import behavioral.command.commands.clusterservercommander.ClusterServerOnCommander;
import behavioral.command.devices.CellPhone;
import behavioral.command.devices.ClusterServer;

public class Client {
    public static void main(String[] args) {
        Controller controller = new Controller();
        //设立服务器类命令类
        ClusterServer server1 = new ClusterServer();
        ClusterServerOnCommander server1on = new ClusterServerOnCommander(server1);
        ClusterServerOffCommander server1off = new ClusterServerOffCommander(server1);
        ClusterServer server2 = new ClusterServer();
        ClusterServerOnCommander server2on = new ClusterServerOnCommander(server2);
        ClusterServerOffCommander server2off = new ClusterServerOffCommander(server2);
        //设立CellPhone
        CellPhone phone = new CellPhone();
        CellPhoneCallCammander cellPhoneCallCammander = new CellPhoneCallCammander(phone);
        CellPhoneMessageCommander cellPhoneMessageCommander = new CellPhoneMessageCommander(phone);
        controller.setCommander("server1",server1on,server1off);
        controller.setCommander("server2",server2on,server2off);
        controller.setCommander("phonecall",cellPhoneCallCammander,null);
        controller.setCommander("phonesend",cellPhoneMessageCommander,null);

        //遥控器操作
        controller.turnOn("server1");
        controller.turnOff("server1");
        controller.turnOn("server2");
        controller.turnOff("server2");
        controller.turnOn("phonecall");
        controller.turnOn("phonesend");
    }
}
