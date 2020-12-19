import java.util.Scanner;
import java.util.Set;

public class VertexClusterTest
{
    public static void main(String[] args)
    {
        VertexCluster vc = new VertexCluster();

        String[][] data1 = {
                {"11","8","7"},
                {"8","7","9"},
                {"7","10","6"},
                {"8","3","14"},
                {"3","4","5"},
                {"3","1","12"},
                {"7","9","22"},
                {"1","9","7"},
                {"9","13","6"},
                {"3","5","10"},
                {"1","5","8"},
                {"1","6","14"},
                {"9","12","12"},
                {"5","6","7"},
                {"5","2","5"},
                {"6","2","13"},
                {null,"    ","-1"},
                {"2","12","10"}
        };

        for(String[] e : data1)
        {
            vc.addEdge(e[0],e[1],Integer.parseInt(e[2]));
        }
        
        Set<Set<String>> ss1 = vc.clusterVertices(7);
        for (Set<String> set : ss1) 
        { 
        	for(String s : set) 
        	{
        		System.out.print(s + " "); 
        		} 
        	System.out.println();
        	} 
       	   

        System.out.println("add <v1> <v2> <weight> \nclusters <threshold>\nprint\nquit\n\n");

        
        

    }
}
