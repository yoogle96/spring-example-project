import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Test {

    long ans = 0;
    ArrayList<Integer> num = new ArrayList<>();
    ArrayList<Character> op = new ArrayList<>();
    HashMap<Character, Integer> operMap = new HashMap<>();

    public String toPost(String ansExp) {
        Stack<Character> stack = new Stack<>();
        String pre = "";

        for(int i = 0; i < ansExp.length(); i++) {
            char token = ansExp.charAt(i);
            if(isOper(token)) {
                if(stack.isEmpty()) {
                    stack.push(token);
                }else {
                    if(token == ')') {
                        while(stack.peek() != '(') {
                            pre += stack.pop();
                        }
                        stack.pop();
                    }else if(token == '(') {
                        stack.push(token);
                    }else {
                        int tokenPri = operMap.get(token);
                        char top = stack.peek();
                        int topPri = operMap.get(top);
                        if(tokenPri > topPri) {
                            stack.push(token);
                        }else {
                            while(!stack.isEmpty()) {
                                char tmp = stack.peek();
                                if(tmp == '(') break;
                                else stack.pop();
                                pre += tmp;
                            }
                            stack.push(token);
                        }
                    }
                }
            }else {
                pre += token;
            }
        }
        while(!stack.isEmpty()) {
            pre += stack.pop();
        }
        return pre;
    }

    boolean isOper(char c) {
        if(operMap.containsKey(c)) {
            return true;
        }else {
            return false;
        }
    }


    public long cal(String ansExp) {
        boolean open = false;
        String post = toPost(ansExp);
        Stack<Long> stack = new Stack<>();

        for(int i = 0; i < post.length(); i++) {
            char token = post.charAt(i);

            if(token == '+') {
                long a1 = stack.pop();
                long a2 = stack.pop();
                long sum = a2 + a1;
                stack.push(sum);
            }else if(token == '-') {
                long a1 = stack.pop();
                long a2 = stack.pop();
                long sum = a2 - a1;
                stack.push(sum);
            }else if(token == 'x') {
                long a1 = stack.pop();
                long a2 = stack.pop();
                long sum = a2 * a1;
                stack.push(sum);
            }else {
                int n = token - '0';
                stack.push(Long.valueOf(n));
            }
        }

        return stack.pop();
    }

    public long solution(String exp) {

        operMap.put('(', 0);
        operMap.put(')', 0);
        operMap.put('+', 1);
        operMap.put('-', 1);
        operMap.put('x', 2);

        StringBuilder sb = new StringBuilder();
        sb.append(exp);
        for(int i = 0; i < exp.length() - 1; i += 1) {
            sb.insert(i, "(");
            for(int j = i+4; j <= exp.length()+1; j += 2) {
                sb.insert(j, ")");
                System.out.println(new String(sb));
                long res = cal(new String(sb));
                this.ans = Math.max(res, ans);
                sb.delete(j, j+1);
            }
            sb.delete(i, i+1);
        }

        return this.ans;
    }

    public static void main(String[] args) throws ScriptException {
        Test test = new Test();
        test.solution("2-1x5-4x3+2");
    }
}
