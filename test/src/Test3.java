public class Test3 {

    int len;
    int ans;
    int[] pp;
    boolean ch[];

    public void dfs(int cnt, int cur, int a) {

        if(cnt == len - 1) {
            ans = Math.max(ans, a);
            return;
        }

        for(int i = 0; i < len; i++) {
            if(!ch[i]) {
                ch[i] = true;
                int b = a;
                if(cur < pp[i]) {
                    b = a + 1;
                }
                dfs(cnt + 1, pp[i], b);
                ch[i] = false;
            }
        }

    }

    public int solution(int[] p) {
        this.len = p.length;
        this.pp = p;
        this.ch = new boolean[100001];

        for(int i = 0; i < len; i++) {
            ch[i] = true;
            dfs(0, p[i], 0);
            ch[i] = false;
        }
        return ans;
    }


    public static void main(String[] args) {
        Test3 test3 = new Test3();
        test3.solution(new int[]{103,101,103,103,101,102,100,100,101,104});
    }
}
