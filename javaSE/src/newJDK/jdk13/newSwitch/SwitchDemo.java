package newJDK.jdk13.newSwitch;

public class SwitchDemo {
    /*
    * newJDK.newSwitch.jdk13 switch 可以不写break 但是需要使用 ->
    *
    * */
    public void noBreak(){
        //没有break不会贯穿，并且可以作为表达式
        char ch = 'A';
        char finalCh = switch(ch){
            //多值匹配
            case 'A','B' -> 'C';
            case 'C','D' -> {
                if (ch == 'C') {
                    yield 'C';  //yield关键字控制返回值
                }else{
                    yield  'D';
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + ch);
        };
    }
}
