/*
ID: jprakha1
LANG: JAVA
TASK: ariprog
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 public class ariprog {

 }

 */

class S
{
	public int a;
	public int d;

	public S(int a, int d)
	{
		super();
		this.a = a;
		this.d = d;
	}
}

public class ariprog
{
	private static PrintWriter printWriter;

	public void solve() throws FileNotFoundException
	{
		InputStream inputStream = getClass().getResourceAsStream("ariprog.in");

		InputHelper inputHelper = new InputHelper(inputStream);

		printWriter = new PrintWriter(new File("ariprog.out"));

		// actual solution

		int n = inputHelper.readInteger();
		int m = inputHelper.readInteger();

		TreeSet<Integer> bss = new TreeSet<Integer>();
		boolean[] bssb = new boolean[125001];
		for (int p = 0; p <= m; p++)
		{
			for (int q = 0; q <= m; q++)
			{
				int bs = p * p + q * q;
				bss.add(bs);
				bssb[bs] = true;
			}
		}

		TreeSet<S> ss = new TreeSet<S>(new Comparator<S>()
		{
			public int compare(S o1, S o2)
			{
				if (o1.d == o2.d)
				{
					return o1.a - o2.a;
				}
				else
				{
					return o1.d - o2.d;
				}
			}
		});

		int f = bss.first();
		int l = bss.last();
		for (int d = 1;; d++)
		{
			// SortedSet<Integer> sss = bss.tailSet(a);
			// for (Integer b : sss)
			// {
			// if (b <= a)
			// continue;
			// int d = b - a;
			// if (isValid(a, d, bss, n))
			// {
			// // printWriter.println(a + " " + d);
			// S s = new S(a, d);
			// ss.add(s);
			// }
			// }

			if (f + (n - 1) * d > l)
				break;

			for (int cni = f; cni < d; cni++)
			{
				int cn = cni;
				int c = 0;

				if (cn + (n - 1) * d > l)
					break;
				while (cn <= l)
				{
					if (bssb[cn])
					{
						c++;
						if (c == n)
						{
							// printWriter.println(cn - (n - 1) * d + " " + d);
							S s = new S(cn - (n - 1) * d, d);
							ss.add(s);
							c--;
						}
					}
					else
					{
						c = 0;
					}
					cn += d;
				}
			}
		}

		if (ss.size() == 0)
		{
			printWriter.println("NONE");
		}
		else
		{
			for (S s : ss)
			{
				printWriter.println(s.a + " " + s.d);
			}
		}
		// end here

		printWriter.close();
	}

	private boolean isValid(Integer a, Integer d, Set<Integer> bss, int n)
	{
		for (int i = 0; i < n; i++)
		{
			if (!bss.contains(a + i * d))
				return false;
		}
		return true;
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		long st = System.currentTimeMillis();
		(new ariprog()).solve();
		long et = System.currentTimeMillis();
		System.out.println((et - st) / 1000.0);
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
