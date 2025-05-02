import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M, D, res;
    static int[][] map, copyMap;
    
    static int[] arch;
    static boolean[] vis;
    static class Point{
        int r,c,d;
        public Point(int r, int c, int d){
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        copyMap = new int[N][M];
        vis = new boolean[M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                copyMap[i][j] = map[i][j];
            }
        }
        res = Integer.MIN_VALUE;
        arch = new int[3];
        dfs(0, 0);
        System.out.println(res);
        
    }
    static void initMap(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j] = copyMap[i][j];
            }
        }
    }
    static void dfs(int cnt, int start){
        if(cnt == 3){
            int count = 0;
            initMap();
            for(int round = 0; round < N; round++){
                boolean[][] killed = new boolean[N][M];
                for(int i = 0; i < 3; i++){
                    int archCol = arch[i];
                    Point t = bfs(N-round, archCol);
                    if(t != null){
                        killed[t.r][t.c] = true;
                    }
                    
                }
                for(int i = 0; i < N; i++){
                    for(int j = 0; j < M; j++){
                        if(killed[i][j]){
                            count++;
                            map[i][j] = 0;
                        }
                    }
                }
                
            }
            res = Math.max(res,count);
            
            return;
        }
        for(int i= start ; i < M ; i++){
            arch[cnt] = i;
            dfs(cnt+1, i+1);
        }
    }
    static Point bfs(int r, int c){
        boolean[][] vis = new boolean[N][M];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r-1, c, 1));
        vis[r-1][c] = true;
        int[] dr = {0,-1,0};
        int[] dc = {-1,0,1};
        while(!q.isEmpty()){
            Point cur = q.poll();
            if(map[cur.r][cur.c] == 1){
                return cur;
            }
            for(int d = 0; d < 3 ; d++){
                int nr = dr[d] + cur.r;
                int nc = dc[d] + cur.c;
                int nd = cur.d + 1;
                if(!check(nr,nc) || vis[nr][nc] || nd > D)continue;
                q.offer(new Point(nr,nc,nd));
                vis[nr][nc] = true;
                
            }
            
        }
        return null;
    }
    static boolean check(int r, int c){
        return r >= 0 && c >=0 && r < N && c <M;
    }
}
