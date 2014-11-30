/*
ID: jprakha1
LANG: JAVA
TASK: combo
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
 public class combo {

 }

 */

public class combo
{
	private static PrintWriter printWriter;

	public void solve() throws FileNotFoundException
	{
		InputStream inputStream = getClass().getResourceAsStream("combo.in");

		InputHelper inputHelper = new InputHelper(inputStream);

		printWriter = new PrintWriter(new File("combo.out"));

		// actual solution

		int n = inputHelper.readInteger();

		int[] fc = new int[3];
		int[] mc = new int[3];

		for (int i = 0; i < 3; i++)
		{
			fc[i] = inputHelper.readInteger();
		}

		for (int i = 0; i < 3; i++)
		{
			mc[i] = inputHelper.readInteger();
		}

		int ans = 0;
		for (int i = 1; i <= n; i++)
		{
			for (int j = 1; j <= n; j++)
			{
				for (int k = 1; k <= n; k++)
				{
					if ((Math.min(Math.abs(fc[0] - i), n - Math.abs(i - fc[0])) <= 2
							&& Math.min(Math.abs(fc[1] - j),
									n - Math.abs(j - fc[1])) <= 2 && Math.min(
							Math.abs(fc[2] - k), n - Math.abs(k - fc[2])) <= 2)
							|| (Math.min(Math.abs(mc[0] - i),
									n - Math.abs(i - mc[0])) <= 2
									&& Math.min(Math.abs(mc[1] - j),
											n - Math.abs(j - mc[1])) <= 2 && Math
									.min(Math.abs(mc[2] - k),
											n - Math.abs(k - mc[2])) <= 2))
					{
						ans++;
					}
				}
			}
		}

		printWriter.println(ans);
		// end here

		printWriter.close();
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		(new combo()).solve();
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
