package com.example.cye.util;

public class FomattingString {

	public static String format(String value) {

		String[] arrValue = value.split("#");
		StringBuilder builder = new StringBuilder();

		builder.append("<style>"
		        + "div.vidu {"
		        + "text-indent:23px;background-size:20px 20px;"
		        + "color:#008000;font-weight:bold;"
		        + "background:URL(file:///android_asset/vidu_tieude.png);"
		        + "background-repeat:no-repeat;}"
		        + "div.equal{background:URL(file:///android_asset/vidu.png); background-repeat:no-repeat ; text-indent:36px;" +
		        "background-position:22px 4px;}"
		        + "div.plus{color:#126ACB; background:URL(file:///android_asset/nghiavidu.png); background-repeat:no-repeat ;"
		        + "text-indent:24px; margin-left:22px;background-position:15px 0px;}"
		        + "div.dollar {"
		        + "text-indent:23px;background-size:20px 20px;"
		        + "color:#008000;font-weight:bold;"
		        + "background:URL(file:///android_asset/nhanxet_tieude.png);"
		        + "background-repeat:no-repeat;}"
		        + "div.minus{color:#282828;background:URL(file:///android_asset/nhanxet.png);text-indent:40px;"
		        + "background-repeat:no-repeat;background-size:18px 18px; background-position:22px 0px;}" 
		        + "div.exclamation {"
		        + "text-indent:23px;background-size:20px 20px;"
		        + "color:#F32F2F;font-weight:bold;"
		        + "background:URL(file:///android_asset/dungviet.png);"
		        + "background-repeat:no-repeat;}" 
		        + "div.asterisk {"
		        + "text-indent:23px;background-size:20px 20px;"
		        + "color:#008000;font-weight:bold;"
		        + "background:URL(file:///android_asset/phaiviet.png);"
		        + "background-repeat:no-repeat;}"
		        + "div.question {"
		        + "text-indent:25px;background-size:20px 20px;"
		        + "color:#008000;font-weight:bold;"
		        + "background:URL(file:///android_asset/note.png);"
		        + "background-repeat:no-repeat;}"
		        
		        + "div.phuchu {"
		        + "text-indent:25px;background-size:20px 20px;"
		        + "color:#008000;font-weight:bold;"
		        + "background:URL(file:///android_asset/phuchu.png);"
		        + "background-repeat:no-repeat;}"

		        + "body{font-size:18px;font-family:sans-serif;}</style>");

		builder.append("<div class=\"vidu\">");
		builder.append("VÍ DỤ");
		builder.append("</div>");
		for (String string : arrValue) {
			if (string.startsWith("=")) {
				builder.append("<div class=\"equal\">");
				builder.append(string.substring(1));
				builder.append("</div>");

			} else if (string.startsWith("+")) {
				builder.append("<div class=\"plus\">");
				builder.append(string.substring(1));
				builder.append("</div>");
			} else if (string.startsWith("$")) {
				builder.append("<br>");
				builder.append("<div class=\"dollar\">");
				builder.append(string.substring(1));
				builder.append("</div>");

			} else if (string.startsWith("-")) {
				builder.append("<div class=\"minus\">");
				builder.append(string.substring(1));
				
				builder.append("</div>");
			} else if (string.startsWith("!")) {
				builder.append("<br>");
				builder.append("<div class=\"exclamation\">");
				builder.append(string.substring(1));
				builder.append("</div>");
			} else if (string.startsWith("*")) {
				builder.append("<br>");
				builder.append("<div class=\"asterisk\">");
				builder.append(string.substring(1));
				builder.append("</div>");
			} else if (string.startsWith("?")) {
				builder.append("<br>");
				builder.append("<div class=\"question\">");
				builder.append(string.substring(1));
				builder.append("</div>");
			} else if(string.startsWith(":")) {
				builder.append("<br>");
				builder.append("<div class=\"appendix\">");
				builder.append(string.substring(1));
				builder.append("</div>");
				
			}
			else {
				builder.append(string);
			}
		}

		return builder.toString();

	}
}
