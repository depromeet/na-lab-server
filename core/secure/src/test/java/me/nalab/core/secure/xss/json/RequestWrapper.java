package me.nalab.core.secure.xss.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class RequestWrapper {

	private Request request;

	private Map<String, Request> requestMap;

	private List<Request> requestList;

	RequestWrapper(String invalid) {
		request = new Request(invalid, invalid);
		requestMap = new HashMap<>();
		requestMap.put(invalid, new Request(invalid, invalid));
		requestList = new ArrayList<>();
		requestList.add(new Request(invalid, invalid));
	}

}
