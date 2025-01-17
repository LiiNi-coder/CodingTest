import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	static int test_n;
	static int cols = 100;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 1; i <= test_n; i++) {
			int dump = Integer.parseInt(br.readLine());
			//boxes[1]은 맨 아래에서 두번째 row에 존재하는 박스개수
			int[] boxes = new int[cols];
			int high = 99;
			int low = 0;
			for (String token : br.readLine().split(" ")) {
				int col = Integer.parseInt(token);
				for (int j = 0; j < col; j++) {
					boxes[j]++;
				}
			}
			while(boxes[high] == 0)
				high--;
			while(boxes[low] == 100)
				low++;
			
			
			while(dump > 0) {
				dump -= 1;
				boxes[high] -= 1;
				if (boxes[high] == 0) {
					high--;
				}
				boxes[low]+=1;
				if (boxes[low]==cols) {
					low++;
				}
			}
			System.out.printf("#%d %d\n", i, high-low);
		}
		
		
	}
}
