/*
ID: jprakha1
LANG: JAVA
TASK: milk3
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 public class milk3 {

 }

 */

class state
{
	int[] c;

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(c);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		state other = (state) obj;
		if (!Arrays.equals(c, other.c))
			return false;
		return true;
	}

	public state(int[] c)
	{
		super();
		this.c = new int[3];
		this.c[0] = c[0];
		this.c[1] = c[1];
		this.c[2] = c[2];
	}
}

public class milk3
{
	private static PrintWriter printWriter;
	Set<Integer> ans = new TreeSet<Integer>();

	public void solve() throws FileNotFoundException
	{
		InputStream inputStream = getClass().getResourceAsStream("milk3.in");

		InputHelper inputHelper = new InputHelper(inputStream);

		printWriter = new PrintWriter(new File("milk3.out"));

		// actual solution

		int[] c = new int[3];

		c[0] = inputHelper.readInteger();
		c[1] = inputHelper.readInteger();
		c[2] = inputHelper.readInteger();

		Set<state> vis = new HashSet<state>();

		state startState = new state(new int[]
		{ 0, 0, c[2] });

		dfs(startState, vis, c);

		boolean first = true;
		for (Integer ap : ans)
		{
			if (first)
			{
				printWriter.print(ap);
				first = false;
			}
			else
			{
				printWriter.print(" " + ap);
			}
		}

		printWriter.println();
		// end here

		printWriter.close();
	}

	private void dfs(state state, Set<state> vis, int[] c)
	{
		// System.out.println(state.c[0] + " " + state.c[1] + " " + state.c[2]);
		if (state.c[0] == 0)
		{
			ans.add(state.c[2]);
		}

		vis.add(state);

		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (i == j)
					continue;

				if (state.c[i] > 0)
				{
					int c1 = state.c[i];
					int c2 = c[j] - state.c[j];

					int tbp = Math.min(c1, c2);

					int[] sc = state.c.clone();

					sc[i] -= tbp;
					sc[j] += tbp;

					state ns = new state(sc);

					if (!vis.contains(ns))
					{
						dfs(ns, vis, c);
					}
				}
			}
		}

	}

	public static void main(String[] args) throws FileNotFoundException
	{
		(new milk3()).solve();
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
