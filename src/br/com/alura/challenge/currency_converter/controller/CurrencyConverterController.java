package br.com.alura.challenge.currency_converter.controller;

import br.com.alura.challenge.currency_converter.model.CurrencyCodes;
import br.com.alura.challenge.currency_converter.model.CurrencyConverter;
import br.com.alura.challenge.currency_converter.model.CurrencyPairJsonBody;
import br.com.alura.challenge.currency_converter.view.CurrencyConverterView;

import static java.lang.System.out;

public class CurrencyConverterController {

  private final CurrencyConverterView VIEW;
  private final CurrencyConverter MODEL;

  public CurrencyConverterController(CurrencyConverterView appView,
                                     CurrencyConverter appModel) {
    this.VIEW = appView;
    this.MODEL = appModel;
  }

  // processes the entire processing flow
  public void start() {
    boolean running = true;

    while (running) {
      this.VIEW.start();

      // gets the option number
      String option;
      boolean isValid = false;

      // checks if the input value is between 0-6 and if it appears only once
      do {
        option = this.VIEW.getInput("Digite a opção desejada: ");
        if (this.MODEL.isValid(option)) {
          isValid = true;
        } else {
          out.println("Opção inválida. Digite uma das opção disponíveis.");
        }
      } while (!isValid);

      // get -> option and value for currency conversion
      short opc = Short.parseShort(option);

      CurrencyPairJsonBody responseBody;

      switch (opc) {
        case 1:
          // USD -> ARS
          this.MODEL.setPair(CurrencyCodes.USD, CurrencyCodes.ARS);
          break;
        case 2:
          // ARS -> USD
          this.MODEL.setPair(CurrencyCodes.ARS, CurrencyCodes.USD);
          break;
        case 3:
          // USD -> BRL
          this.MODEL.setPair(CurrencyCodes.USD, CurrencyCodes.BRL);
          break;
        case 4:
          // BRL -> USD
          this.MODEL.setPair(CurrencyCodes.BRL, CurrencyCodes.USD);
          break;
        case 5:
          // USD -> COP
          this.MODEL.setPair(CurrencyCodes.USD, CurrencyCodes.COP);
          break;
        case 6:
          // COP -> USD
          this.MODEL.setPair(CurrencyCodes.COP, CurrencyCodes.USD);
          break;
        case 0:
          System.out.println("Saindo...");
          running = false;
      }

      if (running) {
        responseBody = this.MODEL.getResponseBody();

        double value = Double.parseDouble(
            this.VIEW.getInput("Qual o valor para a conversão: ")
        );

        this.VIEW.show(
            value,
            responseBody.base_code(),
            this.MODEL.convertAtCurrentRate(responseBody.conversion_rate(), value),
            responseBody.target_code()
        );
      }
    }

    this.VIEW.stop();
  }
}

