/*
ID: jprakha1
LANG: JAVA
TASK: skidesign
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
 public class skidesign {

 }

 */

public class skidesign
{
	private static PrintWriter printWriter;

	public void solve() throws FileNotFoundException
	{
		InputStream inputStream = getClass()
				.getResourceAsStream("skidesign.in");

		InputHelper inputHelper = new InputHelper(inputStream);

		printWriter = new PrintWriter(new File("skidesign.out"));

		// actual solution

		int n = inputHelper.readInteger();

		int[] a = new int[n];

		for (int i = 0; i < n; i++)
		{
			a[i] = inputHelper.readInteger();
		}

		int ans = 10000000;
		for (int i = 0; i <= 100; i++)
		{
			int min = i, max = i + 17;
			int ca = 0;
			for (int j = 0; j < n; j++)
			{
				if (a[j] < min)
				{
					ca += (min - a[j]) * (min - a[j]);
				}
				else if (a[j] > max)
				{
					ca += (a[j] - max) * (a[j] - max);
				}
			}
			if (ans > ca)
				ans = ca;
		}

		printWriter.println(ans);
		// end here

		printWriter.close();
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		(new skidesign()).solve();
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
