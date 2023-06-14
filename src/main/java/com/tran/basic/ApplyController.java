package com.tran.basic;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tran.basic.dto.ApplyDto;
import com.tran.basic.svc.Svcinter;

@Controller
public class ApplyController {
	
	@Autowired
	Svcinter svcinter;
	
	@RequestMapping("/")
	public String index(Model model) {
		
		return "applyForm";
	}
	
	// 지원서 입력양식
		@RequestMapping("/applyForm")
		public String mtdApplyForm() {
			return "applyForm";
		}
	
	// 지원 정보 목록보기
		@RequestMapping("/selList") 
		public String mtdSelList(Model model) {
		  
		  model.addAttribute("selList", svcinter.svcList());
		  
		  return "selList"; 
		 }
		
		// 지원서 내용 저장
		@RequestMapping("/apply")
		public String mtdApply(
				@ModelAttribute("applyDto") ApplyDto applyDto,
				BindingResult result,
				HttpServletRequest req, 
				Model model
				) {
			
			String	page	=	"redirect:selList";
			try {
				
				System.out.println(applyDto);
				
				if	(result.hasErrors())	{
				page	=	"applyForm";
				} else {
					
					req.setCharacterEncoding("UTF-8");
					String userName = req.getParameter("userName");
					String userPhone = req.getParameter("userPhone");
					String part = req.getParameter("part");
					String motive = req.getParameter("motive");			
					
					Map<String, String> map = new HashMap<>(); 
					map.put("item1", userName);
					map.put("item2", userPhone); 
					map.put("item3", part); 
					map.put("item4", motive);
					
					model.addAttribute(req);
					svcinter.mtdInsert(map);
				}
				
			} catch (Exception e) {
				e.getMessage();
			}
			return	page;
			//return "apply/insResult";
		}
		
		// 지원 정보 상세보기
		@RequestMapping("/selView") 
		public String mtdSelView(Model model, HttpServletRequest req) {
		  
			try {
				req.setCharacterEncoding("UTF-8");
				int num = Integer.parseInt(req.getParameter("num"));
				model.addAttribute("applyList", svcinter.mtdView(num));
				model.addAttribute("num",num);
			} catch (Exception e) {
				e.getMessage();
			}
		  
		  return "insResult"; 
		 }
		
		//수정하기 페이지 진입
		@RequestMapping("/applyModify")
		public String mtdModify(Model model, HttpServletRequest req) {
			try { 
				req.setCharacterEncoding("UTF-8");
				int num = Integer.parseInt(req.getParameter("num"));
				model.addAttribute("applyListView", svcinter.mtdView(num));
		} catch (Exception e) {
				e.getMessage();
		}
			return "applyModify";
		}
			
			//수정 완료
			@RequestMapping("/ModifyProc")
			public String ModifyProc(HttpServletRequest req, Model model) {
				String num = req.getParameter("num");
				try {
					req.setCharacterEncoding("UTF-8");
					String userName = req.getParameter("userName");
					String userPhone = req.getParameter("userPhone");
					String part = req.getParameter("part");
					String motive = req.getParameter("motive");
//					String num = req.getParameter("num");
					System.out.println(num);
					System.out.println(part);
					Map<String, String> map = new HashMap<>(); 
					map.put("item1", userName);
					map.put("item2", userPhone); 
					map.put("item3", part); 
					map.put("item4", motive);
					map.put("item5", num);
					
					model.addAttribute(req);
					svcinter.mtdUpdate(map);
				} catch (Exception e) {
					e.getMessage();
				}
				return "redirect:selView?num="+num;
			}
			
		
			
			
		}
		


