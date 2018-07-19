# whonlinedemo
who demo
Hướng dẫn chạy project Spring Boot

##Công cụ:
Mysql
JDK 1.8
Eclipse
Plugin STS - Spring Tool Suite

##Các bước:
Clone git về
Mở eclipse đã có plugin STS
import project vào

Import database 'webelearning' vào mysql

Config thông số trước khi chạy gồm: 
File application.properties: 
###MySQL username and password 
spring.datasource.username= user mysql
spring.datasource.password= pass mysql

Xong. Chạy dự án bằng cách Nhấn phải project Chọn: Run as > Spring Boot App

Lưu ý: Khi dùng phát trực tiếp bị lỗi socket.io phải vào file streamlesson, detailcourse đổi server lại:
tìm dòng 'connection.socketURL' . Server dự phòng đã được comment lại, hãy bỏ comment để dùng 1 server đó.
##Tài khoản admin
quanglam.thieu@yahoo.com
123123123

Lần đầu nên đăng nhập vào admin sau đó tạo các tài khoản cho nhân viên, giáo viên.
Học viên có thể dùng qua Google, Facebook.
### Demo trên hosting



