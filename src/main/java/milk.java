/*
ID: jprakha1
LANG: JAVA
TASK: milk
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 public class milk {

 }

 */

class farmer
{
	int p;
	int a;
}

public class milk
{
	private static PrintWriter printWriter;

	public void solve() throws FileNotFoundException
	{
		InputStream inputStream = getClass().getResourceAsStream("milk.in");

		InputHelper inputHelper = new InputHelper(inputStream);

		printWriter = new PrintWriter(new File("milk.out"));

		// actual solution

		int n = inputHelper.readInteger();

		int m = inputHelper.readInteger();

		farmer[] fl = new farmer[m];

		for (int i = 0; i < m; i++)
		{
			farmer farmer = new farmer();

			int p = inputHelper.readInteger();
			int a = inputHelper.readInteger();

			farmer.a = a;
			farmer.p = p;

			fl[i] = farmer;
		}

		Arrays.sort(fl, new Comparator<farmer>()
		{
			public int compare(farmer o1, farmer o2)
			{
				return o1.p - o2.p;
			}
		});

		int cn = n;

		int ans = 0;

		for (int i = 0; i < m; i++)
		{
			if (cn >= fl[i].a)
			{
				ans += (fl[i].a * fl[i].p);
				cn -= fl[i].a;
			}
			else
			{
				ans += (cn * fl[i].p);
				break;
			}
		}

		printWriter.println(ans);
		// end here

		printWriter.close();
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		(new milk()).solve();
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
