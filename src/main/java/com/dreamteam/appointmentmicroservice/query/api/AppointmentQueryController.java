package com.dreamteam.appointmentmicroservice.query.api;

import com.dreamteam.appointmentmicroservice.config.SwaggerConfig;
import com.dreamteam.appointmentmicroservice.query.projections.AppointmentView;
import com.dreamteam.appointmentmicroservice.query.projections.AppointmentViewRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
@Api(tags={SwaggerConfig.APPOINTMENTS})
public class AppointmentQueryController {
    private final AppointmentViewRepository appointmentViewRepository;

    public AppointmentQueryController(AppointmentViewRepository appointmentViewRepository) {
        this.appointmentViewRepository = appointmentViewRepository;
    }

    @GetMapping("")
    @ApiOperation(value = "Obtener el listado de citas",response = List.class)
    public ResponseEntity<List<AppointmentView>> getAll(){
        try{
            return new ResponseEntity<List<AppointmentView>>(appointmentViewRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/appointment/{appointmentId}")
    @ApiOperation(value="Obtener citas por Id", response = List.class)
    public ResponseEntity<AppointmentView> getAppointmentsById(@PathVariable("appointmentId") String appointmentId){
        try{
            Optional<AppointmentView> appointmentViewOptional = appointmentViewRepository.findById(appointmentId);
            if(appointmentViewOptional.isPresent())
            {
                return new ResponseEntity<AppointmentView>(appointmentViewOptional.get(),HttpStatus.OK);
            }
            return new ResponseEntity("NOT FOUND",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("appointmentStatus/{appointmentStatus}")
    @ApiOperation(value = "Obtener un listado de citas mediante estado de pago", response = List.class)
    public ResponseEntity<List<AppointmentView>> getAllByPaymentStatus(@PathVariable("appointmentStatus")String appointmentStatus) {
        try {
            List<AppointmentView> appointmentView = appointmentViewRepository.getAppointmentViewsByStatus(appointmentStatus);
            return new ResponseEntity<List<AppointmentView>>(appointmentView, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
