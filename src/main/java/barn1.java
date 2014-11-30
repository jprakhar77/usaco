/*
ID: jprakha1
LANG: JAVA
TASK: barn1
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 public class barn1 {

 }

 */

public class barn1
{
	private static PrintWriter printWriter;

	public void solve() throws FileNotFoundException
	{
		InputStream inputStream = getClass().getResourceAsStream("barn1.in");

		InputHelper inputHelper = new InputHelper(inputStream);

		printWriter = new PrintWriter(new File("barn1.out"));

		// actual solution

		int m = inputHelper.readInteger();

		int s = inputHelper.readInteger();

		int c = inputHelper.readInteger();

		int[] fs = new int[c];

		for (int i = 0; i < c; i++)
		{
			fs[i] = inputHelper.readInteger();
		}

		Arrays.sort(fs);

		List<Integer> b = new ArrayList<Integer>();

		for (int i = 1; i < c; i++)
		{
			if (fs[i] - fs[i - 1] > 1)
			{
				b.add(fs[i] - fs[i - 1] - 1);
			}
		}

		Collections.sort(b);

		int nb = b.size();

		int ans = c;
		if (nb + 1 > m)
		{
			int r = nb + 1 - m;

			for (int i = 0; i < r; i++)
			{
				ans += b.get(i);
			}
		}

		printWriter.println(ans);
		// end here

		printWriter.close();
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		(new barn1()).solve();
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
