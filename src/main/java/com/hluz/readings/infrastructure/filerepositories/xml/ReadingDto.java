package com.hluz.readings.infrastructure.filerepositories.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class ReadingDto {

	@JacksonXmlProperty(isAttribute = true)
	public String clientID;
	@JacksonXmlProperty(isAttribute = true)
	public String period;
	@JacksonXmlText
	public String value;
}
