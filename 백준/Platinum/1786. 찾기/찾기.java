import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] text = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();
		int n_pattern = pattern.length;
		int n_text = text.length;
		int[] pi = new int[n_pattern];
		
		//실패함수 만들기
		for(int i = 1, j=0; i<n_pattern; i++) {
			if(pattern[j] == pattern[i]) {
				j++;
				pi[i] = j;  
				continue;
			}
			while(j>0 && pattern[j] != pattern[i]) {
				j = pi[j-1];
			}
			if(pattern[j] == pattern[i]) {
				pi[i] = ++j;
			}else {
				pi[i] = 0;
			}
		}
		
		int cnt = 0;
		List<Integer> list = new ArrayList<>();
		
		for(int i = 0, j=0; i<n_text; i++) {
			if(text[i] == pattern[j]) {
				if(j == n_pattern - 1) {
					cnt++;
					list.add(i - j);
					j = pi[j];
				}else {
					j++;					
				}
			}else {
				while(j>0 && text[i] != pattern[j])
					j = pi[j-1];
				if(text[i] == pattern[j])
					j++;
			}
		}
		
		System.out.println(cnt);
		StringBuilder sb = new StringBuilder();
		if(cnt>0) {
			for (int s : list) {
				sb.append(s+1).append(" ");				
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

}