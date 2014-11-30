/*
ID: jprakha1
LANG: JAVA
TASK: numtri
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
 public class numtri {

 }

 */

public class numtri
{
	private static PrintWriter printWriter;

	public void solve() throws FileNotFoundException
	{
		InputStream inputStream = getClass().getResourceAsStream("numtri.in");

		InputHelper inputHelper = new InputHelper(inputStream);

		printWriter = new PrintWriter(new File("numtri.out"));

		// actual solution

		int r = inputHelper.readInteger();

		int[][] a = new int[r][r];
		int[][] dp = new int[r][r];

		for (int i = 0; i < r; i++)
		{
			for (int j = 0; j <= i; j++)
			{
				a[i][j] = inputHelper.readInteger();
			}
		}

		dp[0][0] = a[0][0];
		for (int i = 1; i < r; i++)
		{
			for (int j = 0; j <= i; j++)
			{
				dp[i][j] = -1;
				if (j > 0)
				{
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1]);
				}
				if (j < i)
				{
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
				}
				dp[i][j] += a[i][j];
			}
		}

		int ans = -1;

		for (int i = 0; i < r; i++)
		{
			ans = Math.max(ans, dp[r - 1][i]);
		}
		printWriter.println(ans);
		// end here

		printWriter.close();
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		(new numtri()).solve();
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
