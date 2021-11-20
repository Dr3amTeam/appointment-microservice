Feature: Como cliente quiero conocer la fecha que el trabajador irá a mi hogar
  Scenario: Incumplimiento en la llegada del trabajador
    Given que el trabajador acudirá a su hogar el sistema le enviará un mensaje automático
    When el trabajador esté cerca al domicilio
    Then el cliente tendrá conocimiento de cuándo llegará
