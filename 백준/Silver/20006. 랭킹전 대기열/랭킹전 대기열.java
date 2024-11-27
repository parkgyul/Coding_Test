import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

class Main{
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Player[] players = new Player[p];

        for(int i = 0; i < p; i++){
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String str = st.nextToken();
            players[i] = new Player(str, level);
        }


        for(int i = 0; i < p; i++){
            ArrayList<Player> room = new ArrayList<Player>();
            if(!players[i].isInRoom){
                room.add(players[i]);
                for(int j = i+1; j < p; j++){

                    if(players[j].isInRoom) continue;
                    int player_level = players[i].level;

                    if(room.size() == m)
                        break;
                    if(players[j].level < player_level - 10 || players[j].level > player_level + 10 )
                        continue;

                    room.add(players[j]);
                    players[j].isInRoom = true;
                }
                room.sort((p1, p2) -> p1.name.compareTo(p2.name));
                if(room.size() == m) sb.append("Started!\n");
                else sb.append("Waiting!\n");

                for(int k = 0; k < room.size(); k++){
                    Player player = room.get(k);
                    sb.append(player.level+ " " + player.name + "\n");
                }
            }

        }
        System.out.println(sb);


    }
    static class Player{
        private String name;
        private int level;

        private boolean isInRoom= false;

        public Player(String name, int level){
            this.name = name;
            this.level = level;
        }
        
    }
}
