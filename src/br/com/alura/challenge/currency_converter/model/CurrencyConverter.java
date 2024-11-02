package br.com.alura.challenge.currency_converter.model;

import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyConverter {

  private final String BASE_URI;
  private final String API_KEY;
  private String currencyCodeFrom;
  private String currencyCodeTo;

  public CurrencyConverter() {

    // get apikey from .env file
    this.API_KEY = Dotenv.load().get("API_KEY");

    // link base for API request for pair conversion (pre-formatted)
    this.BASE_URI = "https://v6.exchangerate-api.com/v6/%s/pair/%s/%s";
  }

  public CurrencyPairJsonBody getResponseBody() {

    try {
      HttpRequest req = HttpRequest.newBuilder()
          .uri(
              URI.create(
                  String.format(
                    this.BASE_URI,
                    this.API_KEY,
                    this.currencyCodeFrom,
                    this.currencyCodeTo
                  )
              )
          )
          .method("GET", HttpRequest.BodyPublishers.noBody())
          .build();

      String clientResponse = HttpClient.newHttpClient()
          .send(req, HttpResponse.BodyHandlers.ofString())
          .body();

      return new Gson().fromJson(clientResponse, CurrencyPairJsonBody.class);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public boolean isValid(String inputOpc) {
    return inputOpc.matches("[?1-6]");
  }

  public double convertAtCurrentRate(double conversionRate,
                                     double amount) {
    return conversionRate * amount;
  }

  public void setPair(CurrencyCodes currencyFrom, CurrencyCodes currencyTo) {
    this.currencyCodeFrom = currencyFrom.name();
    this.currencyCodeTo = currencyTo.name();
  }

}
