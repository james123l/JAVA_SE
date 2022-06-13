package algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/*
* 贪心算法： 每一步都执行最优解
* */
public class BroadcastCover {
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> broadcasts = getQuestionRequestedHashmap();
        HashSet<String> allUncoveredAreas = getAllAreas();
        //创建链表存放被covered的电台集合
        ArrayList<String> selectedStations = new ArrayList<>();
        //临时集合 存放在遍历过程中电台覆盖的地区和当前还没有被覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();
        //保存一次遍历中，能cover最多地区的电台.  如果maxCoverStation不是null 则会把它cover的地区加入到selectedAreas
        String maxCoverStation = null;
        while(!allUncoveredAreas.isEmpty()){ //如果还有地区没被cover
            maxCoverStation = null;
            //遍历broadcasts
            for (String key: broadcasts.keySet()) {
                tempSet.clear();
                HashSet<String> curStationCoveredArea = broadcasts.get(key);
                tempSet.addAll(curStationCoveredArea);
                //求出遍历中当前的集合和还剩下没被cover的地区的交集
                tempSet.retainAll(allUncoveredAreas);
                //maxCoverStation需要重置的情况：
                // 当前这个station能覆盖的区域还有没被覆盖的 并且 （maxCoverStation是null或者当前station可以cover的地区比上一个最大值能cover的地区还要多）
                if(!tempSet.isEmpty()&& (maxCoverStation==null || tempSet.size()>broadcasts.get(maxCoverStation).size())){
                    maxCoverStation = key;
                }
            }
            if(maxCoverStation!=null){
                selectedStations.add(maxCoverStation);
                //将maxCoverStation cover 的地区从allUncoveredAreas清除
                allUncoveredAreas.removeAll(broadcasts.get(maxCoverStation));
            }
        }
        System.out.println(selectedStations.toString());
    }
    private static HashMap<String, HashSet<String>> getQuestionRequestedHashmap(){
        //初始化题目需求 要求选择stations可以cover所有的地区
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        HashSet<String> set1 = new HashSet<>();
        set1.add("Queens");set1.add("Brooklyn");set1.add("Manhattan");
        HashSet<String> set2 = new HashSet<>();
        set2.add("Bronx");set2.add("Queens");set2.add("Hobboken");
        HashSet<String> set3 = new HashSet<>();
        set3.add("StatenIsland");set3.add("Brooklyn");set3.add("JerseyCity");
        HashSet<String> set4 = new HashSet<>();
        set4.add("Manhattan");set4.add("Brooklyn");
        HashSet<String> set5 = new HashSet<>();
        set5.add("JerseyCity");set5.add("NorthBurgen");
        broadcasts.put("station1",set1);
        broadcasts.put("station2",set2);
        broadcasts.put("station3",set3);
        broadcasts.put("station4",set4);
        broadcasts.put("station5",set5);
        return broadcasts;
    }
    private static HashSet<String> getAllAreas(){
        //声明所有的地区的set
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("Queens");
        allAreas.add("Brooklyn");
        allAreas.add("Manhattan");
        allAreas.add("Hobboken");
        allAreas.add("Bronx");
        allAreas.add("StatenIsland");
        allAreas.add("JerseyCity");
        allAreas.add("NorthBurgen");
        return allAreas;
    }
}
