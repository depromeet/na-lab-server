package me.nalab.core.secure.xss.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Request {

	private String hello;
	private String world;

}
