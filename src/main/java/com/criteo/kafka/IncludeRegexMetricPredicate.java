package com.criteo.kafka;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.yammer.metrics.core.Metric;
import com.yammer.metrics.core.MetricName;
import com.yammer.metrics.core.MetricPredicate;

public class IncludeRegexMetricPredicate implements MetricPredicate {

	Pattern pattern = null;
	boolean verbose = false;
	static Logger LOG = Logger.getLogger(IncludeRegexMetricPredicate.class);

	public IncludeRegexMetricPredicate(String regex, boolean verbose) {
		pattern = Pattern.compile(regex);
		this.verbose = verbose;
	}

	@Override
	public boolean matches(MetricName name, Metric metric) {
		boolean ok = pattern.matcher(name.getName()).matches();
		if (verbose) {
			LOG.info(String.format("name: %s - %s", name.getName(), ok));
		}
		return ok;
	}

}
