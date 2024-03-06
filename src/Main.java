import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        String file = "arquivo\\historico.html";

        try{

            Document doc = Jsoup.parse(new File(file), "UTF-8");

            //Teste
            //System.out.println(doc.outerHtml());

            Elements col = doc.select("table tr td:nth-child(2)");

            for (Element dado:col) {
                System.out.println(dado.text());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
