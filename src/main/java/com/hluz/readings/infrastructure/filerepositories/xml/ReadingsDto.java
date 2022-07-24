package com.hluz.readings.infrastructure.filerepositories.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.ArrayList;

@JacksonXmlRootElement(localName = "readings")
public class ReadingsDto {

	@JacksonXmlProperty(localName = "reading")
	@JacksonXmlElementWrapper(useWrapping = false)
	public ArrayList<ReadingDto> readings;
}
