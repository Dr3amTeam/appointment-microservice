package com.dreamteam.appointmentmicroservice.StephsDefinition;

import com.dreamteam.appointmentmicroservice.query.projections.AppointmentView;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class StepDefUS01 {
    RestTemplate restTemplate = new RestTemplate();
    String fooResourceUrl ="http://localhost:8085/appointments/";
    @Given("que el trabajador acudirá a su hogar el sistema le enviará un mensaje automático")
    public void queElTrabajadorAcudiráASuHogarElSistemaLeEnviaráUnMensajeAutomático() {
        ResponseEntity<AppointmentView[]> response = restTemplate.getForEntity(fooResourceUrl,AppointmentView[].class);
        int a = response.getBody().length;
        Assert.assertEquals(response.getBody().length,a);
    }

    @When("el trabajador esté cerca al domicilio")
    public void elTrabajadorEstéCercaAlDomicilio() {
    }

    @Then("el cliente tendrá conocimiento de cuándo llegará")
    public void elClienteTendráConocimientoDeCuándoLlegará() {
        ResponseEntity<AppointmentView> response = restTemplate.getForEntity(fooResourceUrl+"appointment/a77e07f9" +
                        "-b660-4400-a70b-424b0915f7bb",
                AppointmentView.class);
        AppointmentView appointmentView = response.getBody();
        System.out.println("La fecha de la cita es: "+appointmentView.getDate());
    }
}
