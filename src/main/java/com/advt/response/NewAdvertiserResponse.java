package com.advt.response;

import lombok.Data;

@Data
public class NewAdvertiserResponse implements AdvertiserResponse {

	private String welcomeMessage;
	private String greatePlaceToAdvt;

}
