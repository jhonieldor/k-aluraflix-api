package br.com.alura.kaluraflixapi

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.awt.Color
import java.net.URL
import java.util.regex.Matcher
import java.util.regex.Pattern

@SpringBootTest
class KAluraflixApiApplicationTests {

	@Test
	fun contextLoads() {

//		val url = URL("banana")
//
//
//		println(url)

//		val color: Color = Color.getColor("#FFF")
//		print(color)
		val HEX_WEBCOLOR_PATTERN = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$"

		val pattern = Pattern.compile(HEX_WEBCOLOR_PATTERN)
		val colorCode = "#FFF"

		val matcher: Matcher = pattern.matcher(colorCode)
		println(matcher.matches())

		val matcher2: Matcher = pattern.matcher("banana")
		print(matcher2.matches())
	}

}
