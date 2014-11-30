/*
ID: jprakha1
LANG: JAVA
TASK: palsquare
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
 public class palsquare {

 }

 */

public class palsquare
{
	private static PrintWriter printWriter;

	public void solve() throws FileNotFoundException
	{
		InputStream inputStream = getClass()
				.getResourceAsStream("palsquare.in");

		InputHelper inputHelper = new InputHelper(inputStream);

		printWriter = new PrintWriter(new File("palsquare.out"));

		// actual solution

		int base = inputHelper.readInteger();

		Map<Integer, Character> map = new HashMap<Integer, Character>();

		for (int i = 0; i <= base; i++)
		{
			if (i <= 9)
			{
				map.put(i, (char) (i + '0'));
			}
			else
			{
				map.put(i, (char) ('A' + (i - 10)));
			}
		}

		for (int i = 1; i <= 300; i++)
		{
			String n = convertToBaseB(i, base, map);
			String n2 = convertToBaseB(i * i, base, map);

			if (isPalin(n2))
			{
				printWriter.println(n + " " + n2);
			}
		}
		// end here

		printWriter.close();
	}

	private boolean isPalin(String n2)
	{
		int l = n2.length();

		for (int i = 0, j = l - 1; i < l / 2; i++, j--)
		{
			if (n2.charAt(i) != n2.charAt(j))
				return false;
		}
		return true;
	}

	private String convertToBaseB(int i, int base, Map<Integer, Character> map)
	{
		String out = "";

		int c = i;

		while (c >= base)
		{
			int r = c % base;

			out = map.get(r) + out;

			c /= base;
		}

		if (c > 0)
		{
			out = map.get(c) + out;
		}
		return out;
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		(new palsquare()).solve();
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
