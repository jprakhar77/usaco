/*
ID: jprakha1
LANG: JAVA
TASK: pprime
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 public class pprime {

 }

 */

public class pprime
{
	private static PrintWriter printWriter;
	Map<Integer, Short> ps = new HashMap<Integer, Short>();

	public void solve() throws FileNotFoundException
	{
		InputStream inputStream = getClass().getResourceAsStream("pprime.in");

		InputHelper inputHelper = new InputHelper(inputStream);

		printWriter = new PrintWriter(new File("pprime.out"));

		// actual solution
		int a = inputHelper.readInteger();
		int b = inputHelper.readInteger();

		TreeSet<Integer> pn = new TreeSet<Integer>();
		dfs(0, 4, 1, pn);

		int b1 = pn.floor(b);

		SortedSet<Integer> pns = pn.tailSet(a);
		for (int i : pns)
		{
			if (i > b1)
				break;
			if (isP(i))
			{
				printWriter.println(i);
			}
		}
		// end here

		printWriter.close();
	}

	private boolean isP(int num)
	{
		if (ps.containsKey(num))
		{
			if (ps.get(num) == 1)
			{
				return true;
			}
			else
			{
				return false;
			}
		}

		for (int i = 2; i * i <= num; i++)
		{
			if (num % i == 0)
			{
				ps.put(num, (short) 0);
				return false;
			}
		}

		ps.put(num, (short) 1);

		return true;
	}

	void dfs(int cn, int ms, int cs, TreeSet<Integer> pn)
	{
		if (cs > ms)
		{
			return;
		}
		for (int i = 0; i < 10; i++)
		{
			if (i == 0 && cn == 0)
			{
				continue;
			}
			cn = cn * 10 + i;
			int p1 = generatePalin(cn, cs, true);
			if (p1 > 0)
				pn.add(p1);
			int p2 = generatePalin(cn, cs, false);
			if (p2 > 0)
				pn.add(p2);
			dfs(cn, ms, cs + 1, pn);

			cn /= 10;
		}
	}

	private int generatePalin(int cn, int cs, boolean isOdd)
	{
		int cnc = cn;
		if (isOdd)
		{
			int div = 100;
			int div2 = div / 10;

			for (int i = 0; i < cs - 1; i++)
			{
				int r = cn % div;
				r /= div2;
				cnc = cnc * 10 + r;
				div *= 10;
				div2 *= 10;
			}
		}
		else
		{
			int div = 10;
			int div2 = div / 10;

			for (int i = 0; i < cs; i++)
			{
				int r = cn % div;
				r /= div2;
				cnc = cnc * 10 + r;
				div *= 10;
				div2 *= 10;
			}
		}

		cn = cnc;
		return cn;
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		(new pprime()).solve();
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
