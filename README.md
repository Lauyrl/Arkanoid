Phiên bản JavaFX: 25

Phiên bản Maven: 3.9.11 \
Cách tải: https://maven.apache.org/install.html \
![tải cnay (windows)](image.png) \
Hnhu IDE của ae tự nhận dạng project Maven \
chạy `mvn clean compile` 1 lần trong IDE, hoặc cd vào file project r chạy \
`mvn javafx:run` để chạy game

App.java để khởi tạo cửa sổ, StackPane Canvas, GameEngine

GameEngine là lớp quản lí logic game

Cách commit: \
git checkout -b {ten cua branch tam thoi} \
git add .  (stage tat ca thay doi) \
git commit -m " ... " \
git push origin refactor {ten cua branch tam thoi} \
Về main: \
git checkout main \
git branch -d refactor \ (xoa branch tam thoi neu muon)

Nguồn tham khảo:
https://stackoverflow.com/questions/38636254/how-to-convert-json-to-java-object-using-gson \
https://www.geeksforgeeks.org/java/javafx-stackpane-class/ \
