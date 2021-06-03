public class FindtheHighestAltitude1732 {
    public int largestAltitude(int[] gain) {
        if(gain.length==1) return Math.max(gain[0],0 );
        int max = gain[0]>0?gain[0]:0;
        /*gain数组中
        gain[0]是第一次到达的高度 = 0+gain[0],所以不需要计算
        gain[1]是第二次到达的高度 = gain[0]+gain[1]
         */
        for(int i = 1;i<gain.length;i++){
            gain[i]= gain[i-1]+gain[i];
            if(max<gain[i]) max = gain[i];
        }
        return max;
    }
}
