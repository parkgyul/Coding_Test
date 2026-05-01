import java.util.*;
class Solution {
    static List<Node> nodes;
    static int preIndex;
    static int postIndex;
    static int[][] answer;
    public int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;
        nodes = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            int num = i+1;
        
            nodes.add(new Node(num, x, y));
        }
        
        nodes.sort((a, b) -> {
            if(a.y == b.y) {
                return a.x - b.x;
            }
            
            return b.y - a.y;
        });
        
        Node root = nodes.get(0);
        
        for(int i = 1; i < n; i++){
            makeChild(root, nodes.get(i));
        }
        
        
        postIndex = 0;
        preIndex = 0;
        answer = new int[2][n];
        
        postorder(root);
        preorder(root);
        
        return answer;
    }
    
    private void preorder(Node node){
        if(node == null){
            return;
        }
        
        answer[0][preIndex++] = node.number;
        
        preorder(node.left);
        preorder(node.right);
    }
    
    private void postorder(Node node){
        if(node == null){
            return;
        }
        
        postorder(node.left);
        postorder(node.right);
        
        answer[1][postIndex++] = node.number;
    }
    
    private void makeChild(Node parent, Node child){
        if(parent.x > child.x){
            if(parent.left == null){
                parent.left = child;
            }else{
                makeChild(parent.left, child);
            }
        }else{
            if(parent.right == null){
                parent.right = child;
            }else{
                makeChild(parent.right, child);
            }
        }
    }
    
    static class Node{
        int number, x, y;
        Node left;
        Node right;
        
        Node(int number, int x, int y){
            this.number = number;
            this.x = x;
            this.y = y;
        }
    }
}