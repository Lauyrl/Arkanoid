### Phiên bản JavaFX: 25
### Phiên bản Gson: 2.13.2
### Phiên bản Maven: 3.9.11  
`mvn javafx:run` để chạy game

## Cách commit:  
git checkout -b [tên branch tạm thời]  
git add [file muốn commit] (phân loại file theo chức năng, rồi chia thành nhiều commit)  
git commit -m " ... " (commit từng phần dần trên branch đó, khi nào xong hết rồi mới push)  

## Cách push và merge:  
git push origin [tên branch tạm thời]  
git checkout main (quay lại nhánh main)  
git branch -d [tên branch tạm thời] (xoá branch tạm thời khỏi máy)  

## Nguồn tham khảo:  
### Về StackPane:   
https://www.geeksforgeeks.org/java/javafx-stackpane-class/

### Về Gson:  
https://stackoverflow.com/questions/38636254/  
https://stackoverflow.com/questions/3763937/gson-and-deserializing-an-array-of-objects-with-arrays-in-it

### Axis aligned bounding boxes, seperating axis:
https://dyn4j.org/2010/01/sat/
https://benpm.github.io/blog/2d-top-down-collisions-with-rectangles-aabbs/

### Paddle:  
https://gamedev.stackexchange.com/questions/4253/in-pong-how-do-you-calculate-the-balls-direction-when-it-bounces-off-the-paddl
