package com.zp.set.service;

import java.util.List;
import java.util.Map;

import com.zp.set.dao.AnalysisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zp.set.entity.HotDocument;
import com.zp.set.entity.MainAClass;
import com.zp.set.entity.MainType;


@Service
public class AnalysisService {
	
	@Autowired
	private AnalysisMapper analysisMapper;
	
	public List<MainType> getMainLists(Map<String, String> map) {
		return analysisMapper.getMainLists(map);
	}

	public List<Map<String, String>> getaLists(Map<String, String> map) {
		return analysisMapper.getaLists(map);
	}

	public List<HotDocument> listPageHotDocument(HotDocument hd) {
		return analysisMapper.listPageHotDocument(hd);
	}

	public List<MainAClass> getClassMainAClass(MainAClass mac) {
		return analysisMapper.getClassMainAClass(mac);
	}

	public List<MainType> getFileType(Map<String, Object> map) {
		return analysisMapper.getFileType(map);
	}

	public List<HotDocument> listPageiframeAnalyze(HotDocument hd) {
		return analysisMapper.listPageiframeAnalyze(hd);
	}

	public List<MainType> getMainLists4(Map<String, String> map) {
		return analysisMapper.getMainLists4(map);
	}

	public List<MainType> getMainLists4_1(Map<String, String> map) {
		return analysisMapper.getMainLists4_1(map);
	}

	public List<HotDocument> listPageiframeAnalyze4(HotDocument hd) {
		return analysisMapper.listPageiframeAnalyze4(hd);
	}

	public List<HotDocument> listPageiframeAnalyze4_1(HotDocument hd) {
		return analysisMapper.listPageiframeAnalyze4_1(hd);
	}

}
