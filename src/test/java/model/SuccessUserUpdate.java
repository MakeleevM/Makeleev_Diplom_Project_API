package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SuccessUserUpdate{
	private String name;
	private String job;
	private String updatedAt;
}