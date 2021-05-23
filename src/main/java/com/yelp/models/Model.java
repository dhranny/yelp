package com.yelp.models;

import lombok.Data;
import javax.persistence.Id;

@Data
public class Model {

	@Id
	long id;
}
