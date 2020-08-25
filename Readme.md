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

## 맞팔 쿼리, 좋아요 카운트 쿼리

1. 좋아요 수 쿼리 (스칼라 서브쿼리)
   ![blog](https://postfiles.pstatic.net/MjAyMDA4MjRfMTYw/MDAxNTk4MjM5NzUwMjMy.VZH7JMI_P8AwMhJCSXxHfFSQq8uaJ7w6ufEjsvlae44g.mJoyoc69PAY-kHK5jeQW2JtrpOUA6i_qQFGcpqeHNNAg.PNG.getinthere/Screenshot_49.png?type=w773)

```sql
select
i.id,
i.caption,
(select count(*) from likes l where l.imageId = i.id) as 좋아요
from image i;
```

2. 맞팔 유무 쿼리 (Left outer Join 과 스칼라 서브쿼리)
   ![blog](https://postfiles.pstatic.net/MjAyMDA4MjRfMjAy/MDAxNTk4MjM3ODE4MjUw.pDKhnS9IE1usJqVXVVo9iNJOo5FPbC7YDOLBP4IwCQIg.3tTT-qYv5b27K9AMP-dZP1YauCvD-7MJLm_j6FvIvJkg.PNG.getinthere/Screenshot_48.png?type=w773)

```sql
select f1.id, f1.fromUserId, f1.toUserId, f1.createDate,
if(f2.fromUserId is null, false, true) "matpal"
from follow f1 left outer join follow f2
on f1.fromUserId = f2.toUserId and f1.toUserId = f2.fromUserId
order by f1.id;


select f1.id, f1.fromUserId, f1.toUserId,
f1.createDate,
(
select 1 from follow f2
where f1.fromUserId = f2.toUserId
and f1.toUserId = f2.fromUserId
) "matpal"
from follow f1;
```
