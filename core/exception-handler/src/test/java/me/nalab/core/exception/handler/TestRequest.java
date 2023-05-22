package me.nalab.core.exception.handler;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
class TestRequest {

	@Length(max = 5, message = "overflow")
	@NotBlank(message = "blank")
	private String hello;

}
