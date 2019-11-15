package com.sc.act.api.test;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import com.google.common.io.Resources;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainServiceTest {

	static {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		JoranConfigurator configurator = new JoranConfigurator();
		configurator.setContext(lc);
		lc.reset();
		try {
			// 加载logback配置文件
			configurator.doConfigure(Resources.getResource("logback.xml"));
		} catch (JoranException e) {
			e.printStackTrace();
		}

	}


	private static final Logger LOG = LoggerFactory.getLogger(MainServiceTest.class);

	@Test
	public void test01() {
		LOG.info("测试 test01");

	}



}
