import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	static final int[][] drc = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int R, C;
	static class Coord{
		int r;
		int c;
		Coord(int rr, int cc){
			r = rr;
			c = cc;
		}
		Coord go(int rdlu){
			int nr = r + drc[rdlu][0];
			int nc = c + drc[rdlu][1];
			if(nr < 0 || nr>= R || nc < 0 || nc >= C) {
				return null;
			}
			return new Coord(nr, nc);
		}
		boolean isSame(Coord o) {
			return r == o.r && c == o.c;
		}
	}
	static class QElement{
		Coord c;
		int distance;
		QElement(Coord cc, int d){
			c = cc;
			distance = d;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		R = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);
		boolean[][] is_watered_at_map = new boolean[R][C];
		boolean[][] map_wall = new boolean[R][C];
		boolean[][] visited_dochi = new boolean[R][C];
		
		Coord end=null, start=null;
		List<Coord> waters = new LinkedList<>();
		for (int r = 0; r < R; r++) {
			String line = br.readLine();
			for (int c = 0; c < C; c++) {
				switch(line.charAt(c)) {
				case 'X':
					map_wall[r][c] = true;
					break;
				case '*':
					waters.add(new Coord(r, c));
					is_watered_at_map[r][c] = true;
					break;
				case 'D':
					end = new Coord(r, c);
					break;
				case 'S':
					start = new Coord(r, c);
					visited_dochi[r][c] = true;
					break;
				}
			}
		}
		Queue<Coord> q_water = new ArrayDeque<>();
		Queue<Coord> next_q_water = new ArrayDeque<>();
		
		for (Coord w : waters) {
			q_water.add(w);			
		}
		
		int answer = -1;
		
		Queue<QElement> q_dochi = new ArrayDeque<>();
		Queue<QElement> next_q_dochi = new ArrayDeque<>();
		q_dochi.add(new QElement(start, 0));

		do {
			while(!q_water.isEmpty()) {
				Coord water = q_water.poll();
				for (int i = 0; i < 4; i++) {
					Coord next_water = water.go(i);
					if(next_water == null) continue;
					if(map_wall[next_water.r][next_water.c]) continue;
					if(next_water.isSame(end)) continue;
					if(is_watered_at_map[next_water.r][next_water.c]) continue;
					next_q_water.add(next_water);
					is_watered_at_map[next_water.r][next_water.c] = true;
				}
			}
			
			while(!q_dochi.isEmpty()) {
				QElement qe =q_dochi.poll();  
				Coord dochi = qe.c;
				for (int i = 0; i < 4; i++) {
					Coord next_dochi = dochi.go(i);
					if(dochi.isSame(end)) {
						answer = qe.distance;
						q_dochi.clear();
						break;
					}
					if(next_dochi == null) continue;
					if(map_wall[next_dochi.r][next_dochi.c]) continue;
					if(is_watered_at_map[next_dochi.r][next_dochi.c]) continue;
					if(visited_dochi[next_dochi.r][next_dochi.c]) continue;
					next_q_dochi.add(new QElement(next_dochi, qe.distance+1));
					visited_dochi[next_dochi.r][next_dochi.c] = true;
				}
			}
			swapQ(q_water, next_q_water);
			swapQ(q_dochi, next_q_dochi);
		}while(!q_dochi.isEmpty());
		System.out.println((answer == -1)? "KAKTUS" : answer);
		br.close();
	}


	private static <T> void swapQ(Queue<T> q1, Queue<T> q2) {
		Queue<T> tempq = new ArrayDeque<>(q1);
		q1.clear();
		q1.addAll(q2);
		q2.clear();
		q2.addAll(tempq);
	}

}