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
	private int[] dist;
	private final int INF = 1000000000;
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
		for (int i = 0; i < 10; i++) {
			if (i < V) {
				dijkstra(AdjList, i);
				for (int j = 0; j < V; j++) {
					cache[i][j] = dist[j];
				}
			}
		}
		//------------------------------------------------------------------------- 
	}

	int Query(int s, int t, int k) {
		int ans = -1;

		// You have to report the shortest path from Steven and Grace's current position s
		// to reach their chosen hospital t, output -1 if t is not reachable from s
		// with one catch: this path cannot use more than k vertices
		//
		// PS: this query means different thing for the Subtask D (R-option)

		if (cache[s][t] != INF) {
			ans = cache[s][t];
		}
		
		return ans;
	}

	// You can add extra function if needed
	// --------------------------------------------

    public void initSSSP(int source) {
    	for (int i = 0; i < V; i++) {
    		dist[i] = INF;
    	}
    	dist[source] = 0;
    }
    
    public void dijkstra(Vector<Vector<IntegerPair>> adjList, int source) {
    	
    	// Initialize the PQ used for dijkstra
    	PriorityQueue<IntegerPair> pq = new PriorityQueue<IntegerPair>();
    	
    	initSSSP(source);
    	
    	pq.offer(new IntegerPair(0, source));
    	while (!pq.isEmpty()) {
    		IntegerPair temp = pq.poll();
    		int d = temp.first();
    		int u = temp.second();
    		if (d == dist[u]) {
    			for (int i = 0; i < adjList.get(u).size(); i++) {
					int v = adjList.get(u).get(i).second();
					int weight = adjList.get(u).get(i).first();
    				if (dist[v] > dist[u] + weight) {
    					dist[v] = dist[u] + weight;
    					pq.offer(new IntegerPair(dist[v], v));
    				}
    			}
    		}
    	}
    }
    
    public void display() {
    	for (int i = 0; i < V; i++) {
    		System.out.println("Distance to vertex " + i + ": " + dist[i]);
    	}
    	System.out.println();
    }
    
    public void displayCache() {
    	for (int i = 0; i < 10; i++) {
    		for (int j = 0; j < V; j++) {
        		System.out.println(cache[i][j] + " ");
        	}
    		System.out.println();
    	}
    	System.out.println();
    }
    
	// --------------------------------------------

	void run() throws Exception {
		// you can alter this method if you need to do so
		IntegerScanner sc = new IntegerScanner(System.in);
		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int TC = sc.nextInt(); // there will be several test cases
		while (TC-- > 0) {
			V = sc.nextInt();

			// Initialize arrays
			dist = new int[V];
			cache = new int[10][V];
			
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



class IntegerPair implements Comparable<IntegerPair> {
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