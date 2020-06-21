#学习总结  
##动态规划
###DP步骤
1. 找最优子结构
2. 定义状态
3. 状态转移方程
###DP模板
```python
function dp():
	dp = [][] #二维情况
	for i = 0..M {
		for j = 0..N {
			dp[i][j] = _Function(dp[i'][j']...) #递推出最新的dp[i][j]的状态,状态转移方程
		}
	}
	
	return dp[M][N]; #最终结果
```
* dp[][]定义:  
把现实的问题定义成一个数组，保存状态。
* dp方程:  
1. 直接递推
2. 累加/累减
3. 最小值
4. 存在一层循环，从之前的K个状态中找出它的最值  
#字符串算法
##字符串
* Java&Python: String --> immutable 增删字母都是新创建一个String
* C++: String --> mutable 
* "==": 比较对象的引用是否相同  
##字符串匹配算法
* 暴力法(brute force) - O(mn)  
```java
public static int forceSearch(String txt, String pat) {
 int M = txt.length();
 int N = pat.length();
 for (int i = 0; i <= M - N; i++) {
   int j;
   for (j = 0; j < N; j++) {
     if (txt.charAt(i + j) != pat.charAt(j))
     break;
   }
 if (j == N) {
  return i;
 }
 // 更加聪明？
 // 1. 预先判断– hash(txt.substring(i, M)) == hash(pat)
 // 2. KMP
 }
 return -1;
}
```
* Rabin-karp算法  
  1. 假设子串的长度为 M (pat)，目标字符串的长度为 N (txt)
  2. 计算子串的 hash 值 hash_pat
  3. 计算目标字符串txt中每个长度为 M 的子串的 hash 值（共需要计算 N-M+1次）
  4. 比较 hash 值：如果 hash 值不同，字符串必然不匹配; 如果 hash 值相同，还需要使用朴素算法再次判断
```java
public final static int D = 256;
public final static int Q = 9997;
static int RabinKarpSerach(String txt, String pat) {
   int M = pat.length();
   int N = txt.length();
   int i, j;
   int patHash = 0, txtHash = 0;
   for (i = 0; i < M; i++) {
     patHash = (D * patHash + pat.charAt(i)) % Q;
     txtHash = (D * txtHash + txt.charAt(i)) % Q;
   }
   int highestPow = 1; // pow(256, M-1)
   for (i = 0; i < M - 1; i++)
      highestPow = (highestPow * D) % Q;
   for (i = 0; i <= N - M; i++) { // 枚举起点
  
     if (patHash == txtHash) {
       for (j = 0; j < M; j++) {
           if (txt.charAt(i + j) != pat.charAt(j))
           break;
       }
       if (j == M)
        return i;
     }
     if (i < N - M) {
       txtHash = (D * (txtHash - txt.charAt(i) * highestPow) + txt.charAt(i + M)) % Q;
       if (txtHash < 0)
       txtHash += Q;
     }
   }
  return -1;
}
```

* KMP算法  
KMP算法（Knuth-Morris-Pratt）的思想就是，当子串与目标字符串不匹配时，
其实你已经知道了前面已经匹配成功那 一部分的字符（包括子串与目标字符
串）。


