import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> firstDepartment = new HashSet<>();
        List<String> commonEmployees = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            firstDepartment.add(br.readLine());
        }

        for (int i = 0; i < m; i++) {
            String employeeName = br.readLine();
            // Check if the employee is in the first department
            if (firstDepartment.contains(employeeName)) {
                commonEmployees.add(employeeName);
            }
        }

        // Sort the result list
        Collections.sort(commonEmployees);

        // Print the result
        System.out.println(commonEmployees.size());
        for (String name : commonEmployees) {
            System.out.println(name);
        }
    }
}