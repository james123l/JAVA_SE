package behavioral.template;

public class Burger {
    private Meat meat;
    private Sauce sauce;

    public Meat getMeat() {
        return meat;
    }

    public void setMeat(Meat meat) {
        this.meat = meat;
    }

    public Sauce getSauce() {
        return sauce;
    }

    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    enum Meat{
        lamb("lamb"),
        fish("fish"),
        beef("beef"),
        chicken("chicken");

        private String meat;
        private Meat(String str) {
            this.meat = str;
        }
        public String getMeat(){
            return this.meat;
        }
    }
    enum Sauce{
        bbq("bbq"),
        mayo("mayo"),
        chicken("chicken");

        private String sauce;
        private Sauce(String str) {
            this.sauce = str;
        }
        public String getSauce(){
            return this.sauce;
        }
    }

}
