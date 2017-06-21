package junit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class patternAAA {

	public static void main(String[] args) {
		String aa="发${dfs}fdsa${aaaa}fdsafds";
		Pattern pattern = Pattern.compile("\\$\\{[a-z0-9_]+\\}");
		Matcher matcher = pattern.matcher(aa);

		if (matcher.find()){
			System.out.println(matcher.group());
		}
	}

}
