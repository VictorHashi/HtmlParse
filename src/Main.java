import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String file = "arquivo\\historico.html";

        try{

            Document doc = Jsoup.parse(new File(file), "UTF-8");

            //Teste
            //System.out.println(doc.outerHtml());

            //Bloco de cálculo de média
            double soma = 0;
            int count = 0;
            double media = 0;
            Elements col = doc.select("table tr td:nth-child(4)");
            for (Element dado:col) {
                try{
                    soma += Double.parseDouble(dado.text());
                    count++;
                } catch (NumberFormatException e) {
                    //Não conta caso não possua apenas números
                }
            }
            if (count>0){
                media = soma/count;
                System.out.println("Média das notas: "+media);
            }else {
                System.out.println("Sem notas cadastradas");
            }

            //Bloco de maior nº de faltas
            int faltas = 0;
            int linha = 0;
            col = doc.select("table tr td:nth-child(5)");
            for (Element dado:col) {
                linha++;
                try{
                    if (Integer.parseInt(dado.text())>faltas)
                        faltas = Integer.parseInt(dado.text());
                } catch (NumberFormatException e) {
                    //Não conta caso não possua apenas números inteiros
                }
            }
            if (faltas > 0){
                System.out.println("Disciplina com mais faltas: "+doc.select("table tr:nth-child("+linha+") td:nth-child(2)").text());
            }else{
                System.out.println("Sem faltas cadastradas!");
            }

            //Bloco de Professor com maior quantidade de disciplinas
            List<Tupla> lista = new ArrayList<Tupla>();
            rows:
            for (Element row: doc.select("table tr")) {
                for (Tupla professor: lista) {
                    if (row.select("td:nth-child(3)").text().equals(professor.getProfessor())){
                        professor.setDisciplinas(professor.getDisciplinas()+1);
                        continue rows;
                    }
                }
                lista.add(new Tupla(row.select("td:nth-child(3)").text()));
            }
            int high = 0;
            String professorHigh = "";
            for (Tupla professor: lista) {
                if (professor.getDisciplinas() > high){
                    high = professor.getDisciplinas();
                    professorHigh = professor.getProfessor();
                }
            }
            System.out.println("Professor com mais disciplinas: "+professorHigh);

            //Bloco de reorganização por professor (ordem alfabética) e faltas (crescente)
            List<Disciplina> historico = new ArrayList<Disciplina>();

            for (Element row: doc.select("table tr:has(td)")) {
                Disciplina disciplina = new Disciplina(Integer.parseInt(row.select("td:nth-child(1)").text())
                                            ,row.select("td:nth-child(2)").text()
                                            ,row.select("td:nth-child(3)").text()
                                            ,Double.parseDouble(row.select("td:nth-child(4)").text())
                                            ,Integer.parseInt(row.select("td:nth-child(5)").text()));
                historico.add(disciplina);
            }

            Collections.sort(historico, new Comparator<Disciplina>() {
                @Override
                public int compare(Disciplina d1, Disciplina d2) {
                    int result = d1.getProfessor().compareTo(d2.getProfessor());
                    if (result == 0)
                        return Integer.compare(d1.getFaltas(),(d2.getFaltas()));
                    return result;
                }
            });

            for (Disciplina disciplina: historico) {
                System.out.println(disciplina.toString());
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
