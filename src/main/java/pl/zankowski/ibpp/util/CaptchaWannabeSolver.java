package pl.zankowski.ibpp.util;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Wojciech Zankowski
 */
public class CaptchaWannabeSolver {

    public boolean isCaptchaWannabe(Document document) {
        Element form = document.select("form").first();
        if (form == null) {
            return false;
        }
        if (form.attr("type").equals("post")) {
            return true;
        }
        return false;
    }

    public String solveCaptcha(Document document) {
        Element img = document.select("img").first();
        if (img == null) {
            return "";
        }

        System.out.println("CAPTCHA:" + img.attr("src"));

        Matcher matcher = Pattern.compile("str=.+").matcher(img.attr("src"));
        if (matcher.find()) {
            System.out.println("SOLUTION: " + matcher.group().substring(4));
            return matcher.group().substring(4);
        }

        return "";
    }

}
