package com.dreamteam.appointmentmicroservice.command.api;

import com.dhome.common.api.ApiController;
import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dreamteam.appointmentmicroservice.command.application.dto.*;
import com.dreamteam.appointmentmicroservice.command.application.services.AppointmentApplicationService;
import com.dreamteam.appointmentmicroservice.command.infra.AppointmentDateRepository;
import com.dreamteam.appointmentmicroservice.config.SwaggerConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
@Api(tags={SwaggerConfig.APPOINTMENTS})
public class AppointmentCommandController {
    private final AppointmentApplicationService appointmentApplicationService;
    private final CommandGateway commandGateway;
    private final AppointmentDateRepository appointmentDateRepository;

    public AppointmentCommandController(AppointmentApplicationService appointmentApplicationService, CommandGateway commandGateway, AppointmentDateRepository appointmentDateRepository) {
        this.appointmentApplicationService = appointmentApplicationService;
        this.commandGateway = commandGateway;
        this.appointmentDateRepository = appointmentDateRepository;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Crea las citas")
    public ResponseEntity<Object> create(@RequestBody AppointmentRequestDto appointmentRequestDto){
        try{
            Result<AppointmentResponseDto,Notification> result =
                    appointmentApplicationService.create(appointmentRequestDto);
            if(result.isSuccess()){
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        }catch (Exception e){
            return ApiController.serverError();
        }
    }

    @PutMapping("/{appointmentId}")
    @ApiOperation(value = "Confirmar la realización de la cita")
    public ResponseEntity<Object> edit(@PathVariable("appointmentId") String appointmentId,
                                       @RequestBody EditAppointmentRequestDto editAppointmentRequestDto){
        try{
            editAppointmentRequestDto.setAppointmentId(appointmentId);
            Result<EditAppointmentResponseDto, Notification> result =
                    appointmentApplicationService.edit(editAppointmentRequestDto);
            if (result.isSuccess())
                return ApiController.ok(result.getSuccess());
            return ApiController.error(result.getFailure().getErrors());
        }catch (AggregateNotFoundException exception){
            return ApiController.notFound();
        }catch (Exception e){
            return ApiController.serverError();
        }
    }

}
