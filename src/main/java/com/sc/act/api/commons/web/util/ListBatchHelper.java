package com.sc.act.api.commons.web.util;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ListBatchHelper {

	private static final Logger LOG = LoggerFactory.getLogger(ListBatchHelper.class);

	public static void main(String[] args) {
		List<Integer> dataList = new ArrayList<Integer>();
		for (int i = 0; i < 12888; i++) {
			dataList.add(i);
		}

		// 分批处理
		if (null != dataList && dataList.size() > 0) {
			int pointsDataLimit = 7;// 限制条数
			Integer size = dataList.size();
			// 判断是否有必要分批
			if (pointsDataLimit < size) {
				int part = size / pointsDataLimit;// 分批数
				System.out.println("共有 ： " + size + "条，！" + " 分为 ：" + part + "批");
				//
				for (int i = 0; i < part; i++) {
					// 1000条
					List<Integer> listPage = dataList.subList(0, pointsDataLimit);
					System.out.println(listPage);
					// 剔除
					dataList.subList(0, pointsDataLimit).clear();
				}

				if (!dataList.isEmpty()) {
					System.out.println(dataList);// 表示最后剩下的数据
				}
			} else {
				System.out.println(dataList);
			}
		} else {
			System.out.println("没有数据!!!");
		}
	}

	public static List<String> listBatch(List<String> list, int batchNum) {
		List<String> dList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(list)) {
			int size = list.size();
			int part = size / batchNum;// 分批数
			// 判断是否有必要分批
			if (batchNum < size) {
				//
				for (int i = 0; i < part; i++) {
					LOG.debug("信息 {}", list.subList(0, batchNum));
					String str = StringUtils.collectionToDelimitedString(list.subList(0, batchNum), ",");
					dList.add(str);
					// 剔除
					list.subList(0, batchNum).clear();
				}

				if (!list.isEmpty()) {
					LOG.debug("信息 {}", list);
					String str = StringUtils.collectionToDelimitedString(list, ",");
					dList.add(str);// 表示最后剩下的数据
					part = part + 1;
				}

			} else {
				LOG.debug("信息 {}", list);
				String str = StringUtils.collectionToDelimitedString(list, ",");
				dList.add(str);// 表示最后剩下的数据
				part = part + 1;
			}
			LOG.info("总数据共有 :{} 条, 分为 :{} 批", new Object[] { size, part });
		}

		return dList;
	}

	public static List<String> listBatch200(List<String> list) {
		List<String> dList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(list)) {
			int size = list.size();
			int batchNum = 200;
			// 判断是否有必要分批
			if (batchNum < size) {
				int part = size / batchNum;// 分批数
				LOG.info("共有 :{} 条, 分为 :{} 批", new Object[] { size, part });
				//
				for (int i = 0; i < part; i++) {
					String str = StringUtils.collectionToDelimitedString(list.subList(0, batchNum), ",");
					dList.add(str);
					// 剔除
					list.subList(0, batchNum).clear();
				}

				if (!list.isEmpty()) {
					String str = StringUtils.collectionToDelimitedString(list.subList(0, batchNum), ",");
					dList.add(str);// 表示最后剩下的数据
				}
			} else {
				String str = StringUtils.collectionToDelimitedString(list.subList(0, batchNum), ",");
				dList.add(str);// 表示最后剩下的数据
			}
		}

		return dList;
	}
}
