package com.hluz.readings.application;import static java.util.Collections.sort;import java.util.List;public class DoubleExtensions {	public static double median(List<Double> values) {		sort(values);		int size = values.size();		if (size % 2 != 0) {			return values.get(size / 2).doubleValue();		}		return (values.get(size / 2 - 1).doubleValue() + values.get(size / 2).doubleValue()) / 2;	}}