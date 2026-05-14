import java.util.*;
class Solution {
    static List<Node> nodes;
    static int n;
    static int[][] answer;
    static int index;
    public int[][] solution(int[][] nodeinfo) {
        n = nodeinfo.length;
        answer= new int[2][n];
        
        nodes = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
            int num = i+1;
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            
            nodes.add(new Node(num, x, y));
        }
        
        nodes.sort((a, b) -> {
           if(a.y == b.y) return a.x - b.x;
            
            return b.y - a.y;
        });
        
        Node root = nodes.get(0);
        
       for(int i = 1; i < n; i++){
           makeChild(root, nodes.get(i));
       }
        
        index = 0;
        preOrder(root);
        index = 0;
        postOrder(root);
        
        return answer;
    }
    public void preOrder(Node node){
        if(node == null){
            return;
        }
        
        answer[0][index++] = node.num;
        preOrder(node.left);
        preOrder(node.right);
    }
    
    public void postOrder(Node node){
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        answer[1][index++] = node.num;
    }
    
    public void makeChild(Node parent, Node child){
        if(parent.x < child.x){
            if(parent.right == null){
                parent.right = child;
            }else{
                makeChild(parent.right, child);
            }
        }else{
            if(parent.left == null){
                parent.left = child;
            }else{
                makeChild(parent.left, child);
            }
        }
    }
    
    public class Node{ 
        int num, x, y;
        Node left;
        Node right;
        
        public Node(int num, int x, int y){
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }
}