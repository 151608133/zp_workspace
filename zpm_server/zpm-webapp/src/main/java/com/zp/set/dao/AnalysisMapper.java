package com.zp.set.dao;


import com.zp.set.entity.HotDocument;
import com.zp.set.entity.MainAClass;
import com.zp.set.entity.MainType;

import java.util.List;
import java.util.Map;

public interface AnalysisMapper {

	List<MainType> getMainLists(Map<String, String> map);

	List<Map<String, String>> getaLists(Map<String, String> map);

	List<HotDocument> listPageHotDocument(HotDocument hd);

	List<MainAClass> getClassMainAClass(MainAClass mac);

	List<MainType> getFileType(Map<String, Object> map);

	List<HotDocument> listPageiframeAnalyze(HotDocument hd);

	List<MainType> getMainLists4(Map<String, String> map);

	List<MainType> getMainLists4_1(Map<String, String> map);

	List<HotDocument> listPageiframeAnalyze4(HotDocument hd);

	List<HotDocument> listPageiframeAnalyze4_1(HotDocument hd);

}
