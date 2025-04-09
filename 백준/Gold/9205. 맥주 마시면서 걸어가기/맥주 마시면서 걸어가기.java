import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {

	private static BufferedReader br;
	private static int[][] points_conv;
	private static int[] point_end;
	private static int[] point_start;
	private static int[][] points;
	private static Point start;
	private static Point end;
	private static boolean[] visited;
	static class QElement{
		Point now;
		Point pre;
		QElement(Point n, Point p){
			now=n;
			pre=p;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			System.out.println((solution())? "happy" : "sad");
		}
		br.close();
	}

	private static boolean solution() throws NumberFormatException, IOException {
		int n_conv = Integer.parseInt(br.readLine().trim());
		List<Point> points = new ArrayList<>();
		for (int i = 0; i < n_conv+2; i++) {
			String[] temp = br.readLine().trim().split(" ");
			points.add(new Point(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
		}
		
		start = points.get(0);
		end = points.get(n_conv+1);
		visited = new boolean[n_conv + 2];
		
		Queue<QElement> q = new ArrayDeque<>();
		q.add(new QElement(points.get(0), null));
		visited[0] = true;
		while(!q.isEmpty()) {
			QElement qe = q.poll();
			if(canGo(qe.now, end)) {
				return true;
			}
			for(int nv = 0; nv < n_conv+1; nv++) {
				if(visited[nv]) {
					continue;
				}
				if(!canGo(qe.now, points.get(nv))){
					continue;
				}
				q.add(new QElement(points.get(nv), qe.now));
				visited[nv] = true;
			}
		}
		return false;
	}

	private static boolean canGo(Point s, Point e) {
		int d = Math.abs(s.x - e.x) + Math.abs(s.y - e.y);
		return d <= 1000;
	}

}