package com.yelp.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Auth {
	private String password;
	private String username;
}