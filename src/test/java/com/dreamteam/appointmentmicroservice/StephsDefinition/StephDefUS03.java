package com.dreamteam.appointmentmicroservice.StephsDefinition;

import com.dreamteam.appointmentmicroservice.query.projections.AppointmentView;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class StephDefUS03 {
    RestTemplate restTemplate = new RestTemplate();
    String fooResourceUrl ="http://localhost:8085/appointments/";
    @Given("que en elperfil de cita se muestra el estado")
    public void queEnElperfilDeCitaSeMuestraElEstado() {
        ResponseEntity<AppointmentView[]> response = restTemplate.getForEntity(fooResourceUrl,AppointmentView[].class);
        int a = response.getBody().length;
        Assert.assertEquals(response.getBody().length,a);
    }

    @When("el cliente ingrese")
    public void elClienteIngrese() {
    }

    @Then("el el clientepuede revisar si se encuentra pendiente")
    public void elElClientepuedeRevisarSiSeEncuentraPendiente() {
        ResponseEntity<AppointmentView> response = restTemplate.getForEntity(fooResourceUrl+"appointment/19fda4ad" +
                        "-7b16-46c0-ae3e-a9a71e473f3f",
                AppointmentView.class);
        AppointmentView appointmentView = response.getBody();
        System.out.println("El estado de la cita es:  "+appointmentView.getStatus());
    }

    @Then("el el clientepuede revisar si se encuentra realizado")
    public void elElClientepuedeRevisarSiSeEncuentraRealizado() {
        ResponseEntity<AppointmentView> response = restTemplate.getForEntity(fooResourceUrl+"appointment/79d91bf4-02c4-4f9b-b074-9d7d1e25b313",
                AppointmentView.class);
        AppointmentView appointmentView = response.getBody();
        System.out.println("El estado de la cita es:  "+appointmentView.getStatus());
    }
}
