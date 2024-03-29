package application;

import entities.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Program {

    /*
        Programa DESAFIO: Análise de vendas 1 desenvolvido por Marcos Ferreira Shirafuchi
        Data: 29/03/2024
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        System.out.print("Entre o caminho do arquivo: ");
        String path = sc.nextLine();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            List<Sale> list = new ArrayList<>();
            String line = br.readLine();
            while (line != null){
                String[] fields = line.split(",");
                list.add(new Sale(Integer.parseInt(fields[0]),Integer.parseInt(fields[1]),fields[2],Integer.parseInt(fields[3]),Double.parseDouble(fields[4])));
                line = br.readLine();
            }
            List<Sale> sales = list.stream()
                    .filter( sale -> sale.getYear() == 2016)
                    .sorted((sale1, sale2 )-> sale2.averagePrice().compareTo(sale1.averagePrice()))
                    .limit(5)
                    .collect(Collectors.toList());
            System.out.println();
            System.out.println("Cinco primeiras vendas de 2016 de maior preço médio");
            for (Sale sale: sales){
                System.out.println(sale);
            }
            System.out.println();
            double totalValue = list.stream()
                    .filter(seller -> seller.getMonth() == 1 || seller.getMonth() == 7)
                    .filter(seller -> seller.getSeller().equals("Logan"))
                    .map(seller -> seller.getTotal())
                    .reduce(0.0,(x,y) ->x + y);

            System.out.printf("Valor total vendido pelo vendedor Logan nos meses 1 e 7 = %.2f\n",totalValue);
        }catch (IOException e){
            System.out.println("Erro: " + e.getMessage());
        }
        sc.close();
    }
}
