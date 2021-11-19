package com.dreamteam.appointmentmicroservice.command.application.services;

import com.dhome.appointmentmicroservice.commands.CreateAppointment;
import com.dhome.appointmentmicroservice.commands.EditAppointment;
import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dhome.common.application.ResultType;
import com.dreamteam.appointmentmicroservice.command.application.dto.AppointmentResponseDto;
import com.dreamteam.appointmentmicroservice.command.application.dto.AppointmentRequestDto;
import com.dreamteam.appointmentmicroservice.command.application.dto.EditAppointmentRequestDto;
import com.dreamteam.appointmentmicroservice.command.application.dto.EditAppointmentResponseDto;
import com.dreamteam.appointmentmicroservice.command.application.validators.CreateAppointmentValidator;
import com.dreamteam.appointmentmicroservice.command.application.validators.EditAppointmentValidator;
import com.dreamteam.appointmentmicroservice.command.infra.AppointmentDateRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class AppointmentApplicationService {
    private final CreateAppointmentValidator createAppointmentValidator;
    private final CommandGateway commandGateway;
    private final EditAppointmentValidator editAppointmentValidator;
    private final AppointmentDateRepository appointmentDateRepository;

    public AppointmentApplicationService(CreateAppointmentValidator createAppointmentValidator, CommandGateway commandGateway, EditAppointmentValidator editAppointmentValidator, AppointmentDateRepository appointmentDateRepository) {
        this.createAppointmentValidator = createAppointmentValidator;
        this.commandGateway = commandGateway;
        this.editAppointmentValidator = editAppointmentValidator;
        this.appointmentDateRepository = appointmentDateRepository;
    }


    public Result<AppointmentResponseDto, Notification> create(AppointmentRequestDto appointmentRequestDto) throws Exception{
        Notification notification = this.createAppointmentValidator.validate(appointmentRequestDto);
        if (notification.hasErrors()){
            return Result.failure(notification);
        }
        String appointmentId = UUID.randomUUID().toString();
        CreateAppointment createAppointment = new CreateAppointment(
                appointmentId,
                appointmentRequestDto.getPaymentId(),
                appointmentRequestDto.getDate(),
                appointmentRequestDto.getDescription()
        );
        CompletableFuture<Object> future1 = commandGateway.send(createAppointment);
        CompletableFuture<ResultType> futureResult1 = future1.handle((ok,ex)->(ex!=null)?ResultType.FAILURE:
                ResultType.SUCCESS);
        ResultType resultType = futureResult1.get();
        if(resultType==ResultType.FAILURE){
            throw new Exception();
        }
        AppointmentResponseDto appointmentResponseDto = new AppointmentResponseDto(
            createAppointment.getDate(),
                createAppointment.getDescription()
        );
        return Result.success(appointmentResponseDto);
    }


    public Result<EditAppointmentResponseDto,Notification> edit(EditAppointmentRequestDto editAppointmentRequestDto) throws Exception{
        Notification notification = this.editAppointmentValidator.validate(editAppointmentRequestDto);
        if (notification.hasErrors()){
            return Result.failure(notification);
        }
        EditAppointment editAppointment = new EditAppointment(
                editAppointmentRequestDto.getAppointmentId().trim(),
                editAppointmentRequestDto.getPaymentId().trim(),
                editAppointmentRequestDto.getDate().trim(),
                editAppointmentRequestDto.getDescription().trim()
        );
        CompletableFuture<Object> future2 = commandGateway.send(editAppointment);
        CompletableFuture<ResultType> futureResult2 = future2.handle((ok,ex)->(ex!=null)? ResultType.FAILURE :
                ResultType.SUCCESS);
        ResultType resultType = futureResult2.get();
        if(resultType==ResultType.FAILURE){
            throw new Exception();
        }
        EditAppointmentResponseDto editAppointmentResponseDto = new EditAppointmentResponseDto(
                editAppointment.getDate().trim(),
                editAppointment.getDescription().trim()
        );
        return Result.success(editAppointmentResponseDto);
    }
}
