package com.tran.basic.svc;

import java.util.List;
import java.util.Map;

import com.tran.basic.dto.ApplyDto;

public interface Svcinter {
	//글목록보기
	public List<ApplyDto> svcList();
	
	//글쓰기
	public List<ApplyDto> mtdInsert(Map<String, String> map);
	
	//글자세히보기
	public ApplyDto mtdView(int num);
	
	//글수정
	public List<ApplyDto> mtdUpdate(Map<String, String> map);
}
