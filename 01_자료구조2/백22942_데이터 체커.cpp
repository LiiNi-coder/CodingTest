#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
typedef struct _circle_stack_item{
	int x; // 맨 왼쪽 혹은 오른쪽 좌표
	int centor; // 중심좌표
	bool is_left; // 왼쪽좌표인지
}csi;
bool compare(const csi& a, const csi& b) {
	return (a.x < b.x);
}

int main(void) {
	cin.tie(NULL);
	cin.sync_with_stdio(false);
	int N;
	cin >> N;
	vector<csi> x_rs{};
	for (int i = 0; i < N; i++)
	{
		int x, r;
		cin >> x >> r;
		x_rs.push_back({ x - r, x, true });
		x_rs.push_back({ x + r, x, false });
	}
	sort(x_rs.begin(), x_rs.end(),
		[](const csi& a, const csi& b) {
			return a.x < b.x;
		});
	vector<int> stack{};
	
	bool is_ok = true;
	for (auto& i : x_rs)
	{
		if (i.is_left)
		{
			stack.push_back(i.centor);
			continue;
		}
		if (stack.back() != i.centor)
		{
			is_ok = false;
			break;
		}
		else
		{
			stack.pop_back();
		}
	}
	if (!is_ok || stack.size() != 0)
	{
		cout << "NO";
	}
	else
	{
		cout <<"YES";
	}
}