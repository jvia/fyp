package org.bham.aucom.data;

// TODO figure out what can be deleted
public enum FileType {
	model, // system model file
	obs, // timeseries with observations
	dtp, // timeseries with dataTypes
	score, // timeseries with scores
	tdf, // timeseries with temporal duration features
	tpf, // timeseries with temporal probability features
}
