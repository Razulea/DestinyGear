import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Gear> gear = new ArrayList<>();
        gear.add(new Gear(0, new ArrayList<Integer>(Arrays.asList(8,10,14,11,14,6)), false, 0, "7th seraph hood"));
        gear.add(new Gear(0, new ArrayList<Integer>(Arrays.asList(6,9,18,6,7,20)), false, 11, "Wild hunt hood"));
        gear.add(new Gear(0, new ArrayList<Integer>(Arrays.asList(13,16,2,2,16,15)), false, 1, "Insight vikti hood"));
        gear.add(new Gear(1, new ArrayList<Integer>(Arrays.asList(6,6,22,9,12,12)), false, 2, "7th seraph gloves"));
        gear.add(new Gear(1, new ArrayList<Integer>(Arrays.asList(14,7,8,20,10,2)), false, 3, "Legacy's oath gloves"));
        gear.add(new Gear(1, new ArrayList<Integer>(Arrays.asList(19,6,8,7,6,20)), false, 4, "Wild hunt gloves"));
        gear.add(new Gear(2, new ArrayList<Integer>(Arrays.asList(7,7,18,6,19,8)), false, 5, "7th seraph robes"));
        gear.add(new Gear(2, new ArrayList<Integer>(Arrays.asList(10,18,2,12,13,8)), false, 6, "Legacy's oath robes"));
        gear.add(new Gear(2, new ArrayList<Integer>(Arrays.asList(9,6,18,7,6,20)), false, 7, "Wild hunt robes"));
        gear.add(new Gear(3, new ArrayList<Integer>(Arrays.asList(7,6,20,12,9,12)), false, 8, "7th seraph boots"));
        gear.add(new Gear(3, new ArrayList<Integer>(Arrays.asList(8,10,12,6,16,10)), false, 9, "Legacy's oath boots"));
        gear.add(new Gear(3, new ArrayList<Integer>(Arrays.asList(6,9,18,6,10,16)), false, 10, "Wild hunt boots"));

        List<String> prt = new ArrayList<>();
        List<Integer> fin = new ArrayList<>(choose(gear));
        for(Integer integer: fin) {
            prt.add(integer.toString());
        }
        prt.set(6, findGear(gear, fin.get(6)));
        prt.set(7, findGear(gear, fin.get(7)));
        prt.set(8, findGear(gear, fin.get(8)));
        prt.set(9, findGear(gear, fin.get(9)));

        System.out.println(prt);
    }

    public static String findGear(List<Gear> gearList, int id) {
        for(Gear gear: gearList) {
            if(gear.getId() == id)
                return gear.getName();
        }
        return null;
    }

    public static List<Integer> choose(List<Gear> givenGear) {
        List<Gear> listHelm = new ArrayList<>();
        List<Gear> listBracers = new ArrayList<>();
        List<Gear> listChest = new ArrayList<>();
        List<Gear> listBoots = new ArrayList<>();
        for(Gear gear: givenGear) {
            int type = gear.getType();
            if(type == 0)
                listHelm.add(gear);
            else
                if(type == 1)
                    listBracers.add(gear);
                else
                    if(type == 2)
                        listChest.add(gear);
                    else
                        listBoots.add(gear);
        }
        List<List<Integer>> listOfStats = new ArrayList<>();
        for(Gear helm: listHelm)
            for(Gear bracers: listBracers)
                for(Gear chest: listChest)
                    for(Gear boots: listBoots){
                        List<Integer> set = new ArrayList<>();
                        for(int i = 0; i<6; i++) {
                            set.add(0);
                            set.set(i, helm.getStats().get(i) + bracers.getStats().get(i) + chest.getStats().get(i) + boots.getStats().get(i) + 10);
                        }
                        set.add(helm.getId());
                        set.add(bracers.getId());
                        set.add(chest.getId());
                        set.add(boots.getId());
                        listOfStats.add(set);
                    }
        return calculateBest(listOfStats);
    }

    public static List<Integer> calculateBest(List<List<Integer>> setOfStats) {
        for(List<Integer> list: setOfStats) {
            for(int i = 0;i<6;i++){
                if(list.get(i) % 10 != 0)
                    list.set(i, list.get(i) - list.get(i)%10);
            }
        }
        List<Integer> ret = new ArrayList<>();
        int maxTotal = 0;
        int total;
        for(List<Integer> list: setOfStats) {
            total = 0;
            for(int i = 0;i<6;i++) {
                total = list.get(i) + total;
            }
            if(total>maxTotal) {
                maxTotal = total;
                ret.clear();
                ret.addAll(list);
            }
        }
        return ret;
    }

    public static List<Integer> calculateBestSpecific(List<List<Integer>> setOfStats, int stat1, int stat2) {
        for(List<Integer> list: setOfStats) {
            if(list.get(stat1) % 10 != 0)
                list.set(stat1, list.get(stat1) - list.get(stat1)%10);
            if(list.get(stat2) % 10 != 0)
                list.set(stat2, list.get(stat2) - list.get(stat2)%10);
        }
        List<Integer> ret = new ArrayList<>();
        int maxTotal = 0;
        int total;
        for(List<Integer> list: setOfStats) {
            total = list.get(stat1) + list.get(stat2);
            if(total>maxTotal) {
                maxTotal = total;
                ret.clear();
                ret.addAll(list);
            }
        }
        return ret;
    }

}
