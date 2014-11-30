/*
ID: jprakha1
LANG: JAVA
TASK: namenum
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

public class namenum
{
	private static PrintWriter printWriter;

	public void solve() throws FileNotFoundException
	{
		InputStream inputStream = getClass().getResourceAsStream("namenum.in");

		InputHelper inputHelper = new InputHelper(inputStream);

		InputStream dataInputStream = getClass()
				.getResourceAsStream("dict.txt");

		InputHelper dataInputHelper = new InputHelper(dataInputStream);

		printWriter = new PrintWriter(new File("namenum.out"));

		String brandNo = inputHelper.read();

		Map<Character, Integer> map = new HashMap<Character, Integer>();

		for (int i = 0; i < 25; i++)
		{
			char ch = (char) (i + 'A');

			if (i <= 15)
			{
				int v = (i / 3) + 2;
				map.put(ch, v);
			}
			else if (i >= 17)
			{
				int v = ((i - 1) / 3) + 2;
				map.put(ch, v);
			}
		}

		boolean anyAns = false;
		String dataInput = dataInputHelper.read();
		do
		{
			String outBrand = "";

			// System.out.println(dataInput);
			for (int i = 0; i < dataInput.length(); i++)
			{
				outBrand += map.get(dataInput.charAt(i));
			}

			if (outBrand.equals(brandNo))
			{
				anyAns = true;
				printWriter.println(dataInput);
			}
			dataInput = dataInputHelper.read();
		}
		while (dataInput != null);

		if (!anyAns)
		{
			printWriter.println("NONE");
		}
		printWriter.close();
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		(new namenum()).solve();
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
