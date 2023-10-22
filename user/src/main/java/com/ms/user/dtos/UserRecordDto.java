package com.ms.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(@NotBlank String userName, @NotBlank @Email String userEmail) {
}
