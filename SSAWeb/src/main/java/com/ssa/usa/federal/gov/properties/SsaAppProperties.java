package com.ssa.usa.federal.gov.properties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="ssa")
@Data
public class SsaAppProperties {

	private Map<String,String> ssaProps=new HashMap<String,String>();
}
