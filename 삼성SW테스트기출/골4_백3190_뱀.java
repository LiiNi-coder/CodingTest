import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

class Main{
    static HashSet<int[]> APPLES = new HashSet<int[]>();
    static int[][] head_ds = {
        {0, 1},
        {1, 0},
        {0, -1},
        {-1, 0}
    };
    static int N;
    static int N_APPLE;
    public static Object[] move(int[] head, int[] tail, int direction, int[][][] maps){
        int next_r = head[0] + head_ds[direction][0];
        int next_c = head[1] + head_ds[direction][1];
        if(!(0<=next_r && next_r<N && 0<=next_c && next_c<N)){
            return new Object[]{true, new int[]{next_r, next_c}, tail};
        }
        if(maps[next_r][next_c][0] == 1){
            return new Object[]{true, new int[]{next_r, next_c}, tail};
        }
        maps[head[0]][head[1]] = new int[]{1, direction};
        int[] new_tail;
        if(!APPLES.contains(new int[]{1, 2})){
            new_tail = new int[]{tail[0] + head_ds[maps[tail[0]][tail[1]][1]][0], tail[1] + head_ds[maps[tail[0]][tail[1]][1]][1]};
            maps[tail[0]][tail[1]] = new int[]{0, -1};
            tail = new_tail;
        }
        else{
            APPLES.remove(new int[]{next_r, next_c});
        }
        return new Object[]{false, new int[]{next_r, next_c}, tail};
    }
    static int get4n(int now, int d){
        int result = now+d;
        if(result >= 4) return 0;
        else if(result < 0) return 3;
        else return result;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        N_APPLE = Integer.parseInt(br.readLine());
        String[] temp = null;
        for (int i = 0; i < N_APPLE; i++) {
            temp = br.readLine().split(" ");
            APPLES.add(new int[]{Integer.parseInt(temp[0]) - 1, Integer.parseInt(temp[1]) - 1});   
        }
        int N_TURN_COMMANDS = Integer.parseInt(br.readLine());
        var TURN_COMMANDS = new ArrayList<Object[]>();
        for (int i = 0; i < N_TURN_COMMANDS; i++) {
            temp = br.readLine().split(" ");
            TURN_COMMANDS.add(new Object[]{Integer.parseInt(temp[0]), temp[1]});
        }
        
        int i_command = 0;
        int[] head = {0, 0};
        int[] tail = {0, 0};
        int direction = 0;
        int[][][] maps = new int[N][N][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maps[i][j][0] = 0;
                maps[i][j][1] = -1;
            }
        }
        maps[0][0][0] = 1; maps[0][0][0] = 0;
        boolean is_colide;
        int t;
        for (t = 1; t < 10001; t++) {
            Object[] temp2 = move(head, tail, direction, maps);
            is_colide = (boolean) temp2[0];
            head = (int[]) temp2[1];
            tail = (int[]) temp2[2];
            if(is_colide) break;
            if(i_command < N_TURN_COMMANDS && (int)TURN_COMMANDS.get(i_command)[0] == t){
                String command = (String)TURN_COMMANDS.get(i_command++)[1];
                if(command.equals("D")){
                    direction = get4n(direction, 1);
                }else{
                    direction = get4n(direction, -1);
                }
            }   
        }
        System.out.println(t);
    }
}