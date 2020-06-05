package com.example.demo.controller;

import org.apache.catalina.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeContoller {
	@RequestMapping
//型が UserDetails 実装型であるメソッドの引数、あるいは戻り値に付与することで、Authentication.getPrincipal()の呼び出し結果を埋め込んでくれるアノテーションです。
//UserDetails はSpring Securityにおいてユーザ被認証主体の情報を保持する役割を持ち、Spring Security上では UserDetails の持つ情報を使って認証・認可の仕組みを提供しますが、その UserDetails オブジェクトを簡単に取得できます。
//よくあるのが、Controllerメソッドの引数に埋め込んでログイン済みユーザ情報を使用するシーン
	public String home(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("name", user.getUsername());
		return "home.html";
		
	}

}
