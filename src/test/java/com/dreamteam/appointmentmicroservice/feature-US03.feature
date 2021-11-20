Feature: Como clientequiero conocer el estado de mi cita
  Scenario: El cliente quiere conocer si su cita esta pendiente
    Given que en elperfil de cita se muestra el estado
    When el cliente ingrese
    Then el el clientepuede revisar si se encuentra pendiente

  Scenario: El cliente quiere conocer si su cita esta realizado
    Given que en elperfil de cita se muestra el estado
    When el cliente ingrese
    Then el el clientepuede revisar si se encuentra realizado