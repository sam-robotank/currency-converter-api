package br.com.alura.challenge.currency_converter.model;

public record CurrencyPairJsonBody(String result,
                                   String base_code,
                                   String target_code,
                                   double conversion_rate)  {
}
