/*
ID: jprakha1
LANG: JAVA
TASK: wormhole
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 public class wormhole {

 }

 */

class point
{
	public point(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
		// this.code = 1000000000L * y + x;
	}

	public int x;
	public int y;
	// public long code;
}

public class wormhole
{
	private static PrintWriter printWriter;

	int ans = 0;

	public void solve() throws FileNotFoundException
	{
		InputStream inputStream = getClass().getResourceAsStream("wormhole.in");

		InputHelper inputHelper = new InputHelper(inputStream);

		printWriter = new PrintWriter(new File("wormhole.out"));

		// actual solution
		int n = inputHelper.readInteger();

		TreeSet<point> pl = new TreeSet<point>(new Comparator<point>()
		{
			public int compare(point o1, point o2)
			{
				if (o1.y == o2.y)
				{
					return o1.x - o2.x;
				}
				else
				{
					return o1.y - o2.y;
				}
			}
		});

		TreeSet<point> pln = new TreeSet<point>(new Comparator<point>()
		{
			public int compare(point o1, point o2)
			{
				if (o1.y == o2.y)
				{
					return o1.x - o2.x;
				}
				else
				{
					return o1.y - o2.y;
				}
			}
		});
		for (int i = 0; i < n; i++)
		{
			int x = inputHelper.readInteger();
			int y = inputHelper.readInteger();

			point point = new point(x, y);

			pl.add(point);
		}

		Map<point, point> map = new HashMap<point, point>();
		backtrack(pl, pl, map, n);
		// end here

		printWriter.println(ans);
		printWriter.close();
	}

	private void backtrack(TreeSet<point> pl, TreeSet<point> pln,
			Map<point, point> map, int n)
	{
		if (pl.size() == 0)
		{
			Map<Long, Boolean> vis = new HashMap<Long, Boolean>();

			Iterator<point> it = pln.iterator();

			while (it.hasNext())
			{
				point point = it.next();

				// if (!vis.containsKey(point.code))
				// {
				if (isCycle(point, pln, map, vis, n))
				{
					ans++;
					// printWriter.println(map);
					break;
				}
				// }
			}
		}
		else
		{
			point point1 = pl.first();

			TreeSet<point> plc = new TreeSet<point>(pl);
			Iterator<point> it = pl.iterator();
			it.next();
			while (it.hasNext())
			{
				point point2 = it.next();

				plc.remove(point1);
				plc.remove(point2);

				map.put(point1, point2);
				map.put(point2, point1);

				backtrack(plc, pln, map, n);

				plc.add(point1);
				plc.add(point2);

				map.remove(point1);
				map.remove(point2);

			}
		}
	}

	private boolean isCycle(point first, TreeSet<point> pln,
			Map<point, point> map, Map<Long, Boolean> vis, int n)
	{
		// Map<Long, Boolean> lvis = new HashMap<Long, Boolean>();
		point cp = first;
		while (true)
		{
			// lvis.put(cp.code, true);
			point tpoint = map.get(cp);

			point nextpoint = pln.higher(tpoint);

			if (nextpoint != null && tpoint.y == nextpoint.y)
			{
				if (nextpoint == first)
					return true;
				cp = nextpoint;
			}
			else
			{
				// vis.putAll(lvis);
				return false;
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		(new wormhole()).solve();
	}

	class InputHelper
	{
		StringTokenizer tokenizer = null;
		private BufferedReader bufferedReader;

		public InputHelper(InputStream inputStream)
		{
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream);
			bufferedReader = new BufferedReader(inputStreamReader, 16384);
		}

		public String read()
		{
			while (tokenizer == null || !tokenizer.hasMoreTokens())
			{
				try
				{
					String line = bufferedReader.readLine();
					if (line == null)
					{
						return null;
					}
					tokenizer = new StringTokenizer(line);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}

			return tokenizer.nextToken();
		}

		public Integer readInteger()
		{
			return Integer.parseInt(read());
		}

		public Long readLong()
		{
			return Long.parseLong(read());
		}
	}
}
