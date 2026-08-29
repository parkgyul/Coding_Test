class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        
        // C#, D#, F#, G#, A# 을 처리해야함.
        // X, Y, Z, Q, P
        int maxTime = 0;
        
        m = m.replaceAll("C#", "X");
        m = m.replaceAll("D#", "Y");
        m = m.replaceAll("F#", "Z");
        m = m.replaceAll("G#", "Q");
        m = m.replaceAll("A#", "P");
    
        
        for(int i = 0; i < musicinfos.length; i++){
            String[] info = musicinfos[i].split(",");
            
            String[] st = info[0].split(":");
            int startTime = 60*Integer.parseInt(st[0]) + Integer.parseInt(st[1]);
            
            String[] et = info[1].split(":");
            int endTime = 60*Integer.parseInt(et[0]) + Integer.parseInt(et[1]);
            
            int totalTime = endTime - startTime;
            
            StringBuilder sb = new StringBuilder();
            
            info[3] = info[3].replaceAll("C#", "X");
            info[3] = info[3].replaceAll("D#", "Y");
            info[3] = info[3].replaceAll("F#", "Z");
            info[3] = info[3].replaceAll("G#", "Q");
            info[3] = info[3].replaceAll("A#", "P");
            
            for(int j = 0; j < totalTime / info[3].length(); j++){
                sb.append(info[3]);
            }
            
            sb.append(info[3].substring(0, totalTime % info[3].length()));
            
            if(sb.toString().contains(m)){
                if(maxTime < totalTime){
                    answer = info[2];
                    maxTime = totalTime;
                }
            }
        
        }
        
        return answer;
    }
}