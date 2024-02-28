package tt;


public class Theater_seating {
    public static int[][] initialSeating = new int[4][5];

    public static boolean solution(int row, int col, int count){
        int m = initialSeating[0].length;
        boolean valid = false;
        if(initialSeating[row][m-1] == 0) {
            if(initialSeating[row][0] == 0) {
                if (col == 0 && (col + count) <= m) {
                    valid = true;
                }
                if(col >= 0 && (col + count) == m){
                    valid = true;
                }
            }else{
                if(col > 0 && initialSeating[row][col] == 0 && initialSeating[row][col - 1] == 0 && (col + count) <= m){
                    valid = true;
                }
            }
        }else{
            if(col + count < m && col >= 0 && initialSeating[row][col + count - 1] == 0 && initialSeating[row][col + count] == 1){
                valid = true;
            }
        }
        if(valid){
            for(int i = col; i < col + count; i++){
                initialSeating[row][i] = 1;
            }
        }

        return valid;
    }

    public static void main(String[] args){
        int[][] queries = new int[][] {{1,2,3}, {0,2,4},{2,0,2}};
        for(int i = 0; i < queries.length; i++){
            boolean ans = solution(queries[i][0], queries[i][1], queries[i][2]);
            System.out.println( "the ans is: " + ans);
        }
    }
}
