/*
ID: jprakha1
LANG: JAVA
TASK: crypt1
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
import java.util.StringTokenizer;

/*
 public class crypt1 {

 }

 */

public class crypt1
{
	private static PrintWriter printWriter;

	int ans = 0;

	public void solve() throws FileNotFoundException
	{
		InputStream inputStream = getClass().getResourceAsStream("crypt1.in");

		InputHelper inputHelper = new InputHelper(inputStream);

		printWriter = new PrintWriter(new File("crypt1.out"));

		// actual solution

		int n = inputHelper.readInteger();

		int[] c = new int[n];

		for (int i = 0; i < n; i++)
		{
			c[i] = inputHelper.readInteger();
		}

		int[] cc = new int[5];
		bt(c, n, cc, 0);

		printWriter.println(ans);
		// end here

		printWriter.close();
	}

	private void bt(int[] c, int n, int[] cc, int dp)
	{
		if (dp == 5)
		{
			if (isValid(cc, c))
			{
				ans++;
			}
		}
		else
		{
			for (int i = 0; i < c.length; i++)
			{
				cc[dp] = c[i];

				bt(c, n, cc, dp + 1);

				cc[dp] = -1;
			}
		}
	}

	private boolean isValid(int[] cc, int[] c)
	{
		int abc = 100 * cc[0] + 10 * cc[1] + cc[2];

		int pp1 = abc * cc[4];
		int pp2 = abc * cc[3];

		int fp = pp1 + (pp2 * 10);

		if (ch(pp1, c, true) && ch(pp2, c, true) && ch(fp, c, false))
		{
			return true;
		}
		return false;
	}

	private boolean ch(int pp2, int[] c, boolean isp)
	{
		Map<Integer, Boolean> m = new HashMap<Integer, Boolean>();

		if (isp && pp2 / 1000 > 0)
		{
			return false;
		}
		if (!isp && pp2 / 10000 > 0)
		{
			return false;
		}
		for (int i = 0; i < c.length; i++)
		{
			m.put(c[i], true);
		}

		int r = 1000;

		boolean start = true;
		while (r > 0)
		{
			int q = (pp2 / r) % 10;

			if (start && q == 0)
			{
				r /= 10;
				continue;
			}

			start = false;

			if (!m.containsKey(q))
			{
				return false;
			}

			r /= 10;
		}

		return true;
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		(new crypt1()).solve();
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
