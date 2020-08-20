## 스프링부트 JPA, MySQL, Security 인스타그램

## 의존성

![blog](https://postfiles.pstatic.net/MjAyMDA4MjBfNTAg/MDAxNTk3ODgzNDMxOTIw.lDN7lE8fkvGHKvbc-4-TkEWKIFaV51byktQEvhoOJqog.MjydMojrRqTmDhaLOHjnmBNnqn8d29148v6ijDJvdUMg.PNG.hjkl7913/Screenshot_1465.png?type=w773)

## MySQL 세팅

1. MySQL 한글 설정

```ini
[client]
default-character-set=utf8

[mysql]
default-character-set=utf8

[mysqld]
collation-server = utf8_unicode_ci
init-connect='SET NAMES utf8'
init_connect='SET collation_connection = utf8_general_ci'
character-set-server=utf8
```

2. 사용자 생성 및 권한 주기 및 DB 생성

- create user 'insta'@'%' identified by 'bitc5600';
- GRANT ALL PRIVILEGES ON 별.별 TO 'insta'@'%';
- create database insta;
- use insta;

## application.yml 설정

```yml
server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: UTF-8
      enabled: true
      force: true

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/insta?serverTimezone=Asia/Seoul&useSSL=false&allowPublicKeyRetrieval=true
    username: insta
    password: bitc5600

  jpa:
    open-in-view: true
    hibernate:
      ddl-auto: create
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
      use-new-id-generator-mappings: false
    show-sql: true # 쿼리문 콘솔창에 확인

  servlet:
    multipart:
      enabled: true
      max-file-size: 2MB #파일 업로드 용량 2MB로 제한

  # security 에서   아래 내용으로 로그인가능함 안걸면 해쉬비밀번호를 넣고 로그인 해야함
  security:
    user:
      name: cos
      password: 1234

# cos = yml 이 정식으로 읽는 태그가아니고 내가만든태그로 변수명이 cos 인것 찾아서 사용할수 있음
cos:
  secret: 겟인데어

# classpath : C:/src/springwork/instagram
file:
  path: C:/src/springwork/instagram/src/main/resources/upload/
```
