// Copy paste this Java Template and save it as "Labor.java"
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here: Adrian Pheh
// write list of collaborators here: A0124123Y
// year 2015 hash code: JESg5svjYpIsmHmIjabX (do NOT delete this line)

class Labor {
	private int V; // number of vertices in the graph (number of junctions in Singapore map)
	private int Q; // number of queries
	private int sumOfWeights;
	private int[] parent, visited;
	private int[][] cache;
	private Vector<Vector<IntegerPair>> AdjList; // the weighted graph (the Singapore map), the length of each edge (road) is stored here too, as the weight of edge

	// if needed, declare a private data structure here that
	// is accessible to all methods in this class
	// --------------------------------------------



	// --------------------------------------------

	public Labor() {
		// Write necessary code during construction
		//
		// write your answer here
		
	}

	void PreProcess() {
		// Write necessary code to preprocess the graph, if needed
		//
		// write your answer here
		//------------------------------------------------------------------------- 
		// Cache the results beforehand
		cache = new int[V][V];
		
		for (int i = 0; i < V; i++) {
			initArrays();
			DFS(AdjList, i, i, 0);
			//System.out.println();
			//displayParent();
		}
		
		//System.out.println();
		//displayCache();

		//------------------------------------------------------------------------- 
	}

	int Query(int s, int t, int k) {
		int ans = -1;

		// You have to report the shortest path from Steven and Grace's current position s
		// to reach their chosen hospital t, output -1 if t is not reachable from s
		// with one catch: this path cannot use more than k vertices
		//
		// PS: this query means different thing for the Subtask D (R-option)
		//
		// write your answer here
		ans = cache[s][t];

		//------------------------------------------------------------------------- 

		return ans;
	}

	// You can add extra function if needed
	// --------------------------------------------

    // Reinitialize the visited and parent arrays after every run of DFS
    public void initArrays() {
    	
    	visited = new int[V];
    	parent = new int[V];
    	
        for (int i = 0; i < V; i++) {
            visited[i] = 0;
            parent[i] = -1;
        }
        sumOfWeights = 0;
    }

    public void DFS(Vector<Vector<IntegerPair>> adjList, int source, int vertex, int sum) {
    	visited[vertex] = 1;
    	//System.out.println("At vertex " + vertex);
    	for (int i = 0; i < adjList.get(vertex).size(); i++) {
    		
    		int nextVertex = adjList.get(vertex).get(i).second();
    		//System.out.println("To vertex " + nextVertex);
    		
    		if (visited[nextVertex] == 0) {
    			// Use sum as total weight up to vertex
    			sumOfWeights = sum + adjList.get(vertex).get(i).first();
    			//System.out.println("Sum of weights: " + sumOfWeights);
    			
    			cache[source][nextVertex] = sumOfWeights; 
    			parent[nextVertex] = vertex;
    			DFS(adjList, source, nextVertex, sumOfWeights);
    			
    			//System.out.println("Returning to branch " + vertex);
    			// After returning from a depth traversal, restore sumOfWeights back to the
    			// original weight before entering the depth traversal (i.e sum of the top most
    			// element of the traversal stack)
    			sumOfWeights = sum;
    		}
    	}
    }
    
    public void displayCache() {
    	System.out.println();
    	for (int i = 0; i < V; i++) {
    		for (int j = 0; j < V; j++) {
    			System.out.print(cache[i][j] + " ");
    		}
    		System.out.println();
    	}
    }

    public void displayParent() {
    	System.out.println();
    	for (int i = 0; i < parent.length; i++) {
    		System.out.print(parent[i] + " ");
    	}
    }
	// --------------------------------------------

	void run() throws Exception {
		// you can alter this method if you need to do so
		IntegerScanner sc = new IntegerScanner(System.in);
		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int TC = sc.nextInt(); // there will be several test cases
		while (TC-- > 0) {
			V = sc.nextInt();

			// clear the graph and read in a new graph as Adjacency List
			AdjList = new Vector<Vector<IntegerPair>>();
			for (int i = 0; i < V; i++) {
				AdjList.add(new Vector<IntegerPair>());

				int k = sc.nextInt();
				while (k-- > 0) {
					int j = sc.nextInt(), w = sc.nextInt();
					AdjList.get(i).add(new IntegerPair(w, j)); // edge (road) weight (in minutes) is stored here
				}
			}

			PreProcess(); // optional

			Q = sc.nextInt();
			while (Q-- > 0)
				pr.println(Query(sc.nextInt(), sc.nextInt(), sc.nextInt()));

			if (TC > 0)
				pr.println();
		}

		pr.close();
	}

	public static void main(String[] args) throws Exception {
		// do not alter this method
		Labor ps5 = new Labor();
		ps5.run();
	}
}



class IntegerScanner { // coded by Ian Leow, using any other I/O method is not recommended
	BufferedInputStream bis;
	IntegerScanner(InputStream is) {
		bis = new BufferedInputStream(is, 1000000);
	}

	public int nextInt() {
		int result = 0;
		try {
			int cur = bis.read();
			if (cur == -1)
				return -1;

			while ((cur < 48 || cur > 57) && cur != 45) {
				cur = bis.read();
			}

			boolean negate = false;
			if (cur == 45) {
				negate = true;
				cur = bis.read();
			}

			while (cur >= 48 && cur <= 57) {
				result = result * 10 + (cur - 48);
				cur = bis.read();
			}

			if (negate) {
				return -result;
			}
			return result;
		}
		catch (IOException ioe) {
			return -1;
		}
	}
}



class IntegerPair implements Comparable < IntegerPair > {
	Integer _first, _second;

	public IntegerPair(Integer f, Integer s) {
		_first = f;
		_second = s;
	}

	public int compareTo(IntegerPair o) {
		if (!this.first().equals(o.first()))
			return this.first() - o.first();
		else
			return this.second() - o.second();
	}

	Integer first() { return _first; }
	Integer second() { return _second; }
}