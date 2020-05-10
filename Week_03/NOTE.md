递归：
可以使用递归的问题的特点：
    节点的定义，本身就有递归。
    重复性（自相似性）

需要注意的：
    不要进行人肉递归
    找到最近最简单的方法，将其拆解成可重复解决的问题（重复子问题）
    数学归纳法思维
    
泛型递归模板：
    public void recur(int level, int param) {
        //terminator
        if (level >=  MAX_VALUE) {
          //process result 
          return;
        }
        
        //process current logic
        process(level, param);
        
        //drill down 
        recur(level: level + 1, newParam);
        
        //restore current status
    }
    
分治：
    1、分解原问题为若干子问题，这些子问题是原问题的规模较小的实例； 
    2、解决这些子问题，递归地求解各子问题。如果子问题的规模足够小，则直接求解； 
    3、合并这些子问题的解成原问题的解；
    
    代码模板：
    def divide_conquer(problem, param1, param2, ...): 
      # recursion terminator 
      if problem is None: 
        print_result 
        return 
      # prepare data  （process current logic）
      data = prepare_data(problem) 
      subproblems = split_problem(problem, data) 
      # conquer subproblems  (drill down)
      subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
      subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
      subresult3 = self.divide_conquer(subproblems[2], p1, ...) 
      ... 
      # process and generate the final result (merge result)
      result = process_result(subresult1, subresult2, subresult3, …) 
      # revert the current level states

回溯：
    思维特点：
        1、路径：也就是已经做出的选择。
        2、选择列表：也就是你当前可以做的选择。
        3、结束条件：也就是到达决策树底层，无法再做选择的条件。

    模板：
    result = []
    def backtrack(路径, 选择列表):
        if 满足结束条件:
            result.add(路径)
            return
    
        for 选择 in 选择列表:
            做选择
            backtrack(路径, 选择列表)
            撤销选择