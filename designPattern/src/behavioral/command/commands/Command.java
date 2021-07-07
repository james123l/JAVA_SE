package behavioral.command.commands;

/*
* 命令模式通过设置命令类 让客户端不直接操作目标类 然后通过controller控制命令类集合来控制目标类的行为
* 实例：spring JDBCTemplate
* */
public interface Command {
    void execute();
    void undo();
}
