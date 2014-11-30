/*
ID: jprakha1
LANG: JAVA
TASK: sprime
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
 public class sprime {

 }

 */

public class sprime
{
	private static PrintWriter printWriter;

	Map<Integer, Short> ps = new HashMap<Integer, Short>();

	public void solve() throws FileNotFoundException
	{
		InputStream inputStream = getClass().getResourceAsStream("sprime.in");

		InputHelper inputHelper = new InputHelper(inputStream);

		printWriter = new PrintWriter(new File("sprime.out"));

		// actual solution

		int n = inputHelper.readInteger();

		dfs(0, 0, n);
		// end here

		printWriter.close();
	}

	private void dfs(int num, int i, int n)
	{
		if (i == n)
		{
			if (isP(num))
			{
				printWriter.println(num);
			}
		}
		else
		{
			if (i == 0)
			{
				num = 2;
				dfs(num, 1, n);
				num = 3;
				dfs(num, 1, n);
				num = 5;
				dfs(num, 1, n);
				num = 7;
				dfs(num, 1, n);
			}
			else
			{
				if (!isP(num))
				{
					return;
				}
				num = num * 10 + 1;
				dfs(num, i + 1, n);
				num /= 10;

				num = num * 10 + 3;
				dfs(num, i + 1, n);
				num /= 10;

				num = num * 10 + 7;
				dfs(num, i + 1, n);
				num /= 10;

				num = num * 10 + 9;
				dfs(num, i + 1, n);
				num /= 10;
			}
		}

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

	public static void main(String[] args) throws FileNotFoundException
	{
		(new sprime()).solve();
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
