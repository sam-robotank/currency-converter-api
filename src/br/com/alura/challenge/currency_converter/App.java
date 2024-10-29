package br.com.alura.challenge.currency_converter;

import br.com.alura.challenge.currency_converter.controller.CurrencyConverterController;
import br.com.alura.challenge.currency_converter.model.CurrencyConverter;
import br.com.alura.challenge.currency_converter.view.CurrencyConverterView;

public class App {

  public static void main(String[] args) {

    CurrencyConverter ccModel = new CurrencyConverter();

    CurrencyConverterView ccView = new CurrencyConverterView();

    CurrencyConverterController ccController =
        new CurrencyConverterController(ccView,
            ccModel);

    ccController.start();
  }
}
