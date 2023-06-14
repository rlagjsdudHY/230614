package com.tran.basic.svc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tran.basic.dao.ApplyDao;
import com.tran.basic.dto.ApplyDto;

@Service
public class Svcinterplet implements Svcinter {

	
		@Autowired
		ApplyDao applydao;
		
		//글목록보기
		@Override
		public List svcList() {
			
			return applydao.mtdList();
		}
		//글쓰기
		@Override
		public List<ApplyDto> mtdInsert(Map<String, String> map) {
			return applydao.mtdInsert(map);
		}
		
		//글내용보기
		@Override
		public ApplyDto mtdView(int num) {
			return applydao.mtdView(num);
		}
		
		//글수정
		@Override
		public List<ApplyDto> mtdUpdate(Map<String, String> map){
			return applydao.mtdUpdate(map);
		}
		
		
}
