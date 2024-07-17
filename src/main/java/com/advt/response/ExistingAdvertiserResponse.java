package com.advt.response;

import lombok.Data;

@Data
public class ExistingAdvertiserResponse implements AdvertiserResponse {

	private String welcomeBack;
    private String yourRecentPrevioiusLocation;
}
