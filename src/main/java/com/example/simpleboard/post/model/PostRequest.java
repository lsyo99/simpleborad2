package com.example.simpleboard.post.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostRequest {
    @NotBlank
    private String userName;
    @NotBlank
    @Size(min=4, max=4)
    private String password;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}