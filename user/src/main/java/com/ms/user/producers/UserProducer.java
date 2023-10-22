package com.ms.user.producers;

import com.ms.user.dtos.EmailDto;
import com.ms.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {
    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(UserModel userModel) {
        var emailDto = new EmailDto();
        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getUserEmail());
        emailDto.setSubject("Cadastro realizado com sucesso!");

        var formattedText = userModel.getUserName() + ", seja bem-vindo(a)! \nAgradecemos o seu cadastro!";
        emailDto.setText(formattedText);

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}
