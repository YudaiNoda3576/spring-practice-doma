package com.example.demo;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//　これも必要
@Configuration
//　これが必要↓
@EnableWebSecurity
//　WebSecurityConfigurerAdapterを継承したたクラスであること
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
//	SQL文を実行できるようにする
    @Autowired
    private DataSource datasource;

//    このメソッドをDIコンテナに入れるという設定。この設定を行うときはクラス自体に@Configurationをつける必要がある
//    ユーザー登録するときなど様々な場面で使用するからDIコンテナの管理下に置いて様々な場所で使えるようにする
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//       認可担当
    	http
            .authorizeRequests()
            .antMatchers("/signup", "/login", "/password/*")
            .permitAll()
//            **はその配下(サブディレクトリも)全て含めるという意味のantの表現で、正規表現とは違う
            .antMatchers("/admin/**")
            .hasAuthority("ADMIN")
            .anyRequest()
            .authenticated();
//    	認証
        http
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login")
//            ("/home", trueとか書けば絶対ここに飛ぶという指定ができる)
            .defaultSuccessUrl("/home")
            .usernameParameter("loginId")
            .passwordParameter("password")
            .failureUrl("/login");
//      ログアウトの設定
        http
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login");
    }

//    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//       JDBC(データベース)使いますよという宣言的なもの
    	auth.jdbcAuthentication()
            .dataSource(datasource)
            .usersByUsernameQuery(
                "SELECT loginId, password, is_active from users where loginId= ?")
            .authoritiesByUsernameQuery("SELECT loginId, role from users where loginId = ?")
//            どんなパスワードのハッシュアルゴリズム使うのかという設定・passwordEncoder()はユーザー登録するときなど様々な場面で使用する
            .passwordEncoder(passwordEncoder());
    }
}
