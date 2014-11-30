/*
ID: jprakha1
LANG: JAVA
TASK: transform
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
 public class transform {

 }

 */

public class transform
{
	private static PrintWriter printWriter;

	public void solve() throws FileNotFoundException
	{
		InputStream inputStream = getClass()
				.getResourceAsStream("transform.in");

		InputHelper inputHelper = new InputHelper(inputStream);

		printWriter = new PrintWriter(new File("transform.out"));

		// actual solution

		int n = inputHelper.readInteger();

		char[][] fm = new char[n][n];
		for (int i = 0; i < n; i++)
		{
			fm[i] = inputHelper.readCharArray();
		}

		char[][] tm = new char[n][n];
		for (int i = 0; i < n; i++)
		{
			tm[i] = inputHelper.readCharArray();
		}

		if (isUnchanged(tm, rotateBy90(fm)))
		{
			printWriter.println("1");
		}

		else if (isUnchanged(tm, rotateBy90(rotateBy90(fm))))
		{
			printWriter.println("2");
		}

		else if (isUnchanged(tm, rotateBy90(rotateBy90(rotateBy90(fm)))))
		{
			printWriter.println("3");
		}

		else if (isUnchanged(tm, rotateHorizontally(fm)))
		{
			printWriter.println("4");
		}

		else if (isUnchanged(tm, rotateHorizontally(rotateBy90(fm))))
		{
			printWriter.println("5");
		}

		else if (isUnchanged(tm, rotateHorizontally(rotateBy90(rotateBy90(fm)))))
		{
			printWriter.println("5");
		}

		else if (isUnchanged(tm,
				rotateHorizontally(rotateBy90(rotateBy90(rotateBy90(fm))))))
		{
			printWriter.println("5");
		}

		else if (isUnchanged(fm, tm))
		{
			printWriter.println("6");
		}

		else
		{
			printWriter.println("7");
		}
		// end here

		printWriter.close();
	}

	char[][] rotateBy90(char[][] a)
	{
		int n = a.length;
		int m = a[0].length;

		char[][] b = new char[m][n];

		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				b[j][n - i - 1] = a[i][j];
			}
		}

		return b;
	}

	char[][] rotateHorizontally(char[][] a)
	{
		int n = a.length;
		int m = a[0].length;

		char[][] b = new char[n][m];

		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				b[i][n - j - 1] = a[i][j];
			}
		}

		return b;
	}

	boolean isUnchanged(char[][] a, char[][] b)
	{
		int n = a.length;
		int m = a[0].length;

		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				if (a[i][j] != b[i][j])
					return false;
			}
		}

		return true;
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		(new transform()).solve();
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

		public char[] readCharArray()
		{
			String token = read();

			if (token == null)
				return null;

			return token.toCharArray();
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
