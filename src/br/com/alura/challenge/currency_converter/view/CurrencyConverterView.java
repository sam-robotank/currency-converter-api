package br.com.alura.challenge.currency_converter.view;

import java.util.Locale;
import java.util.Scanner;
import static java.lang.System.*;

public class CurrencyConverterView {

  Scanner scanner;
  short lineWidth;

  public CurrencyConverterView(short lineWidth) {
    this.scanner = new Scanner(in);
    this.lineWidth = lineWidth;
  }

  public CurrencyConverterView() {
    this((short) 50); // default lineWidth

    out.println("\nIniciando a view...\n");

    out.println("Seja bem-vindo(a) ao conversor de moedas Fiat. :D");
  }

  public void start() {
    this.br();
    this.header();
    this.br();
  }


  private void header() {
    String headerRequest = """        
        Opções de conversão:
        
        1) Dólar            -> Peso argentino;
        2) Peso argentino   -> Dólar;
        3) Dólar            -> Real brasileiro;
        4) Real brasileiro  -> Dólar;
        5) Dólar            -> Peso colombiano;
        6) Peso Colombiano  -> Dólar;
        0) Sair;
        """;

    out.println(headerRequest);
  }

  private void br() {
    // creates a breakes with 40 characters
    for(int i = 0; i < this.lineWidth; i++) {
      out.print("*");
    }
    out.println();
  }

  public void show(double valueFrom, String requestCodeFrom,
                   double valueTo, String requestCodeTo) {

    String outputUnformatted = "%.2f [%s] corresponde ao valor final " +
        "de" +
        " -> %.2f [%s]";

    out.print(String.format(Locale.forLanguageTag("pt-BR"), outputUnformatted,
        valueFrom, requestCodeFrom, valueTo, requestCodeTo));

    this.await();
  }

  private void await() {
    out.println("\n\nAperte ENTER para continuar...");
    scanner.nextLine();
  }

  public String getInput(String requestMsg) {
    out.println(requestMsg);
    return this.scanner.nextLine();
  }

  public void stop() {
    out.println("Fechando o Scanner...");
    scanner.close();
    out.println("View desligada...");
    out.println("...");
    out.println("Até logo!");
  }

}
