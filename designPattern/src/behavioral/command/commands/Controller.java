package behavioral.command.commands;

import java.util.HashMap;
import java.util.LinkedList;

public class Controller {
    // 储存名称和下标索引
    int count = 0;
    HashMap<String,Integer> nameIndex = new HashMap<>();
    //空索引位置
    LinkedList<Integer> nullIndex = new LinkedList<>();
    // 开关按钮的命令链表
    LinkedList<Command> onCommands = new LinkedList<>();
    LinkedList<Command> offCommands = new LinkedList<>();

    // 执行撤销的命令
    Command undoCommand;


    // 设置命令
    public void setCommander(String name, Command onCommand, Command offCommand) {
        onCommands.add(onCommand);
        offCommands.add(offCommand);
        if(!nullIndex.isEmpty()){
            nameIndex.put(name,nullIndex.get(0));
        }else{
            nameIndex.put(name,count);
            count++;
        }
    }
    public void deleteCommander(String str){
        if(!nameIndex.containsKey(str)) throw new RuntimeException("no such device");
        int index = nameIndex.get(str);
        nameIndex.remove(str);
        nullIndex.add(index);
    }

    // 开
    public void turnOn(String str) {
        if(!nameIndex.containsKey(str)) throw new RuntimeException("no such device");
        int index = nameIndex.get(str);
        // 找到你按下的开的按钮， 并调用对应方法
        onCommands.get(index).execute();
        // 记录这次的操作，用于撤销
        undoCommand = onCommands.get(index);

    }

    // 关
    public void turnOff(String str) {
        if(!nameIndex.containsKey(str)) throw new RuntimeException("no such device");
        int index = nameIndex.get(str);
        // 找到你按下的开的按钮， 并调用对应方法
        offCommands.get(index).execute();
        // 记录这次的操作，用于撤销
        undoCommand = offCommands.get(index);

    }

    // 撤销
    public void undo() {
        undoCommand.undo();
    }
}
