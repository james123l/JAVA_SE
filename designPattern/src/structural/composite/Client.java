package structural.composite;

public class Client {
    public static void main(String[] args) {
        League axis = new League("Axis","Nazism");
        League allies = new League("Allies","AntiNazism");

        Country germany = new Country("Germany","nazi country");
        Country italy = new Country("Italy","poor country");
        Country japan = new Country("Japan","asian island country");
        Country america = new Country("America","rich country");
        Country soviet = new Country("Soviet","communism country");
        Country england = new Country("England","europe island country");

        General rommel = new General("Rommel","clever");
        General batton = new General("Batton","clever");
        General zhukov = new General("Zhukov","brave");
        General montgomary = new General("Montgomary","lucky");
        General nagaokahon = new General("Nagaokahon","negative");
        General kanji = new General("Kanji","predicate");

        germany.add(rommel);
        america.add(batton);
        japan.add(nagaokahon);
        japan.add(kanji);
        soviet.add(zhukov);
        england.add(montgomary);
        allies.add(america);
        allies.add(england);
        allies.add(soviet);
        axis.add(germany);
        axis.add(italy);
        axis.add(japan);

        axis.print();
        germany.print();
        japan.print();
        italy.print();



    }
}
