import java.util.ArrayList;
import java.util.Arrays;

public class Test2 {

    public int solution(int[] s) {
        int ans = 0;
        int len = s.length;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(Integer t : s) {
            arrayList.add(t);
        }

        boolean flag = true;
        for(int i = 0; i < arrayList.size() - 1; i++) {
            if(flag) {
                if(arrayList.get(i) > arrayList.get(i+1)) {
//                    System.out.println(arrayList.get(i));
                    ans++;
                    arrayList.add(i, arrayList.get(i) - 1);
                }
                flag = false;
            }else {

                if(arrayList.get(i) < arrayList.get(i+1)) {
//                    System.out.println(arrayList.get(i));
                    ans++;
                    arrayList.add(i + 1, arrayList.get(i) - 1);
                }
                flag = true;
            }
        }
        if(len % 2 == 0 && flag) {
            ans++;
        }else if(len % 2 != 0 && !flag) {
            ans++;
        }
//
//        for(Integer t : arrayList) {
//            System.out.println(t);
//        }

        return ans;
    }


    public static void main(String[] args) {
        Test2 test2 = new Test2();
        System.out.println(test2.solution(new int[]{3, -1, 0, 4}));
    }
}
