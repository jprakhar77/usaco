/*
ID: jprakha1
LANG: JAVA
TASK: dualpal
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
 public class dualpal {

 }

 */

public class dualpal
{
	private static PrintWriter printWriter;

	public void solve() throws FileNotFoundException
	{
		InputStream inputStream = getClass().getResourceAsStream("dualpal.in");

		InputHelper inputHelper = new InputHelper(inputStream);

		printWriter = new PrintWriter(new File("dualpal.out"));

		// actual solution

		int n = inputHelper.readInteger();
		int s = inputHelper.readInteger();

		for (int i = s + 1; n > 0; i++)
		{
			int nop = 0;
			for (int j = 2; j <= 10; j++)
			{
				String conn = convertToBaseB(i, j);
				if (isPalin(conn))
				{
					nop++;
					if (nop == 2)
						break;
				}
			}

			if (nop == 2)
			{
				printWriter.println(i);
				n--;
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

	private String convertToBaseB(int i, int base)
	{
		String out = "";

		int c = i;

		while (c >= base)
		{
			int r = c % base;

			out = String.valueOf(r) + out;

			c /= base;
		}

		if (c > 0)
		{
			out = String.valueOf(c) + out;
		}
		return out;
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		(new dualpal()).solve();
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
