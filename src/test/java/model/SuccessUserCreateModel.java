package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuccessUserCreateModel {

	private String createdAt;
	private String name;
	private String id;
	private String job;
}
